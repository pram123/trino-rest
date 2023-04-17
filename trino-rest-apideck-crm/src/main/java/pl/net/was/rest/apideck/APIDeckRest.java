/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.net.was.rest.apideck;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.trino.spi.TrinoException;
import io.trino.spi.connector.ColumnHandle;
import io.trino.spi.connector.ColumnMetadata;
import io.trino.spi.connector.ConnectorSession;
import io.trino.spi.connector.ConnectorTableHandle;
import io.trino.spi.connector.ConnectorTableMetadata;
import io.trino.spi.connector.Constraint;
import io.trino.spi.connector.ConstraintApplicationResult;
import io.trino.spi.connector.SchemaTableName;
import io.trino.spi.connector.SchemaTablePrefix;
import io.trino.spi.predicate.TupleDomain;
import pl.net.was.rest.Rest;
import pl.net.was.rest.RestColumnHandle;
import pl.net.was.rest.RestConfig;
import pl.net.was.rest.RestTableHandle;
import pl.net.was.rest.apideck.filter.CompanyFilter;
import pl.net.was.rest.apideck.model.Envelope;
import pl.net.was.rest.filter.FilterApplier;
import retrofit2.Call;
import retrofit2.Response;

import javax.inject.Inject;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.trino.spi.StandardErrorCode.GENERIC_INTERNAL_ERROR;
import static io.trino.spi.StandardErrorCode.INVALID_ROW_FILTER;
import static io.trino.spi.type.VarcharType.VARCHAR;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static pl.net.was.rest.RestModule.getService;

