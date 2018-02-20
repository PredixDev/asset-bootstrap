
package com.ge.predix.solsvc.bootstrap.ams.dto;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Tag
 * <p>
 * Tag - meta model tag
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "uri",
    "name",
    "description",
    "dataType",
    "tagType",
    "uom",
    "defaultTag"
})
public class Tag {

    /**
     * uniquely identify Tag
     * 
     */
    @JsonProperty("uri")
    private String uri;
    /**
     * Name of the Tag
     * (Required)
     * 
     */
    @JsonProperty("name")
    private String name;
    /**
     * Description for the domain object
     * 
     */
    @JsonProperty("description")
    private String description;
    /**
     * Data Type, enumeration value - string, number, date, boolean
     * (Required)
     * 
     */
    @JsonProperty("dataType")
    private String dataType;
    /**
     * Tag Type, enumeration value - Continuous, Gauge, Characteristic
     * (Required)
     * 
     */
    @JsonProperty("tagType")
    private String tagType;
    /**
     * Unit of measure
     * 
     */
    @JsonProperty("uom")
    private String uom;
    /**
     * Array of Tags
     * 
     */
    @JsonProperty("defaultTag")
    private List<String> defaultTag = new ArrayList<String>();

    /**
     * uniquely identify Tag
     * @return -
     * 
     */
    
    @JsonProperty("uri")
    public String getUri() {
        return this.uri;
    }

    /**
     * uniquely identify Tag
     * 
     */
    
    @JsonProperty("uri")
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * Name of the Tag
     * (Required)
     * 
     */
    
    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    /**
     * Name of the Tag
     * (Required)
     * 
     */
    
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Description for the domain object
     * 
     */
    
    @JsonProperty("description")
    public String getDescription() {
        return this.description;
    }

    /**
     * Description for the domain object
     * 
     */
    
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Data Type, enumeration value - string, number, date, boolean
     * (Required)
     * 
     */
    
    @JsonProperty("dataType")
    public String getDataType() {
        return this.dataType;
    }

    /**
     * Data Type, enumeration value - string, number, date, boolean
     * (Required)
     * 
     */
    
    @JsonProperty("dataType")
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * Tag Type, enumeration value - Continuous, Gauge, Characteristic
     * (Required)
     * 
     */
    
    @JsonProperty("tagType")
    public String getTagType() {
        return this.tagType;
    }

    /**
     * Tag Type, enumeration value - Continuous, Gauge, Characteristic
     * (Required)
     * 
     */
    
    @JsonProperty("tagType")
    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    /**
     * Unit of measure
     * @return -
     * 
     */
    
    @JsonProperty("uom")
    public String getUom() {
        return this.uom;
    }

    /**
     * Unit of measure
     * 
     */
    
    @JsonProperty("uom")
    public void setUom(String uom) {
        this.uom = uom;
    }

    /**
     * Array of Tags
     * @return -
     * 
     */
    
    @JsonProperty("defaultTag")
    public List<String> getDefaultTag() {
        return this.defaultTag;
    }

    /**
     * Array of Tags
     * @param defaultTag  -
     * 
     */
    
    @JsonProperty("defaultTag")
    public void setDefaultTag(List<String> defaultTag) {
        this.defaultTag = defaultTag;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

}
