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
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "interaction_count",
    "owner_id",
    "image",
    "description",
    "vat_number",
    "currency",
    "status",
    "fax",
    "annual_revenue",
    "number_of_employees",
    "industry",
    "ownership",
    "sales_tax_number",
    "payee_number",
    "abn_or_tfn",
    "abn_branch",
    "acn",
    "first_name",
    "last_name",
    "parent_id",
    "bank_accounts",
    "websites",
    "addresses",
    "social_links",
    "phone_numbers",
    "emails",
    "row_type",
    "custom_fields",
    "tags",
    "read_only",
    "last_activity_at",
    "deleted",
    "salutation",
    "birthday",
    "updated_by",
    "created_by",
    "updated_at",
    "created_at"
})
@Generated("jsonschema2pojo")
public class Datum
{
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("interaction_count")
    private Integer interactionCount;
    @JsonProperty("owner_id")
    private String ownerId;
    @JsonProperty("image")
    private String image;
    @JsonProperty("description")
    private String description;
    @JsonProperty("vat_number")
    private String vatNumber;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("status")
    private String status;
    @JsonProperty("fax")
    private String fax;
    @JsonProperty("annual_revenue")
    private String annualRevenue;
    @JsonProperty("number_of_employees")
    private String numberOfEmployees;
    @JsonProperty("industry")
    private String industry;
    @JsonProperty("ownership")
    private String ownership;
    @JsonProperty("sales_tax_number")
    private String salesTaxNumber;
    @JsonProperty("payee_number")
    private String payeeNumber;
    @JsonProperty("abn_or_tfn")
    private String abnOrTfn;
    @JsonProperty("abn_branch")
    private String abnBranch;
    @JsonProperty("acn")
    private String acn;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("parent_id")
    private String parentId;
    @JsonProperty("bank_accounts")
    private List<BankAccount> bankAccounts;
    @JsonProperty("websites")
    private List<Website> websites;
    @JsonProperty("addresses")
    private List<Address> addresses;
    @JsonProperty("social_links")
    private List<SocialLink> socialLinks;
    @JsonProperty("phone_numbers")
    private List<PhoneNumber> phoneNumbers;
    @JsonProperty("emails")
    private List<Email> emails;
    @JsonProperty("row_type")
    private RowType rowType;
    @JsonProperty("custom_fields")
    private List<CustomField> customFields;
    @JsonProperty("tags")
    private List<String> tags;
    @JsonProperty("read_only")
    private Boolean readOnly;
    @JsonProperty("last_activity_at")
    private String lastActivityAt;
    @JsonProperty("deleted")
    private Boolean deleted;
    @JsonProperty("salutation")
    private String salutation;
    @JsonProperty("birthday")
    private String birthday;
    @JsonProperty("updated_by")
    private String updatedBy;
    @JsonProperty("created_by")
    private String createdBy;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("created_at")
    private String createdAt;
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

    @JsonProperty("interaction_count")
    public Integer getInteractionCount()
    {
        return interactionCount;
    }

    @JsonProperty("interaction_count")
    public void setInteractionCount(Integer interactionCount)
    {
        this.interactionCount = interactionCount;
    }

    @JsonProperty("owner_id")
    public String getOwnerId()
    {
        return ownerId;
    }

    @JsonProperty("owner_id")
    public void setOwnerId(String ownerId)
    {
        this.ownerId = ownerId;
    }

    @JsonProperty("image")
    public String getImage()
    {
        return image;
    }

    @JsonProperty("image")
    public void setImage(String image)
    {
        this.image = image;
    }

    @JsonProperty("description")
    public String getDescription()
    {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description)
    {
        this.description = description;
    }

    @JsonProperty("vat_number")
    public String getVatNumber()
    {
        return vatNumber;
    }

