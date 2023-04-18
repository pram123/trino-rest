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
    "type",
    "string",
    "name",
    "line1",
    "line2",
    "line3",
    "line4",
    "street_number",
    "city",
    "state",
    "postal_code",
    "country",
    "latitude",
    "longitude",
    "county",
    "contact_name",
    "salutation",
    "phone_number",
    "fax",
    "email",
    "website",
    "row_version"
})
@Generated("jsonschema2pojo")
public class Address
{
    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("string")
    private String string;
    @JsonProperty("name")
    private String name;
    @JsonProperty("line1")
    private String line1;
    @JsonProperty("line2")
    private String line2;
    @JsonProperty("line3")
    private String line3;
    @JsonProperty("line4")
    private String line4;
    @JsonProperty("street_number")
    private String streetNumber;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("postal_code")
    private String postalCode;
    @JsonProperty("country")
    private String country;
    @JsonProperty("latitude")
    private String latitude;
    @JsonProperty("longitude")
    private String longitude;
    @JsonProperty("county")
    private String county;
    @JsonProperty("contact_name")
    private String contactName;
    @JsonProperty("salutation")
    private String salutation;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("fax")
    private String fax;
    @JsonProperty("email")
    private String email;
    @JsonProperty("website")
    private String website;
    @JsonProperty("row_version")
    private String rowVersion;
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

    @JsonProperty("string")
    public String getString()
    {
        return string;
    }

    @JsonProperty("string")
    public void setString(String string)
    {
        this.string = string;
    }

    @JsonProperty("name")
    public String getName()
    {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name)
    {
        this.name = name;
    }

    @JsonProperty("line1")
    public String getLine1()
    {
        return line1;
    }

    @JsonProperty("line1")
    public void setLine1(String line1)
    {
        this.line1 = line1;
    }

    @JsonProperty("line2")
    public String getLine2()
    {
        return line2;
    }

    @JsonProperty("line2")
    public void setLine2(String line2)
    {
        this.line2 = line2;
    }

    @JsonProperty("line3")
    public String getLine3()
    {
        return line3;
    }

    @JsonProperty("line3")
    public void setLine3(String line3)
    {
        this.line3 = line3;
    }

    @JsonProperty("line4")
    public String getLine4()
    {
        return line4;
    }

    @JsonProperty("line4")
    public void setLine4(String line4)
    {
        this.line4 = line4;
    }

    @JsonProperty("street_number")
    public String getStreetNumber()
    {
        return streetNumber;
    }

    @JsonProperty("street_number")
    public void setStreetNumber(String streetNumber)
    {
        this.streetNumber = streetNumber;
    }

    @JsonProperty("city")
    public String getCity()
    {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city)
    {
        this.city = city;
    }

    @JsonProperty("state")
    public String getState()
    {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state)
    {
        this.state = state;
    }

    @JsonProperty("postal_code")
    public String getPostalCode()
    {
        return postalCode;
    }

    @JsonProperty("postal_code")
    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    @JsonProperty("country")
    public String getCountry()
    {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country)
    {
        this.country = country;
    }

    @JsonProperty("latitude")
    public String getLatitude()
    {
        return latitude;
    }

    @JsonProperty("latitude")
    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    @JsonProperty("longitude")
    public String getLongitude()
    {
        return longitude;
    }

    @JsonProperty("longitude")
    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    @JsonProperty("county")
    public String getCounty()
    {
        return county;
    }

    @JsonProperty("county")
    public void setCounty(String county)
    {
        this.county = county;
    }

    @JsonProperty("contact_name")
    public String getContactName()
    {
        return contactName;
    }

    @JsonProperty("contact_name")
    public void setContactName(String contactName)
    {
        this.contactName = contactName;
    }

    @JsonProperty("salutation")
    public String getSalutation()
    {
        return salutation;
    }

    @JsonProperty("salutation")
    public void setSalutation(String salutation)
    {
        this.salutation = salutation;
    }

    @JsonProperty("phone_number")
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    @JsonProperty("phone_number")
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    @JsonProperty("fax")
    public String getFax()
    {
        return fax;
    }

    @JsonProperty("fax")
    public void setFax(String fax)
    {
        this.fax = fax;
    }

    @JsonProperty("email")
    public String getEmail()
    {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email)
    {
        this.email = email;
    }

    @JsonProperty("website")
    public String getWebsite()
    {
        return website;
    }

    @JsonProperty("website")
    public void setWebsite(String website)
    {
        this.website = website;
    }

    @JsonProperty("row_version")
    public String getRowVersion()
    {
        return rowVersion;
    }

    @JsonProperty("row_version")
    public void setRowVersion(String rowVersion)
    {
        this.rowVersion = rowVersion;
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
