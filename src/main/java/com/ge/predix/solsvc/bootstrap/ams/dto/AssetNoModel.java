
package com.ge.predix.solsvc.bootstrap.ams.dto;

import java.util.LinkedHashMap;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.ge.predix.entity.asset.AssetTag;


/**
 * Asset
 * <p>
 * Asset which is the instance of Type
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "uri",
    "description",
    "obsolete",
    "empty",
    "assetId",
    "structureid",
    "template4structure",
    "position",
    "sortorder",
    "parent",
    "specification",
    "state",
    "propagateState",
    "currentEvent",
    "group",
    "attributes",
    "nonserialized",
    "tags",
    "tags"
})
//@Deprecated
public class AssetNoModel {

    /**
     * System generated uri which can uniquely identify Asset, uri must be null or omitted when send JSon request for creating new Asset, uri must be sent when send JSon request for updating  new Asset, uri is always returned for creating, updating and retrieving Asset
     * 
     */
    @JsonProperty("uri")
    private java.lang.String uri;
    /**
     * Description for the domain object
     * 
     */
    @JsonProperty("description")
    private java.lang.String description;
    /**
     * Obsolete/non-obsolete Asset
     * (Required)
     * 
     */
    @JsonProperty("obsolete")
    private Boolean obsolete = false;
    /**
     * Empty/Real Asset
     * (Required)
     * 
     */
    @JsonProperty("empty")
    private Boolean empty = false;
    /**
     * serial number for Asset
     * 
     */
    @JsonProperty("assetId")
    private java.lang.String assetId;
    /**
     * structure name associated
     * 
     */
    @JsonProperty("structureid")
    private java.lang.String structureid;
    /**
     * connected template uri
     * 
     */
    @JsonProperty("template4structure")
    private java.lang.String template4structure;
    /**
     * connected template uri
     * 
     */
    @JsonProperty("position")
    private java.lang.String position;
    /**
     * sort order with respect to sibling assets
     * 
     */
    @JsonProperty("sortorder")
    private Double sortorder;
    /**
     * parent uri for Asset
     * 
     */
    @JsonProperty("parent")
    private java.lang.String parent;
    /**
     * Type of Asset, value is the uri of Type, e.g. /type/12345
     * 
     */
    @JsonProperty("specification")
    private java.lang.String specification;
    /**
     * State of Asset, value is the uri of State, e.g. /state/12345
     * 
     */
    @JsonProperty("state")
    private java.lang.String state;
    /**
     * Propagate State, when true the sate's flowed down to children
     * 
     */
    @JsonProperty("propagateState")
    private Boolean propagateState = false;
    /**
     * Event associated with Asset, value is the uri of Event, e.g. /event/12345
     * 
     */
    @JsonProperty("currentEvent")
    private java.lang.String currentEvent;
    /**
     * collection uri for group.Value is collection uri, e.g. /asset/uuid/group
     * 
     */
    @JsonProperty("group")
    private java.lang.String group;
    /**
     * Map. key is attribute name and value is Attribute.
     * 
     */
    @JsonProperty("attributes")
    private LinkedHashMap<String,  Attribute> attributes;
    /**
     * contains non serialized part quantities
     * 
     */
    @JsonProperty("nonserialized")
    private LinkedHashMap<String,  Object> nonserialized;
    
    /**
     * Map. key is tag key and value is AssetTag.
     * 
     */
    @JsonProperty("tags")
    private LinkedHashMap<String,  AssetTag> tags;

    /**
     * System generated uri which can uniquely identify Asset, uri must be null or omitted when send JSon request for creating new Asset, uri must be sent when send JSon request for updating  new Asset, uri is always returned for creating, updating and retrieving Asset
     * @return -
     * 
     */
    
	@JsonProperty("uri")
    public java.lang.String getUri() {
        return this.uri;
    }

    /**
     * System generated uri which can uniquely identify Asset, uri must be null or omitted when send JSon request for creating new Asset, uri must be sent when send JSon request for updating  new Asset, uri is always returned for creating, updating and retrieving Asset
     * @param uri -
     * 
     */
    
    @JsonProperty("uri")
    public void setUri(java.lang.String uri) {
        this.uri = uri;
    }

    /**
     * Description for the domain object
     * @return -
     * 
     */
    
    @JsonProperty("description")
    public java.lang.String getDescription() {
        return this.description;
    }

    /**
     * Description for the domain object
     * @param description -
     * 
     */
    
    @JsonProperty("description")
    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    /**
     * Obsolete/non-obsolete Asset
     * (Required)
     * @return -
     * 
     */
    
    @JsonProperty("obsolete")
    public Boolean getObsolete() {
        return this.obsolete;
    }

    /**
     * Obsolete/non-obsolete Asset
     * (Required)
     * @param obsolete -
     * 
     */
    
    @JsonProperty("obsolete")
    public void setObsolete(Boolean obsolete) {
        this.obsolete = obsolete;
    }

    /**
     * Empty/Real Asset
     * (Required)
     * @return -
     * 
     */
    
    @JsonProperty("empty")
    public Boolean getEmpty() {
        return this.empty;
    }

    /**
     * Empty/Real Asset
     * (Required)
     * 
     */
    
    @JsonProperty("empty")
    public void setEmpty(Boolean empty) {
        this.empty = empty;
    }

    /**
     * serial number for Asset
     * 
     */
    
    @JsonProperty("assetId")
    public java.lang.String getAssetId() {
        return this.assetId;
    }

    /**
     * serial number for Asset
     * 
     */
    
    @JsonProperty("assetId")
    public void setAssetId(java.lang.String assetId) {
        this.assetId = assetId;
    }

    /**
     * structure name associated
     * 
     */
    
	@JsonProperty("structureid")
    public java.lang.String getStructureid() {
        return this.structureid;
    }

    /**
     * structure name associated
     * 
     */
    
    @JsonProperty("structureid")
    public void setStructureid(java.lang.String structureid) {
        this.structureid = structureid;
    }

    /**
     * connected template uri
     * 
     */
    
    @JsonProperty("template4structure")
    public java.lang.String getTemplate4structure() {
        return this.template4structure;
    }

    /**
     * connected template uri
     * 
     */
    
    @JsonProperty("template4structure")
    public void setTemplate4structure(java.lang.String template4structure) {
        this.template4structure = template4structure;
    }

    /**
     * connected template uri
     * 
     */
    
    @JsonProperty("position")
    public java.lang.String getPosition() {
        return this.position;
    }

    /**
     * connected template uri
     * 
     */
    
    @JsonProperty("position")
    public void setPosition(java.lang.String position) {
        this.position = position;
    }

    /**
     * sort order with respect to sibling assets
     * 
     */
    
    @JsonProperty("sortorder")
    public Double getSortorder() {
        return this.sortorder;
    }

    /**
     * sort order with respect to sibling assets
     * 
     */
    
    @JsonProperty("sortorder")
    public void setSortorder(Double sortorder) {
        this.sortorder = sortorder;
    }

    /**
     * parent uri for Asset
     * 
     */
    
    @JsonProperty("parent")
    public java.lang.String getParent() {
        return this.parent;
    }

    /**
     * parent uri for Asset
     * 
     */
    
    @JsonProperty("parent")
    public void setParent(java.lang.String parent) {
        this.parent = parent;
    }

    /**
     * Type of Asset, value is the uri of Type, e.g. /type/12345
     * 
     */
    
    @JsonProperty("specification")
    public java.lang.String getSpecification() {
        return this.specification;
    }

    /**
     * Type of Asset, value is the uri of Type, e.g. /type/12345
     * 
     */
    
    @JsonProperty("specification")
    public void setSpecification(java.lang.String specification) {
        this.specification = specification;
    }

    /**
     * State of Asset, value is the uri of State, e.g. /state/12345
     * 
     */
    
    @JsonProperty("state")
    public java.lang.String getState() {
        return this.state;
    }

    /**
     * State of Asset, value is the uri of State, e.g. /state/12345
     * @param state s
     * 
     */
    @JsonProperty("state")
    public void setState(java.lang.String state) {
        this.state = state;
    }

    /**
     * Propagate State, when true the sate's flowed down to children
     * 
     */
    
    @JsonProperty("propagateState")
    public Boolean getPropagateState() {
        return this.propagateState;
    }

    /**
     * Propagate State, when true the sate's flowed down to children
     * @param propagateState s
     * 
     */
    @JsonProperty("propagateState")
    public void setPropagateState(Boolean propagateState) {
        this.propagateState = propagateState;
    }

    /**
     * Event associated with Asset, value is the uri of Event, e.g. /event/12345
     * 
     */
    
    @JsonProperty("currentEvent")
    public java.lang.String getCurrentEvent() {
        return this.currentEvent;
    }

    /**
     * Event associated with Asset, value is the uri of Event, e.g. /event/12345
     * 
     */
    
    @JsonProperty("currentEvent")
    public void setCurrentEvent(java.lang.String currentEvent) {
        this.currentEvent = currentEvent;
    }

    /**
     * collection uri for group.Value is collection uri, e.g. /asset/uuid/group
     * 
     */
    
    @JsonProperty("group")
    public java.lang.String getGroup() {
        return this.group;
    }

    /**
     * collection uri for group.Value is collection uri, e.g. /asset/uuid/group
     * 
     */
    
    @JsonProperty("group")
    public void setGroup(java.lang.String group) {
        this.group = group;
    }

    /**
     * Map. key is attribute name and value is Attribute.
     * 
     */
    
    @JsonProperty("attributes")
    public LinkedHashMap<String,  Attribute> getAttributes() {
        return this.attributes;
    }

    /**
     * Map. key is attribute name and value is Attribute.
     * 
     */
    
    @JsonProperty("attributes")
    public void setAttributes(LinkedHashMap<String,  Attribute> attributes) {
        this.attributes = attributes;
    }

    /**
     * contains non serialized part quantities
     * 
     */
    
    @JsonProperty("nonserialized")
    public LinkedHashMap<String,  Object> getNonserialized() {
        return this.nonserialized;
    }

    /**
     * contains non serialized part quantities
     * 
     */
    
    @JsonProperty("nonserialized")
    public void setNonserialized(LinkedHashMap<String,  Object> nonserialized) {
        this.nonserialized = nonserialized;
    }
    

    /**
     * Map. key is tag key and value is AssetTag.
     * 
     */
    
    @JsonProperty("tags")
    public LinkedHashMap<String,  AssetTag> getTags() {
        return this.tags;
    }

    /**
     * Map. key is tag key and value is AssetTag.
     * 
     */
    
    @JsonProperty("tags")
    public void setTags(LinkedHashMap<String,  AssetTag> tags) {
        this.tags = tags;
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
