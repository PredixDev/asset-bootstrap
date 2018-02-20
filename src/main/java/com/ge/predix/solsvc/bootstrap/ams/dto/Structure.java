
package com.ge.predix.solsvc.bootstrap.ams.dto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Structure
 * <p>
 * Attribute used by multiple domain objects
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "specification",
    "alternate",
    "position",
    "label",
    "quantity",
    "structure",
    "safetyCritical"
})
public class Structure {

    /**
     * Type of Structure. This may be Part, Classification or Template
     * 
     */
    @JsonProperty("specification")
    private java.lang.String specification;
    /**
     * alternate part numbers
     * 
     */
    @JsonProperty("alternate")
    private List<java.lang.String> alternate = new ArrayList<java.lang.String>();
    /**
     * Position for the specification
     * 
     */
    @JsonProperty("position")
    private java.lang.String position;
    /**
     * label for the specification
     * 
     */
    @JsonProperty("label")
    private java.lang.String label;
    /**
     * quantity used for non serialized part
     * 
     */
    @JsonProperty("quantity")
    private Double quantity;
    /**
     * Map. key is structure name and value is Structure.
     * 
     */
    @JsonProperty("structure")
    private LinkedHashMap<String,  Structure> structure;
    /**
     * safety critical flag
     * 
     */
    @JsonProperty("safetyCritical")
    private Boolean safetyCritical = false;

    /**
     * Type of Structure. This may be Part, Classification or Template
     * 
     */
    
    @JsonProperty("specification")
    public java.lang.String getSpecification() {
        return this.specification;
    }

    /**
     * Type of Structure. This may be Part, Classification or Template
     * 
     */
    
    @JsonProperty("specification")
    public void setSpecification(java.lang.String specification) {
        this.specification = specification;
    }

    /**
     * alternate part numbers
     * 
     */
    
    @JsonProperty("alternate")
    public List<java.lang.String> getAlternate() {
        return this.alternate;
    }

    /**
     * alternate part numbers
     * 
     */
    
    @JsonProperty("alternate")
    public void setAlternate(List<java.lang.String> alternate) {
        this.alternate = alternate;
    }

    /**
     * Position for the specification
     * 
     */
    
    @JsonProperty("position")
    public java.lang.String getPosition() {
        return this.position;
    }

    /**
     * Position for the specification
     * 
     */
    
    @JsonProperty("position")
    public void setPosition(java.lang.String position) {
        this.position = position;
    }

    /**
     * label for the specification
     * 
     */
    
    @JsonProperty("label")
    public java.lang.String getLabel() {
        return this.label;
    }

    /**
     * label for the specification
     * 
     */
    
    @JsonProperty("label")
    public void setLabel(java.lang.String label) {
        this.label = label;
    }

    /**
     * quantity used for non serialized part
     * 
     */
    
    @JsonProperty("quantity")
    public Double getQuantity() {
        return this.quantity;
    }

    /**
     * quantity used for non serialized part
     * 
     */
    
    @JsonProperty("quantity")
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    /**
     * Map. key is structure name and value is Structure.
     * 
     */
    
    @JsonProperty("structure")
    public LinkedHashMap<String,  Structure> getStructure() {
        return this.structure;
    }

    /**
     * Map. key is structure name and value is Structure.
     * 
     */
    
    @JsonProperty("structure")
    public void setStructure(LinkedHashMap<String,  Structure> structure) {
        this.structure = structure;
    }

    /**
     * safety critical flag
     * 
     */
    
    @JsonProperty("safetyCritical")
    public Boolean getSafetyCritical() {
        return this.safetyCritical;
    }

    /**
     * safety critical flag
     * 
     */
    
    @JsonProperty("safetyCritical")
    public void setSafetyCritical(Boolean safetyCritical) {
        this.safetyCritical = safetyCritical;
    }

    @Override
    public java.lang.String toString() {
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
