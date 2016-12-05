
package com.ge.predix.solsvc.bootstrap.ams.dto;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Group
 * <p>
 * Group
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "parent",
    "description",
    "uri",
    "level",
    "name",
    "createdate"
})
public class Group {

    /**
     * Parent Reference
     * 
     */
    @JsonProperty("parent")
    private String parent;
    /**
     * Description for the domain object
     * 
     */
    @JsonProperty("description")
    private String description;
    /**
     * unique identifier
     * (Required)
     * 
     */
    @JsonProperty("uri")
    private String uri;
    /**
     * category
     * (Required)
     * 
     */
    @JsonProperty("level")
    private String level;
    /**
     * String value denoting the group tag
     * (Required)
     * 
     */
    @JsonProperty("name")
    private String name;
    /**
     * Date when group was created
     * 
     */
    @JsonProperty("createdate")
    private String createdate;

    /**
     * Parent Reference
     * 
     */
    
    @JsonProperty("parent")
    public String getParent() {
        return this.parent;
    }

    /**
     * Parent Reference
     * 
     */
    
    @JsonProperty("parent")
    public void setParent(String parent) {
        this.parent = parent;
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
     * category
     * (Required)
     * 
     */
    
    @JsonProperty("level")
    public String getLevel() {
        return this.level;
    }

    /**
     * category
     * (Required)
     * 
     */
    
    @JsonProperty("level")
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * String value denoting the group tag
     * (Required)
     * 
     */
    
    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    /**
     * String value denoting the group tag
     * (Required)
     * 
     */
    
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Date when group was created
     * 
     */
    
    @JsonProperty("createdate")
    public String getCreatedate() {
        return this.createdate;
    }

    /**
     * Date when group was created
     * 
     */
    
    @JsonProperty("createdate")
    public void setCreatedate(String createdate) {
        this.createdate = createdate;
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
