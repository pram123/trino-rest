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

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "previous",
    "current",
    "next"
})
@Generated("jsonschema2pojo")
public class Links
{
    @JsonProperty("previous")
    private String previous;
    @JsonProperty("current")
    private String current;
    @JsonProperty("next")
    private String next;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("previous")
    public String getPrevious()
    {
        return previous;
    }

    @JsonProperty("previous")
    public void setPrevious(String previous)
    {
        this.previous = previous;
    }

    @JsonProperty("current")
    public String getCurrent()
    {
        return current;
    }

    @JsonProperty("current")
    public void setCurrent(String current)
    {
        this.current = current;
    }

    @JsonProperty("next")
    public String getNext()
    {
        return next;
    }

    @JsonProperty("next")
    public void setNext(String next)
    {
        this.next = next;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties()
    {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value)
    {
        this.additionalProperties.put(name, value);
    }
}
