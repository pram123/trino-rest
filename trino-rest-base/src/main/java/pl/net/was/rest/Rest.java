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

package pl.net.was.rest;

import io.trino.spi.HostAddress;
import io.trino.spi.Node;
import io.trino.spi.NodeManager;
import io.trino.spi.connector.ColumnHandle;
import io.trino.spi.connector.ColumnMetadata;
import io.trino.spi.connector.ConnectorSession;
import io.trino.spi.connector.ConnectorSplitManager;
import io.trino.spi.connector.ConnectorSplitSource;
import io.trino.spi.connector.ConnectorTableHandle;
import io.trino.spi.connector.ConnectorTableMetadata;
import io.trino.spi.connector.Constraint;
import io.trino.spi.connector.ConstraintApplicationResult;
import io.trino.spi.connector.DynamicFilter;
import io.trino.spi.connector.FixedSplitSource;
import io.trino.spi.connector.LimitApplicationResult;
import io.trino.spi.connector.SchemaTableName;
import io.trino.spi.connector.SchemaTablePrefix;
import io.trino.spi.connector.SortItem;
import io.trino.spi.connector.TopNApplicationResult;
import io.trino.spi.statistics.TableStatistics;
import io.trino.spi.type.Type;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import static java.util.stream.Collectors.toList;

public interface Rest
{
    ConnectorTableMetadata getTableMetadata(SchemaTableName schemaTableName);

    List<String> listSchemas();

    default List<SchemaTableName> listTables()
    {
        return listSchemas().stream()
                .flatMap(schema -> listTables(schema).stream())
                .collect(toList());
    }

    List<SchemaTableName> listTables(String schema);

    Map<SchemaTableName, List<ColumnMetadata>> listTableColumns(SchemaTablePrefix schemaTablePrefix);

    Iterable<List<?>> getRows(RestTableHandle table);

    Consumer<List> createRowSink(SchemaTableName schemaTableName);

    default List<Type> getTypes(SchemaTableName schemaTableName)
    {
        return getTableMetadata(schemaTableName).getColumns().stream()
                .map(ColumnMetadata::getType)
                .collect(toList());
    }

    default Optional<LimitApplicationResult<ConnectorTableHandle>> applyLimit(
            ConnectorSession session,
            ConnectorTableHandle handle,
            long limit)
    {
        return Optional.empty();
    }

    default Optional<TopNApplicationResult<ConnectorTableHandle>> applyTopN(
            ConnectorSession session,
            ConnectorTableHandle handle,
            long topNCount,
            List<SortItem> sortItems,
            Map<String, ColumnHandle> assignments)
    {
        return Optional.empty();
    }

    default Optional<ConstraintApplicationResult<ConnectorTableHandle>> applyFilter(
            ConnectorSession session,
            ConnectorTableHandle handle,
            Constraint constraint)
    {
        return Optional.empty();
    }

    default TableStatistics getTableStatistics(
            ConnectorSession session,
            ConnectorTableHandle tableHandle,
            Constraint constraint)
    {
        return TableStatistics.empty();
    }

    default ConnectorSplitSource getSplitSource(
            NodeManager nodeManager,
            ConnectorTableHandle table,
            ConnectorSplitManager.SplitSchedulingStrategy splitSchedulingStrategy,
            DynamicFilter dynamicFilter)
    {
        if (splitSchedulingStrategy != ConnectorSplitManager.SplitSchedulingStrategy.UNGROUPED_SCHEDULING) {
            throw new IllegalArgumentException("Unknown splitSchedulingStrategy: " + splitSchedulingStrategy);
        }

        RestTableHandle handle = (RestTableHandle) table;

        List<HostAddress> addresses = nodeManager.getRequiredWorkerNodes().stream()
                .map(Node::getHostAndPort)
                .collect(toList());

        List<RestConnectorSplit> splits = List.of(new RestConnectorSplit(handle, addresses));
        return new FixedSplitSource(splits);
    }
}
