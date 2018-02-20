
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
 * Template
 * <p>
 * Template - Asset Structure Template
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "uri",
    "name",
    "description",
    "specification",
    "obsolete",
    "safetyCritical",
    "attributes",
    "structure",
    "tags"
})
public class Template {

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
     * Description for the domain object
     * 
     */
    @JsonProperty("description")
    private java.lang.String description;
    /**
     * Part or Classification
     * 
     */
    @JsonProperty("specification")
    private java.lang.String specification;
    /**
     * Obsolete/non-obsolete template
     * 
     */
    @JsonProperty("obsolete")
    private Boolean obsolete = false;
    /**
     * safety critical flag
     * 
     */
    @JsonProperty("safetyCritical")
    private Boolean safetyCritical = false;
    /**
     * Map. key is attribute name and value is Attribute.
     * 
     */
    @JsonProperty("attributes")
    private LinkedHashMap<String,  Attribute> attributes;
    /**
     * Map. key is structure name and value is Structure.
     * 
     */
    @JsonProperty("structure")
    private LinkedHashMap<String,  Structure> structure;
    /**
     * Array of Tags
     * 
     */
    @JsonProperty("tags")
    private List<java.lang.String> tags = new ArrayList<java.lang.String>();

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
     * Part or Classification
     * 
     */
    
    @JsonProperty("specification")
    public java.lang.String getSpecification() {
        return this.specification;
    }

    /**
     * Part or Classification
     * 
     */
    
    @JsonProperty("specification")
    public void setSpecification(java.lang.String specification) {
        this.specification = specification;
    }

    /**
     * Obsolete/non-obsolete template
     * 
     */
    
    @JsonProperty("obsolete")
    public Boolean getObsolete() {
        return this.obsolete;
    }

    /**
     * Obsolete/non-obsolete template
     * 
     */
    
    @JsonProperty("obsolete")
    public void setObsolete(Boolean obsolete) {
        this.obsolete = obsolete;
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
