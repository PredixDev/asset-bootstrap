
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
 * Classification
 * <p>
 * Classification - meta model type which has attributes
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "parent",
    "description",
    "uri",
    "name",
    "obsolete",
    "attributes",
    "tags"
})
public class Classification {

    /**
     * Parent Reference
     * 
     */
    @JsonProperty("parent")
    private java.lang.String parent;
    /**
     * Description for the domain object
     * 
     */
    @JsonProperty("description")
    private java.lang.String description;
    /**
     * System generated uri which can uniquely identify Type, uri must be null or omitted when send JSon request for creating new Type, uri must be sent when send JSon request for updating  new Type, uri is always returned for creating, updating and retrieving Type
     * 
     */
    @JsonProperty("uri")
    private java.lang.String uri;
    /**
     * Name of the Type
     * (Required)
     * 
     */
    @JsonProperty("name")
    private java.lang.String name;
    /**
     * Obsolete/non-obsolete Classification
     * (Required)
     * 
     */
    @JsonProperty("obsolete")
    private Boolean obsolete = false;
    /**
     * Map. key is attribute name and value is Attribute.
     * 
     */
    @JsonProperty("attributes")
    private LinkedHashMap<String,  Attribute> attributes;
    /**
     * Array of Tags
     * 
     */
    @JsonProperty("tags")
    private List<java.lang.String> tags = new ArrayList<java.lang.String>();

    /**
     * Parent Reference
     * 
     */
    
    @JsonProperty("parent")
    public java.lang.String getParent() {
        return this.parent;
    }

    /**
     * Parent Reference
     * 
     */
    
    @JsonProperty("parent")
    public void setParent(java.lang.String parent) {
        this.parent = parent;
    }

    /**
     * Description for the domain object
     * 
     */
    
    @JsonProperty("description")
    public java.lang.String getDescription() {
        return this.description;
    }

    /**
     * Description for the domain object
     * 
     */
    
    @JsonProperty("description")
    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    /**
     * System generated uri which can uniquely identify Type, uri must be null or omitted when send JSon request for creating new Type, uri must be sent when send JSon request for updating  new Type, uri is always returned for creating, updating and retrieving Type
     * 
     */
    
    @JsonProperty("uri")
    public java.lang.String getUri() {
        return this.uri;
    }

    /**
     * System generated uri which can uniquely identify Type, uri must be null or omitted when send JSon request for creating new Type, uri must be sent when send JSon request for updating  new Type, uri is always returned for creating, updating and retrieving Type
     * 
     */
    
    @JsonProperty("uri")
    public void setUri(java.lang.String uri) {
        this.uri = uri;
    }

    /**
     * Name of the Type
     * (Required)
     * 
     */
    
    @JsonProperty("name")
    public java.lang.String getName() {
        return this.name;
    }

    /**
     * Name of the Type
     * (Required)
     * 
     */
    
    @JsonProperty("name")
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * Obsolete/non-obsolete Classification
     * (Required)
     * 
     */
    
    @JsonProperty("obsolete")
    public Boolean getObsolete() {
        return this.obsolete;
    }

    /**
     * Obsolete/non-obsolete Classification
     * (Required)
     * 
     */
    
    @JsonProperty("obsolete")
    public void setObsolete(Boolean obsolete) {
        this.obsolete = obsolete;
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
     * Array of Tags
     * 
     */
    
    @JsonProperty("tags")
    public List<java.lang.String> getTags() {
        return this.tags;
    }

    /**
     * Array of Tags
     * 
     */
    
    @JsonProperty("tags")
    public void setTags(List<java.lang.String> tags) {
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
