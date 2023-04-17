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

package pl.net.was.rest.apideck.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;

import java.util.List;

public class Company
{
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;

    @JsonCreator
    public Company(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name)
    {
        this.id = id;
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getId()
    {
        return id;
    }

    public List<?> toRow()
    {
      /*  BlockBuilder pendingSharedList = VARCHAR.createBlockBuilder(null, pendingShared != null ? pendingShared.size() : 0);
        if (pendingShared != null) {
            for (String name : pendingShared) {
                VARCHAR.writeString(pendingSharedList, name);
            }
        }
        BlockBuilder previousNamesList = VARCHAR.createBlockBuilder(null, previousNames != null ? previousNames.size() : 0);
        if (previousNames != null) {
            for (String name : previousNames) {
                VARCHAR.writeString(previousNamesList, name);
            }
        }

       */
        return ImmutableList.builder()
                .add(
                        id != null ? id : "",
                        name != null ? name : "")
                .build();
    }
}
