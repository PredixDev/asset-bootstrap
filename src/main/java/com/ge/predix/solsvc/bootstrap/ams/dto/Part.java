
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
 * Part
 * <p>
 * Part - meta model part which has attributes
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "uri",
    "name",
    "description",
    "classification",
    "obsolete",
    "serialized",
    "alternate",
    "attributes",
    "tags"
})
public class Part {

    /**
     * System generated uri which can uniquely identify Part, uri must be null or omitted when send JSon request for creating new Part, uri must be sent when send JSon request for updating  new Part, uri is always returned for creating, updating and retrieving Part
     * 
     */
    @JsonProperty("uri")
    private java.lang.String uri;
    /**
     * Name of the Part
     * 
     */
    @JsonProperty("name")
    private java.lang.String name;
    /**
     * Description for the domain object
     * 
     */
    @JsonProperty("description")
    private java.lang.String description;
    /**
     * Classification of the Part
     * 
     */
    @JsonProperty("classification")
    private java.lang.String classification;
    /**
     * Obsolete/non-obsolete part number
     * (Required)
     * 
     */
    @JsonProperty("obsolete")
    private Boolean obsolete = false;
    /**
     * Serialized/non-serialized part number
     * (Required)
     * 
     */
    @JsonProperty("serialized")
    private Boolean serialized = true;
    /**
     * alternate part numbers
     * 
     */
    @JsonProperty("alternate")
    private List<java.lang.String> alternate = new ArrayList<java.lang.String>();
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
     * System generated uri which can uniquely identify Part, uri must be null or omitted when send JSon request for creating new Part, uri must be sent when send JSon request for updating  new Part, uri is always returned for creating, updating and retrieving Part
     * 
     */
    @JsonProperty("uri")
    
    public java.lang.String getUri() {
        return this.uri;
    }

    /**
     * System generated uri which can uniquely identify Part, uri must be null or omitted when send JSon request for creating new Part, uri must be sent when send JSon request for updating  new Part, uri is always returned for creating, updating and retrieving Part
     * 
     */
    @JsonProperty("uri")  
    public void setUri(java.lang.String uri) {
        this.uri = uri;
    }

    /**
     * Name of the Part
     * 
     */
    @JsonProperty("name")
    
    public java.lang.String getName() {
        return this.name;
    }

    /**
     * Name of the Part
     * 
     */
    @JsonProperty("name")
    
    public void setName(java.lang.String name) {
        this.name = name;
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
     * Classification of the Part
     * 
     */
    @JsonProperty("classification")
    
    public java.lang.String getClassification() {
        return this.classification;
    }

    /**
     * Classification of the Part
     * 
     */
    @JsonProperty("classification")
    
    public void setClassification(java.lang.String classification) {
        this.classification = classification;
    }

    /**
     * Obsolete/non-obsolete part number
     * (Required)
     * 
     */
    @JsonProperty("obsolete")
    
    public Boolean getObsolete() {
        return this.obsolete;
    }

    /**
     * Obsolete/non-obsolete part number
     * (Required)
     * 
     */
    @JsonProperty("obsolete")
    
    public void setObsolete(Boolean obsolete) {
        this.obsolete = obsolete;
    }

    /**
     * Serialized/non-serialized part number
     * (Required)
     * 
     */
    @JsonProperty("serialized")
    
    public Boolean getSerialized() {
        return this.serialized;
    }

    /**
     * Serialized/non-serialized part number
     * (Required)
     * 
     */
    @JsonProperty("serialized")
    
    public void setSerialized(Boolean serialized) {
        this.serialized = serialized;
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
