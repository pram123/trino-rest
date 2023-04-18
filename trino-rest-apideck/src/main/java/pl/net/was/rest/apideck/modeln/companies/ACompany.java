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

package pl.net.was.rest.apideck.modeln.companies;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class ACompany
        implements Envelope<Company>
{
    private final Company company;
    private final Integer ok;
    private final String status;
    private final Meta meta;
    private final Links links;
    private final String service;
    private final String resource;
    private final String operation;

    public ACompany(
            @JsonProperty("status_code") Integer ok,
            @JsonProperty("status") String status,
            @JsonProperty("service") String service,
            @JsonProperty("resource") String resource,
            @JsonProperty("operation") String operation,
            @JsonProperty("data") Company company,
            @JsonProperty("meta") Meta meta,
            @JsonProperty("links") Links links)
    {
        this.ok = ok;
        this.meta = meta;
        this.links = links;
        this.status = status;
        this.service = service;
        this.resource = resource;
        this.operation = operation;
        requireNonNull(company, "company is null");
        this.company = company;
        //super(ok, status, service, resource, operation,meta,links);
    }

    @Override
    public List<Company> getItems()
    {
        ArrayList<Company> list = new ArrayList<>();
        list.add(company);
        return list;
    }

    @Override
    public List<Company> getCompanies()
    {
        ArrayList<Company> list = new ArrayList<>();
        list.add(company);
        return list;
    }

    @Override
    public boolean isOk()
    {
        if (this.ok == 200) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String getError()
    {
        return this.status;
    }

    @Override
    public String getNextCursor()
    {
        if (this.meta.getCursors().getNext() == null) {
            return "";
        }
        else {
            return this.meta.getCursors().getNext();
        }
    }
}
