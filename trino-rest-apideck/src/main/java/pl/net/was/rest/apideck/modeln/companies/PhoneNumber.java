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
    "id",
    "country_code",
    "area_code",
    "number",
    "extension",
    "type"
})

@Generated("jsonschema2pojo")

public class PhoneNumber
{
    @JsonProperty("id")
    private String id;
    @JsonProperty("country_code")
    private String countryCode;
    @JsonProperty("area_code")
    private String areaCode;
    @JsonProperty("number")
    private String number;
    @JsonProperty("extension")
    private String extension;
    @JsonProperty("type")
    private String type;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public String getId()
    {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id)
    {
        this.id = id;
    }

    @JsonProperty("country_code")
    public String getCountryCode()
    {
        return countryCode;
    }

    @JsonProperty("country_code")
    public void setCountryCode(String countryCode)
    {
        this.countryCode = countryCode;
    }

    @JsonProperty("area_code")
    public String getAreaCode()
    {
        return areaCode;
    }

    @JsonProperty("area_code")
    public void setAreaCode(String areaCode)
    {
        this.areaCode = areaCode;
    }

    @JsonProperty("number")
    public String getNumber()
    {
        return number;
    }

    @JsonProperty("number")
    public void setNumber(String number)
    {
        this.number = number;
    }

    @JsonProperty("extension")
    public String getExtension()
    {
        return extension;
    }

    @JsonProperty("extension")
    public void setExtension(String extension)
    {
        this.extension = extension;
    }

    @JsonProperty("type")
    public String getType()
    {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type)
    {
        this.type = type;
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
