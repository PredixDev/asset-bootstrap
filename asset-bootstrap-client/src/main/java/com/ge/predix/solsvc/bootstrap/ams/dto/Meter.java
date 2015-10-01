
package com.ge.predix.solsvc.bootstrap.ams.dto;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Meter
 * <p>
 * Meter - meta model meter
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "uri",
    "name",
    "description",
    "dataType",
    "meterType",
    "uom",
    "tags"
})
public class Meter {

    /**
     * uniquely identify Meter
     * 
     */
    @JsonProperty("uri")
    private String uri;
    /**
     * Name of the Meter
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
     * Meter Type, enumeration value - Continuous, Gauge, Characteristic
     * (Required)
     * 
     */
    @JsonProperty("meterType")
    private String meterType;
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
    @JsonProperty("tags")
    private List<String> tags = new ArrayList<String>();

    /**
     * uniquely identify Meter
     * 
     */
    
    @JsonProperty("uri")
    public String getUri() {
        return this.uri;
    }

    /**
     * uniquely identify Meter
     * 
     */
    
    @JsonProperty("uri")
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * Name of the Meter
     * (Required)
     * 
     */
    
    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    /**
     * Name of the Meter
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
     * Meter Type, enumeration value - Continuous, Gauge, Characteristic
     * (Required)
     * 
     */
    
    @JsonProperty("meterType")
    public String getMeterType() {
        return this.meterType;
    }

    /**
     * Meter Type, enumeration value - Continuous, Gauge, Characteristic
     * (Required)
     * 
     */
    
    @JsonProperty("meterType")
    public void setMeterType(String meterType) {
        this.meterType = meterType;
    }

    /**
     * Unit of measure
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
     * 
     */
    
    @JsonProperty("tags")
    public List<String> getTags() {
        return this.tags;
    }

    /**
     * Array of Tags
     * 
     */
    
    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
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
