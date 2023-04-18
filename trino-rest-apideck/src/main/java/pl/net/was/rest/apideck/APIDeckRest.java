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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import pl.net.was.rest.apideck.modeln.companies.Envelope;
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

    static String jsonOutput = "{\"status_code\":200,\"status\":\"OK\",\"service\":\"zoho-crm\",\"resource\":\"companies\",\"operation\":\"all\",\"data\":[{\"id\":\"12345\",\"name\":\"SpaceX\",\"interaction_count\":1,\"owner_id\":\"12345\",\"image\":\"https://www.spacex.com/static/images/share.jpg\",\"description\":\"Space Exploration Technologies Corp. is an American aerospace manufacturer, space transportation services and communications company headquartered in Hawthorne, California.\",\"vat_number\":\"BE0689615164\",\"currency\":\"UNKNOWN_CURRENCY\",\"status\":\"Open\",\"fax\":\"+12129876543\",\"annual_revenue\":\"+$35m\",\"number_of_employees\":\"500-1000\",\"industry\":\"Apparel\",\"ownership\":\"Public\",\"sales_tax_number\":\"12456EN\",\"payee_number\":\"78932EN\",\"abn_or_tfn\":\"46 115 614 695\",\"abn_branch\":\"123\",\"acn\":\"XXX XXX XXX\",\"first_name\":\"Elon\",\"last_name\":\"Musk\",\"parent_id\":\"22345\",\"bank_accounts\":[{\"account_number\":\"123465\",\"account_name\":\"SPACEX LLC\",\"account_type\":\"bank_account\",\"iban\":\"CH2989144532982975332\",\"bic\":\"AUDSCHGGXXX\",\"bsb_number\":\"062-001\",\"branch_identifier\":\"001\",\"bank_code\":\"BNH\",\"currency\":\"UNKNOWN_CURRENCY\"}],\"websites\":[{\"id\":\"12345\",\"url\":\"http://example.com\",\"type\":\"primary\"}],\"addresses\":[{\"id\":\"123\",\"type\":\"primary\",\"string\":\"25 Spring Street, Blackburn, VIC 3130\",\"name\":\"HQ US\",\"line1\":\"Main street\",\"line2\":\"apt #\",\"line3\":\"Suite #\",\"line4\":\"delivery instructions\",\"street_number\":\"25\",\"city\":\"San Francisco\",\"state\":\"CA\",\"postal_code\":\"94104\",\"country\":\"US\",\"latitude\":\"40.759211\",\"longitude\":\"-73.984638\",\"county\":\"Santa Clara\",\"contact_name\":\"Elon Musk\",\"salutation\":\"Mr\",\"phone_number\":\"111-111-1111\",\"fax\":\"122-111-1111\",\"email\":\"elon@musk.com\",\"website\":\"https://elonmusk.com\",\"row_version\":\"1-12345\"}],\"social_links\":[{\"id\":\"12345\",\"url\":\"https://www.twitter.com/apideck-io\",\"type\":\"twitter\"}],\"phone_numbers\":[{\"id\":\"12345\",\"country_code\":\"1\",\"area_code\":\"323\",\"number\":\"111-111-1111\",\"extension\":\"105\",\"type\":\"primary\"}],\"emails\":[{\"id\":\"123\",\"email\":\"elon@musk.com\",\"type\":\"primary\"}],\"row_type\":{\"id\":\"12345\",\"name\":\"Customer Account\"},\"custom_fields\":[{\"id\":\"2389328923893298\",\"name\":\"employee_level\",\"description\":\"Employee Level\",\"value\":\"Uses Salesforce and Marketo\"}],\"tags\":[\"New\"],\"read_only\":false,\"last_activity_at\":\"2020-09-30T07:43:32.000Z\",\"deleted\":false,\"salutation\":\"Mr\",\"birthday\":\"2000-08-12\",\"updated_by\":\"12345\",\"created_by\":\"12345\",\"updated_at\":\"2020-09-30T07:43:32.000Z\",\"created_at\":\"2020-09-30T07:43:32.000Z\"}],\"meta\":{\"items_on_page\":50,\"cursors\":{\"previous\":\"em9oby1jcm06OnBhZ2U6OjE=\",\"current\":\"em9oby1jcm06OnBhZ2U6OjI=\",\"next\":\"em9oby1jcm06OnBhZ2U6OjM=\"}},\"links\":{\"previous\":\"https://unify.apideck.com/crm/companies?cursor=em9oby1jcm06OnBhZ2U6OjE%3D\",\"current\":\"https://unify.apideck.com/crm/companies\",\"next\":\"https://unify.apideck.com/crm/companies?cursor=em9oby1jcm06OnBhZ2U6OjM\"}}";

    static ObjectMapper objectMapper = new ObjectMapper();
    public static final Map<APIDeckCRMTable, List<ColumnMetadata>> columns;

    static {
        ImmutableMap.Builder<APIDeckCRMTable, List<ColumnMetadata>> apiDeckCRMTableListBuilder = ImmutableMap.builder();
        try {
            List<ColumnMetadata> companyColumns = objectMapper.readValue(jsonOutput, new TypeReference<List<ColumnMetadata>>() {});
            apiDeckCRMTableListBuilder.put(APIDeckCRMTable.COMPANIES, companyColumns);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        columns = apiDeckCRMTableListBuilder.build();
    }

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
