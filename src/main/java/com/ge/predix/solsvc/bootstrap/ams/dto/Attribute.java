
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
import com.ge.predix.entity.model.Typed;


/**
 * Attribute
 * <p>
 * Attribute used by multiple domain objects
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "type",
    "enumeration",
    "unique",
    "required",
    "display",
    "entropy",
    "value",
    "uom",
    "cardinality"
})
public class Attribute extends Typed {

    /**
     * Type of Attribute
     * 
     */
    @JsonProperty("type")
    private String type;
    /**
     * Enumeration constraint values
     * 
     */
    @JsonProperty("enumeration")
    private List<Object> enumeration = new ArrayList<Object>();
    /**
     * Unique constraint
     * 
     */
    @JsonProperty("unique")
    private Boolean unique;
    /**
     * Required constraint
     * 
     */
    @JsonProperty("required")
    private Boolean required;
    /**
     * this attribute should be displayed or not
     * 
     */
    @JsonProperty("display")
    private Boolean display;
    /**
     * Read-only, entropy of Attribute
     * 
     */
    @JsonProperty("entropy")
    private Double entropy;
    /**
     * 
     */
    @JsonProperty("value")
    private List<Object> value = new ArrayList<Object>();
    /**
     * Unit of measure
     * 
     */
    @JsonProperty("uom")
    private String uom;
    /**
     * Cardinality of Attribute
     * 
     */
    @JsonProperty("cardinality")
    private Cardinality cardinality;

    /**
     * Type of Attribute
     * 
     */
    
	@JsonProperty("type")
    public String getType() {
        return this.type;
    }

    /**
     * Type of Attribute
     * 
     */
    
	@JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Enumeration constraint values
     * 
     */
    
	@JsonProperty("enumeration")
    public List<Object> getEnumeration() {
        return this.enumeration;
    }

    /**
     * Enumeration constraint values
     * 
     */
    
	@JsonProperty("enumeration")
    public void setEnumeration(List<Object> enumeration) {
        this.enumeration = enumeration;
    }

    /**
     * Unique constraint
     * 
     */
    
	@JsonProperty("unique")
    public Boolean getUnique() {
        return this.unique;
    }

    /**
     * Unique constraint
     * 
     */
    
	@JsonProperty("unique")
    public void setUnique(Boolean unique) {
        this.unique = unique;
    }

    /**
     * Required constraint
     * 
     */
    @SuppressWarnings({  })
	@JsonProperty("required")
    public Boolean getRequired() {
        return this.required;
    }

    /**
     * Required constraint
     * 
     */
    @JsonProperty("required")
    public void setRequired( Boolean required) {
        this.required = required;
    }

    /**
     * this attribute should be displayed or not
     * 
     */
    
	@JsonProperty("display")
    public Boolean getDisplay() {
        return this.display;
    }

    /**
     * this attribute should be displayed or not
     * 
     */
    @JsonProperty("display")
    public void setDisplay( Boolean display) {
        this.display = display;
    }

    /**
     * Read-only, entropy of Attribute
     * 
     */
    
	@JsonProperty("entropy")
    public Double getEntropy() {
        return this.entropy;
    }

    /**
     * Read-only, entropy of Attribute
     * 
     */
    @JsonProperty("entropy")
    public void setEntropy( Double entropy) {
        this.entropy = entropy;
    }

    /**
     * 
     */
    
	@JsonProperty("value")
    public List<Object> getValue() {
        return this.value;
    }

    /**
     * 
     */
    
	@JsonProperty("value")
    public void setValue(List<Object> value) {
        this.value = value;
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
     * Cardinality of Attribute
     * 
     */
    
	@JsonProperty("cardinality")
    public Cardinality getCardinality() {
        return this.cardinality;
    }

    /**
     * Cardinality of Attribute
     * 
     */
    
	@JsonProperty("cardinality")
    public void setCardinality(Cardinality cardinality) {
        this.cardinality = cardinality;
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
