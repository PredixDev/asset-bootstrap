
package com.ge.predix.solsvc.bootstrap.ams.dto;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * State
 * <p>
 * State - meta model state
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "uri",
    "displayName",
    "order",
    "description",
    "requireEvent",
    "obsolete",
    "deleteAsset",
    "editAsset",
    "defaultState"
})
public class State {

    /**
     * uniquely identify state
     * (Required)
     * 
     */
    @JsonProperty("uri")
    private String uri;
    /**
     * Name of the state
     * (Required)
     * 
     */
    @JsonProperty("displayName")
    private String displayName;
    /**
     * sort order
     * 
     */
    @JsonProperty("order")
    private Double order;
    /**
     * Description for the domain object
     * 
     */
    @JsonProperty("description")
    private String description;
    /**
     * Required event
     * 
     */
    @JsonProperty("requireEvent")
    private Boolean requireEvent = false;
    /**
     * Obsolete/non-obsolete State
     * (Required)
     * 
     */
    @JsonProperty("obsolete")
    private Boolean obsolete = false;
    /**
     * Delete asset
     * 
     */
    @JsonProperty("deleteAsset")
    private Boolean deleteAsset = false;
    /**
     * Edit asset
     * 
     */
    @JsonProperty("editAsset")
    private Boolean editAsset = false;
    /**
     * Default asset
     * 
     */
    @JsonProperty("defaultState")
    private Boolean defaultState = false;

    /**
     * uniquely identify state
     * (Required)
     * 
     */
    
    @JsonProperty("uri")
    public String getUri() {
        return this.uri;
    }

    /**
     * uniquely identify state
     * (Required)
     * 
     */
    
    @JsonProperty("uri")
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * Name of the state
     * (Required)
     * 
     */
    
    @JsonProperty("displayName")
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * Name of the state
     * (Required)
     * 
     */
    
    @JsonProperty("displayName")
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * sort order
     * 
     */
    
    @JsonProperty("order")
    public Double getOrder() {
        return this.order;
    }

    /**
     * sort order
     * 
     */
    
    @JsonProperty("order")
    public void setOrder(Double order) {
        this.order = order;
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
     * Required event
     * 
     */
    
    @JsonProperty("requireEvent")
    public Boolean getRequireEvent() {
        return this.requireEvent;
    }

    /**
     * Required event
     * 
     */
    
    @JsonProperty("requireEvent")
    public void setRequireEvent(Boolean requireEvent) {
        this.requireEvent = requireEvent;
    }

    /**
     * Obsolete/non-obsolete State
     * (Required)
     * 
     */
    
    @JsonProperty("obsolete")
    public Boolean getObsolete() {
        return this.obsolete;
    }

    /**
     * Obsolete/non-obsolete State
     * (Required)
     * 
     */
    
    @JsonProperty("obsolete")
    public void setObsolete(Boolean obsolete) {
        this.obsolete = obsolete;
    }

    /**
     * Delete asset
     * 
     */
    
    @JsonProperty("deleteAsset")
    public Boolean getDeleteAsset() {
        return this.deleteAsset;
    }

    /**
     * Delete asset
     * 
     */
    
    @JsonProperty("deleteAsset")
    public void setDeleteAsset(Boolean deleteAsset) {
        this.deleteAsset = deleteAsset;
    }

    /**
     * Edit asset
     * 
     */
    
    @JsonProperty("editAsset")
    public Boolean getEditAsset() {
        return this.editAsset;
    }

    /**
     * Edit asset
     * 
     */
    
    @JsonProperty("editAsset")
    public void setEditAsset(Boolean editAsset) {
        this.editAsset = editAsset;
    }

    /**
     * Default asset
     * 
     */
    
    @JsonProperty("defaultState")
    public Boolean getDefaultState() {
        return this.defaultState;
    }

    /**
     * Default asset
     * 
     */
    
    @JsonProperty("defaultState")
    public void setDefaultState(Boolean defaultState) {
        this.defaultState = defaultState;
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
