
package com.ge.predix.solsvc.bootstrap.ams.dto;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * EventType
 * <p>
 * event type
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "uri",
    "name",
    "obsolete",
    "internal"
})
public class EventType {

    /**
     * unique identifier
     * (Required)
     * 
     */
    @JsonProperty("uri")
    private String uri;
    /**
     * Name of the event type
     * (Required)
     * 
     */
    @JsonProperty("name")
    private String name;
    /**
     * Obsolete/non-obsolete event type
     * 
     */
    @JsonProperty("obsolete")
    private Boolean obsolete;
    /**
     * Internal/external event type
     * 
     */
    @JsonProperty("internal")
    private Boolean internal;

    /**
     * unique identifier
     * (Required)
     * 
     */
    
    @JsonProperty("uri")
    public String getUri() {
        return this.uri;
    }

    /**
     * unique identifier
     * (Required)
     * 
     */
    
    @JsonProperty("uri")
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * Name of the event type
     * (Required)
     * 
     */
    
    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    /**
     * Name of the event type
     * (Required)
     * 
     */
    
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obsolete/non-obsolete event type
     * 
     */
    
    @JsonProperty("obsolete")
    public Boolean getObsolete() {
        return this.obsolete;
    }

    /**
     * Obsolete/non-obsolete event type
     * 
     */
    
    @JsonProperty("obsolete")
    public void setObsolete(Boolean obsolete) {
        this.obsolete = obsolete;
    }

    /**
     * Internal/external event type
     * 
     */
    
    @JsonProperty("internal")
    public Boolean getInternal() {
        return this.internal;
    }

    /**
     * Internal/external event type
     * 
     */
    
    @JsonProperty("internal")
    public void setInternal(Boolean internal) {
        this.internal = internal;
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
