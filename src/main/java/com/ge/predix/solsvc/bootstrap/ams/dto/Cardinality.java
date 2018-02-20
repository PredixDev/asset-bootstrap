
package com.ge.predix.solsvc.bootstrap.ams.dto;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Cardinality of Attribute
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "min",
    "max"
})
public class Cardinality {

    /**
     * Minimal value of cardinality
     * 
     */
    @JsonProperty("min")
    private Object min;
    /**
     * Maximum value of cardinality
     * 
     */
    @JsonProperty("max")
    private Object max;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Minimal value of cardinality
     * 
     */
    
    @JsonProperty("min")
    public Object getMin() {
        return this.min;
    }

    /**
     * Minimal value of cardinality
     * 
     */
    
    @JsonProperty("min")
    public void setMin(Object min) {
        this.min = min;
    }

    /**
     * Maximum value of cardinality
     * 
     */
    
    @JsonProperty("max")
    public Object getMax() {
        return this.max;
    }

    /**
     * Maximum value of cardinality
     * 
     */
    
    @JsonProperty("max")
    public void setMax(Object max) {
        this.max = max;
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

    
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
