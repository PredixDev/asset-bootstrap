
package com.ge.predix.solsvc.bootstrap.ams.dto;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Name
 * <p>
 * name and uri pair for type ahead (auto-complete) function for UI front-end
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "name",
    "uri"
})
public class Name {

    /**
     * Name of the meta model object
     * (Required)
     * 
     */
    @JsonProperty("name")
    private String name;
    /**
     * uri which can uniquely identify meta model object
     * (Required)
     * 
     */
    @JsonProperty("uri")
    private String uri;

    /**
     * Name of the meta model object
     * (Required)
     * 
     */
    
    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    /**
     * Name of the meta model object
     * (Required)
     * 
     */
    
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * uri which can uniquely identify meta model object
     * (Required)
     * 
     */
    
    @JsonProperty("uri")
    public String getUri() {
        return this.uri;
    }

    /**
     * uri which can uniquely identify meta model object
     * (Required)
     * 
     */
    
    @JsonProperty("uri")
    public void setUri(String uri) {
        this.uri = uri;
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