    @JsonProperty("vat_number")
    public void setVatNumber(String vatNumber)
    {
        this.vatNumber = vatNumber;
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

    @JsonProperty("status")
    public String getStatus()
    {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status)
    {
        this.status = status;
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

    @JsonProperty("annual_revenue")
    public String getAnnualRevenue()
    {
        return annualRevenue;
    }

    @JsonProperty("annual_revenue")
    public void setAnnualRevenue(String annualRevenue)
    {
        this.annualRevenue = annualRevenue;
    }

    @JsonProperty("number_of_employees")
    public String getNumberOfEmployees()
    {
        return numberOfEmployees;
    }

    @JsonProperty("number_of_employees")
    public void setNumberOfEmployees(String numberOfEmployees)
    {
        this.numberOfEmployees = numberOfEmployees;
    }

    @JsonProperty("industry")
    public String getIndustry()
    {
        return industry;
    }

    @JsonProperty("industry")
    public void setIndustry(String industry)
    {
        this.industry = industry;
    }

    @JsonProperty("ownership")
    public String getOwnership()
    {
        return ownership;
    }

    @JsonProperty("ownership")
    public void setOwnership(String ownership)
    {
        this.ownership = ownership;
    }

    @JsonProperty("sales_tax_number")
    public String getSalesTaxNumber()
    {
        return salesTaxNumber;
    }

    @JsonProperty("sales_tax_number")
    public void setSalesTaxNumber(String salesTaxNumber)
    {
        this.salesTaxNumber = salesTaxNumber;
    }

    @JsonProperty("payee_number")
    public String getPayeeNumber()
    {
        return payeeNumber;
    }

    @JsonProperty("payee_number")
    public void setPayeeNumber(String payeeNumber)
    {
        this.payeeNumber = payeeNumber;
    }

    @JsonProperty("abn_or_tfn")
    public String getAbnOrTfn()
    {
        return abnOrTfn;
    }

    @JsonProperty("abn_or_tfn")
    public void setAbnOrTfn(String abnOrTfn)
    {
        this.abnOrTfn = abnOrTfn;
    }

    @JsonProperty("abn_branch")
    public String getAbnBranch()
    {
        return abnBranch;
    }

    @JsonProperty("abn_branch")
    public void setAbnBranch(String abnBranch)
    {
        this.abnBranch = abnBranch;
    }

    @JsonProperty("acn")
    public String getAcn()
    {
        return acn;
    }

    @JsonProperty("acn")
    public void setAcn(String acn)
    {
        this.acn = acn;
    }

    @JsonProperty("first_name")
    public String getFirstName()
    {
        return firstName;
    }

    @JsonProperty("first_name")
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    @JsonProperty("last_name")
    public String getLastName()
    {
        return lastName;
    }

    @JsonProperty("last_name")
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    @JsonProperty("parent_id")
    public String getParentId()
    {
        return parentId;
    }

    @JsonProperty("parent_id")
    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }

    @JsonProperty("bank_accounts")
    public List<BankAccount> getBankAccounts()
    {
        return bankAccounts;
    }

    @JsonProperty("bank_accounts")
    public void setBankAccounts(List<BankAccount> bankAccounts)
    {
        this.bankAccounts = bankAccounts;
    }

    @JsonProperty("websites")
    public List<Website> getWebsites()
    {
        return websites;
    }

    @JsonProperty("websites")
    public void setWebsites(List<Website> websites)
    {
        this.websites = websites;
    }

    @JsonProperty("addresses")
    public List<Address> getAddresses()
    {
        return addresses;
    }

    @JsonProperty("addresses")
    public void setAddresses(List<Address> addresses)
    {
        this.addresses = addresses;
    }

    @JsonProperty("social_links")
    public List<SocialLink> getSocialLinks()
    {
        return socialLinks;
    }

    @JsonProperty("social_links")
    public void setSocialLinks(List<SocialLink> socialLinks)
    {
        this.socialLinks = socialLinks;
    }

    @JsonProperty("phone_numbers")
    public List<PhoneNumber> getPhoneNumbers()
    {
        return phoneNumbers;
    }

    @JsonProperty("phone_numbers")
    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers)
    {
        this.phoneNumbers = phoneNumbers;
    }

    @JsonProperty("emails")
    public List<Email> getEmails()
    {
        return emails;
    }

    @JsonProperty("emails")
    public void setEmails(List<Email> emails)
    {
        this.emails = emails;
    }

    @JsonProperty("row_type")
    public RowType getRowType()
    {
        return rowType;
    }

    @JsonProperty("row_type")
    public void setRowType(RowType rowType)
    {
        this.rowType = rowType;
    }

    @JsonProperty("custom_fields")
    public List<CustomField> getCustomFields()
    {
        return customFields;
    }

    @JsonProperty("custom_fields")
    public void setCustomFields(List<CustomField> customFields)
    {
        this.customFields = customFields;
    }

    @JsonProperty("tags")
    public List<String> getTags()
    {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<String> tags)
    {
        this.tags = tags;
    }

    @JsonProperty("read_only")
    public Boolean getReadOnly()
    {
        return readOnly;
    }

    @JsonProperty("read_only")
    public void setReadOnly(Boolean readOnly)
    {
        this.readOnly = readOnly;
    }

    @JsonProperty("last_activity_at")
    public String getLastActivityAt()
    {
        return lastActivityAt;
    }

    @JsonProperty("last_activity_at")
    public void setLastActivityAt(String lastActivityAt)
    {
        this.lastActivityAt = lastActivityAt;
    }

    @JsonProperty("deleted")
    public Boolean getDeleted()
    {
        return deleted;
    }

    @JsonProperty("deleted")
    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
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

    @JsonProperty("birthday")
    public String getBirthday()
    {
        return birthday;
    }

    @JsonProperty("birthday")
    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    @JsonProperty("updated_by")
    public String getUpdatedBy()
    {
        return updatedBy;
    }

    @JsonProperty("updated_by")
    public void setUpdatedBy(String updatedBy)
    {
        this.updatedBy = updatedBy;
    }

    @JsonProperty("created_by")
    public String getCreatedBy()
    {
        return createdBy;
    }

    @JsonProperty("created_by")
    public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt()
    {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("created_at")
    public String getCreatedAt()
    {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt)
    {
        this.createdAt = createdAt;
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
