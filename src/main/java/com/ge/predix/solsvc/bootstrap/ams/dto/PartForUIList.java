
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
    "classificationName",
    "classificationUri",
    "model",
    "obsolete",
    "serialized",
    "alternate",
    "attributes",
    "tags"
})
public class PartForUIList {

    /**
     * System generated uri which can uniquely identify Part, uri must be null or omitted when send JSon request for creating new Part, uri must be sent when send JSon request for updating  new Part, uri is always returned for creating, updating and retrieving Part
     * 
     */
    @JsonProperty("uri")
    private String uri;
    /**
     * Name of the Part
     * 
     */
    @JsonProperty("name")
    private String name;
    /**
     * Description for the domain object
     * 
     */
    @JsonProperty("description")
    private String description;
    /**
     * Classification Name of the Part
     * 
     */
    @JsonProperty("classificationName")
    private String classificationName;
    /**
     * Classification Name of the Part
     * 
     */
    @JsonProperty("classificationUri")
    private String classificationUri;
    /**
     * model of part numbers
     * 
     */
    @JsonProperty("model")
    private List<String> model = new ArrayList<String>();
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
    private List<String> alternate = new ArrayList<String>();
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
    private List<String> tags = new ArrayList<String>();

    /**
     * System generated uri which can uniquely identify Part, uri must be null or omitted when send JSon request for creating new Part, uri must be sent when send JSon request for updating  new Part, uri is always returned for creating, updating and retrieving Part
     * @return -
     * 
     */
	@JsonProperty("uri")
    public String getUri() {
        return this.uri;
    }

    /**
     * System generated uri which can uniquely identify Part, uri must be null or omitted when send JSon request for creating new Part, uri must be sent when send JSon request for updating  new Part, uri is always returned for creating, updating and retrieving Part
     * @param uri -
     * 
     */
    @JsonProperty("uri")
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * Name of the Part
     * @return -
     * 
     */
    @SuppressWarnings({ ,  })
    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    /**
     * Name of the Part
     * @param name -
     * 
     */
    
	@JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Description for the domain object
     * @return -
     * 
     */
    @SuppressWarnings({ ,  })
    @JsonProperty("description")
    public String getDescription() {
        return this.description;
    }

    /**
     * Description for the domain object
     * @param description -
     * 
     */
    
	@JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Classification Name of the Part
     * @return -
     * 
     */
    @SuppressWarnings({ ,  })
    @JsonProperty("classificationName")
    public String getClassificationName() {
        return this.classificationName;
    }

    /**
     * Classification Name of the Part
     * @param classificationName -
     * 
     */
	@JsonProperty("classificationName")
    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }

    /**
     * Classification Name of the Part
     * @return -
     * 
     */
    @SuppressWarnings({ ,  })
    @JsonProperty("classificationUri")
    public String getClassificationUri() {
        return this.classificationUri;
    }

    /**
     * Classification Name of the Part
     * @param classificationUri -
     * 
     */
    
	@JsonProperty("classificationUri")
    public void setClassificationUri(String classificationUri) {
        this.classificationUri = classificationUri;
    }

    /**
     * model of part numbers
     * @return -
     * 
     */
    @SuppressWarnings({ ,  })
    @JsonProperty("model")
    public List<String> getModel() {
        return this.model;
    }

    /**
     * model of part numbers
     * @param model model
     * 
     */
    @JsonProperty("model")
    public void setModel(List<String> model) {
        this.model = model;
    }

    /**
     * Obsolete/non-obsolete part number
     * (Required)
     * @return Boolean
     * 
     */
    @JsonProperty("obsolete")
    public Boolean getObsolete() {
        return this.obsolete;
    }

    /**
     * Obsolete/non-obsolete part number
     * (Required)
     * @param obsolete Boolean
     * 
     */
    @JsonProperty("obsolete")
    public void setObsolete(Boolean obsolete) {
        this.obsolete = obsolete;
    }

    /**
     * Serialized/non-serialized part number
     * (Required)
     * @return Boolean
     * 
     */
    @JsonProperty("serialized")
    public Boolean getSerialized() {
        return this.serialized;
    }

    /**
     * Serialized/non-serialized part number
     * (Required)
     * @param serialized Boolean serialized
     * 
     */
    @JsonProperty("serialized")
    public void setSerialized(Boolean serialized) {
        this.serialized = serialized;
    }

    /**
     * alternate part numbers
     * @return -
     * 
     */
    
	@JsonProperty("alternate")
    public List<String> getAlternate() {
        return this.alternate;
    }

    /**
     * alternate part numbers
     * @param alternate -
     * 
     */
    
    @JsonProperty("alternate")
    public void setAlternate(List<String> alternate) {
        this.alternate = alternate;
    }

    /**
     * Map. key is attribute name and value is Attribute.
     * @return -
     * 
     */
    
    @JsonProperty("attributes")
    public LinkedHashMap<String,  Attribute> getAttributes() {
        return this.attributes;
    }

    /**
     * Map. key is attribute name and value is Attribute.
     * @param attributes -
     * 
     */
    
    @JsonProperty("attributes")
    public void setAttributes(LinkedHashMap<String,  Attribute> attributes) {
        this.attributes = attributes;
    }

    /**
     * Array of Tags
     * @return -
     * 
     */
    
    @JsonProperty("tags")
    public List<String> getTags() {
        return this.tags;
    }

    /**
     * Array of Tags
     * @param tags -
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
