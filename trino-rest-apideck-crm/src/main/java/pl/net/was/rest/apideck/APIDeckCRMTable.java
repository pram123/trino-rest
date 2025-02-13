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

import io.trino.spi.connector.SchemaTableName;
import pl.net.was.rest.RestTableHandle;

public enum APIDeckCRMTable
{
    COMPANIES("companies"),
    CONTACTS("contacts"),
    OPPORTUNITIES("opportunities"),
    USERS("users"),
    CHANNELS("channels"),
    CHANNEL_MEMBERS("channel_members"),
    MESSAGES("messages"),
    REPLIES("replies");

    private final String name;

    APIDeckCRMTable(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public static APIDeckCRMTable valueOf(RestTableHandle table)
    {
        return valueOf(table.getSchemaTableName());
    }

    public static APIDeckCRMTable valueOf(SchemaTableName schemaTable)
    {
        return valueOf(schemaTable.getTableName().toUpperCase());
    }
}