public class APIDeckRest
        implements Rest
{
    public static final String SCHEMA_NAME = "default";
    private static final int PER_PAGE = 200;
    static String token;
    static String xAPIDeckAppID;
    static String xAPIDeckConsumerID;
    static String xAPIDeckServiceID;
    static String webServiceUrl;
    private static APIDeckService service;

    public static final Map<APIDeckCRMTable, List<ColumnMetadata>> columns = new ImmutableMap.Builder<APIDeckCRMTable, List<ColumnMetadata>>()
            .put(APIDeckCRMTable.COMPANIES, ImmutableList.of(
                    new ColumnMetadata("id", VARCHAR),
                    new ColumnMetadata("name", VARCHAR)))
            .build();

    private final Map<APIDeckCRMTable, Function<RestTableHandle, Iterable<List<?>>>> rowGetters = new ImmutableMap.Builder<APIDeckCRMTable, Function<RestTableHandle, Iterable<List<?>>>>()
            .put(APIDeckCRMTable.COMPANIES, this::getCompanies)
            .build();

    private final Map<APIDeckCRMTable, Map<String, ColumnHandle>> columnHandles;

    private final Map<APIDeckCRMTable, ? extends FilterApplier> filterAppliers = new ImmutableMap.Builder<APIDeckCRMTable, FilterApplier>()
            .put(APIDeckCRMTable.COMPANIES, new CompanyFilter())
            .build();

    @Inject
    public APIDeckRest(RestConfig config)
    {
        requireNonNull(config, "config is null");
        APIDeckRest.token = config.getToken();
        APIDeckRest.xAPIDeckAppID = config.getApideckAppID();
        APIDeckRest.xAPIDeckConsumerID = config.getApideckConsumerId();
        APIDeckRest.xAPIDeckServiceID = config.getApideckServiceID();

        APIDeckRest.service = getService(APIDeckService.class, config.getWebServiceUrl(),
                config.getClientBuilder());

        columnHandles = columns.keySet()
                .stream()
                .collect(Collectors.toMap(
                        tableName -> tableName,
                        tableName -> columns.get(tableName)
                                .stream()
                                .collect(toMap(
                                        ColumnMetadata::getName,
                                        column -> new RestColumnHandle(column.getName(), column.getType())))));
    }

    @Override
    public ConnectorTableMetadata getTableMetadata(SchemaTableName schemaTableName)
    {
        APIDeckCRMTable tableName = APIDeckCRMTable.valueOf(schemaTableName);
        return new ConnectorTableMetadata(
                schemaTableName,
                columns.get(tableName));
    }

    @Override
    public List<String> listSchemas()
    {
        return ImmutableList.of(SCHEMA_NAME);
    }

    @Override
    public List<SchemaTableName> listTables(String schema)
    {
        return columns
                .keySet()
                .stream()
                .map(table -> new SchemaTableName(SCHEMA_NAME, table.getName()))
                .collect(toList());
    }

    @Override
    public Map<SchemaTableName, List<ColumnMetadata>> listTableColumns(SchemaTablePrefix schemaTablePrefix)
    {
        return columns.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> new SchemaTableName(schemaTablePrefix.getSchema().orElse(""), e.getKey().getName()),
                        Map.Entry::getValue));
    }

    @Override
    public Iterable<List<?>> getRows(RestTableHandle table)
    {
        APIDeckCRMTable tableName = APIDeckCRMTable.valueOf(table);
        return rowGetters.get(tableName).apply(table);
    }

    private Iterable<List<?>> getCompanies(RestTableHandle table)
    {
        if (table.getLimit() == 0) {
            return List.of();
        }
        APIDeckCRMTable tableName = APIDeckCRMTable.valueOf(table);
        TupleDomain<ColumnHandle> constraint = table.getConstraint();
        Map<String, ColumnHandle> columns = columnHandles.get(tableName);
        FilterApplier filter = filterAppliers.get(tableName);

//        String type = (String) filter.getFilter((RestColumnHandle) columns.get("type"), constraint, "public_channel");
        return getRowsFromPagesEnvelope(
                 cursor -> service.listCompanies(
                         xAPIDeckConsumerID,
                         xAPIDeckServiceID,
                         xAPIDeckAppID,
                         "Bearer " + token,
                                 // cursor
                         PER_PAGE),
                 company -> Stream.of(company.toRow()),
                 table.getLimit());
    }

    private void requirePredicate(Object value, String name)
    {
        if (value == null) {
            throw new TrinoException(INVALID_ROW_FILTER, "Missing required constraint for " + name);
        }
    }

    private <T, E extends Envelope<T>> Iterable<List<?>> getRowsFromPagesEnvelope(
            Function<String, Call<E>> fetcher,
            Function<T, Stream<List<?>>> mapper,
            int limit)
    {
        return () -> new Iterator<>()
        {
            int resultSize;
            String cursor = "";
            Iterator<List<?>> rows;

            @Override
            public boolean hasNext()
            {
                if (rows != null && rows.hasNext()) {
                    return true;
                }
                if (resultSize >= limit) {
                    return false;
                }
                Response<E> response;
                try {
                    response = fetcher.apply(cursor).execute();
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (response.code() == HTTP_NOT_FOUND) {
                    return false;
                }
                Rest.checkServiceResponse(response);
                E envelope = requireNonNull(response.body(), "response body is null");
                if (!envelope.isOk()) {
                    throw new TrinoException(GENERIC_INTERNAL_ERROR, envelope.getError());
                }
                List<T> items = envelope.getItems();
                if (items.size() == 0) {
                    return false;
                }
                int itemsToUse = Math.min(
                        Math.max(0, limit - resultSize),
                        items.size());
                List<List<?>> rows = items.subList(0, itemsToUse).stream().flatMap(mapper).collect(toList());

                // mapper can produce 1 or more rows per item, so subList them again
                rows = rows.subList(0, itemsToUse);
                this.rows = rows.iterator();
                resultSize += itemsToUse;
                cursor = envelope.getNextCursor();
                if (cursor.isEmpty()) {
                    resultSize = limit;
                }
                return true;
            }

            @Override
            public List<?> next()
            {
                return rows.next();
            }
        };
    }

    @Override
    public Optional<ConstraintApplicationResult<ConnectorTableHandle>> applyFilter(
            ConnectorSession session,
            ConnectorTableHandle table,
            Constraint constraint)
    {
        RestTableHandle restTable = (RestTableHandle) table;
        APIDeckCRMTable tableName = APIDeckCRMTable.valueOf(restTable);

        FilterApplier filterApplier = filterAppliers.get(tableName);
        if (filterApplier == null) {
            return Optional.empty();
        }
        return filterApplier.applyFilter(
                restTable,
                columnHandles.get(tableName),
                filterApplier.getSupportedFilters(),
                constraint.getSummary());
    }

    @Override
    public Consumer<List> createRowSink(SchemaTableName schemaTableName)
    {
        throw new IllegalStateException("This connector does not support write");
    }
}
