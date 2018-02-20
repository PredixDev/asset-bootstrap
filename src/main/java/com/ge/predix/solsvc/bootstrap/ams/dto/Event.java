
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
 * Event
 * <p>
 * Event which can be attached to asset
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "uri",
    "eventId",
    "eventType",
    "eventTypeUri",
    "eventTypeName",
    "comment",
    "effectiveDate",
    "internal",
    "tags"
})
public class Event {

    /**
     * uniquely identify Event
     * 
     */
    @JsonProperty("uri")
    private String uri;
    /**
     * Id of the Event
     * (Required)
     * 
     */
    @JsonProperty("eventId")
    private String eventId;
    /**
     * uri for the event type
     * 
     */
    @JsonProperty("eventType")
    private String eventType;
    /**
     * uri for the event type for UI list only
     * 
     */
    @JsonProperty("eventTypeUri")
    private String eventTypeUri;
    /**
     * name for the event type for UI list only
     * 
     */
    @JsonProperty("eventTypeName")
    private String eventTypeName;
    /**
     * Comment for the event
     * 
     */
    @JsonProperty("comment")
    private String comment;
    /**
     * effective date for the event
     * (Required)
     * 
     */
    @JsonProperty("effectiveDate")
    private String effectiveDate;
    /**
     * boolean value to set event internal or external
     * 
     */
    @JsonProperty("internal")
    private Boolean internal;
    /**
     * Array of Tags
     * 
     */
    @JsonProperty("tags")
    private List<String> tags = new ArrayList<String>();

    /**
     * uniquely identify Event
     * 
     */
    @JsonProperty("uri")
    
    public String getUri() {
        return this.uri;
    }

    /**
     * uniquely identify Event
     * 
     */
    @JsonProperty("uri")
    
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * Id of the Event
     * (Required)
     * 
     */
    @JsonProperty("eventId")
    
    public String getEventId() {
        return this.eventId;
    }

    /**
     * Id of the Event
     * (Required)
     * 
     */
    @JsonProperty("eventId")
    
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    /**
     * uri for the event type
     * 
     */
    @JsonProperty("eventType")
    
    public String getEventType() {
        return this.eventType;
    }

    /**
     * uri for the event type
     * 
     */
    @JsonProperty("eventType")
    
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * uri for the event type for UI list only
     * 
     */
    @JsonProperty("eventTypeUri")
    
    public String getEventTypeUri() {
        return this.eventTypeUri;
    }

    /**
     * uri for the event type for UI list only
     * 
     */
    @JsonProperty("eventTypeUri")
    
    public void setEventTypeUri(String eventTypeUri) {
        this.eventTypeUri = eventTypeUri;
    }

    /**
     * name for the event type for UI list only
     * 
     */
    
    @JsonProperty("eventTypeName")
    public String getEventTypeName() {
        return this.eventTypeName;
    }

    /**
     * name for the event type for UI list only
     * 
     */
    
    @JsonProperty("eventTypeName")
    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

    /**
     * Comment for the event
     * 
     */
    @JsonProperty("comment")
    
    public String getComment() {
        return this.comment;
    }

    /**
     * Comment for the event
     * 
     */
    
    @JsonProperty("comment")
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * effective date for the event
     * (Required)
     * 
     */
    
    @JsonProperty("effectiveDate")
    public String getEffectiveDate() {
        return this.effectiveDate;
    }

    /**
     * effective date for the event
     * (Required)
     * 
     */
    
    @JsonProperty("effectiveDate")
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * boolean value to set event internal or external
     * 
     */
    
    @JsonProperty("internal")
    public Boolean getInternal() {
        return this.internal;
    }

    /**
     * boolean value to set event internal or external
     * 
     */
    
    @JsonProperty("internal")
    public void setInternal(Boolean internal) {
        this.internal = internal;
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
