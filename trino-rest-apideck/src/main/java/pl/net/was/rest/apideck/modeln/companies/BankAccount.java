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
    "account_number",
    "account_name",
    "account_type",
    "iban",
    "bic",
    "bsb_number",
    "branch_identifier",
    "bank_code",
    "currency"
})
@Generated("jsonschema2pojo")
public class BankAccount
{
    @JsonProperty("account_number")
    private String accountNumber;
    @JsonProperty("account_name")
    private String accountName;
    @JsonProperty("account_type")
    private String accountType;
    @JsonProperty("iban")
    private String iban;
    @JsonProperty("bic")
    private String bic;
    @JsonProperty("bsb_number")
    private String bsbNumber;
    @JsonProperty("branch_identifier")
    private String branchIdentifier;
    @JsonProperty("bank_code")
    private String bankCode;
    @JsonProperty("currency")
    private String currency;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("account_number")
    public String getAccountNumber()
    {
        return accountNumber;
    }

    @JsonProperty("account_number")
    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    @JsonProperty("account_name")
    public String getAccountName()
    {
        return accountName;
    }

    @JsonProperty("account_name")
    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }

    @JsonProperty("account_type")
    public String getAccountType()
    {
        return accountType;
    }

    @JsonProperty("account_type")
    public void setAccountType(String accountType)
    {
        this.accountType = accountType;
    }

    @JsonProperty("iban")
    public String getIban()
    {
        return iban;
    }

    @JsonProperty("iban")
    public void setIban(String iban)
    {
        this.iban = iban;
    }

    @JsonProperty("bic")
    public String getBic()
    {
        return bic;
    }

    @JsonProperty("bic")
    public void setBic(String bic)
    {
        this.bic = bic;
    }

    @JsonProperty("bsb_number")
    public String getBsbNumber()
    {
        return bsbNumber;
    }

    @JsonProperty("bsb_number")
    public void setBsbNumber(String bsbNumber)
    {
        this.bsbNumber = bsbNumber;
    }

    @JsonProperty("branch_identifier")
    public String getBranchIdentifier()
    {
        return branchIdentifier;
    }

    @JsonProperty("branch_identifier")
    public void setBranchIdentifier(String branchIdentifier)
    {
        this.branchIdentifier = branchIdentifier;
    }

    @JsonProperty("bank_code")
    public String getBankCode()
    {
        return bankCode;
    }

    @JsonProperty("bank_code")
    public void setBankCode(String bankCode)
    {
        this.bankCode = bankCode;
    }

    @JsonProperty("currency")
    public String getCurrency()
    {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency)
    {
        this.currency = currency;
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
