
package com.ge.predix.solsvc.bootstrap.ams.dto;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * AuditPatch
 * <p>
 * Audit Patch is for sending Audit Change Record to Audit Service
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "versionId",
    "id",
    "userId",
    "tenantId",
    "timestamp",
    "reason",
    "action",
    "identifier",
    "elementAction",
    "elementUri",
    "elementOldValue",
    "elementNewValue",
    "auditId"
})
public class AuditPatch {

    /**
     * Version Number
     * 
     */
    @JsonProperty("versionId")
    private Double versionId;
    /**
     * Application generated id which can uniquely identify AuditPatch
     * 
     */
    @JsonProperty("id")
    private Double id;
    /**
     * User who made the change
     * 
     */
    @JsonProperty("userId")
    private String userId;
    /**
     * Tenant Information where this change happened
     * 
     */
    @JsonProperty("tenantId")
    private String tenantId;
    /**
     * Time when this change happened
     * 
     */
    @JsonProperty("timestamp")
    private Double timestamp;
    /**
     * Reason for the Change
     * 
     */
    @JsonProperty("reason")
    private String reason;
    /**
     * Action performed on the object Like CREATE, UPDATE, DELETE
     * 
     */
    @JsonProperty("action")
    private String action;
    /**
     * Object Identifier where the change happened
     * 
     */
    @JsonProperty("identifier")
    private String identifier;
    /**
     * Action on element
     * 
     */
    @JsonProperty("elementAction")
    private String elementAction;
    /**
     * Element Uri
     * 
     */
    @JsonProperty("elementUri")
    private String elementUri;
    /**
     * Element Old Value
     * 
     */
    @JsonProperty("elementOldValue")
    private String elementOldValue;
    /**
     * Element New Value
     * 
     */
    @JsonProperty("elementNewValue")
    private String elementNewValue;
    /**
     * Id of the Audit Record
     * 
     */
    @JsonProperty("auditId")
    private String auditId;

    /**
     * Version Number
     * 
     */
    
	@JsonProperty("versionId")
    public Double getVersionId() {
        return this.versionId;
    }

    /**
     * Version Number
     * 
     */
    
	@JsonProperty("versionId")
    public void setVersionId(Double versionId) {
        this.versionId = versionId;
    }

    /**
     * Application generated id which can uniquely identify AuditPatch
     * 
     */
    
	@JsonProperty("id")
    public Double getId() {
        return this.id;
    }

    /**
     * Application generated id which can uniquely identify AuditPatch
     * 
     */
    
	@JsonProperty("id")
    public void setId(Double id) {
        this.id = id;
    }

    /**
     * User who made the change
     * 
     */
    
	@JsonProperty("userId")
    public String getUserId() {
        return this.userId;
    }

    /**
     * User who made the change
     * 
     */
    
	@JsonProperty("userId")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Tenant Information where this change happened
     * 
     */
    
	@JsonProperty("tenantId")
    public String getTenantId() {
        return this.tenantId;
    }

    /**
     * Tenant Information where this change happened
     * 
     */
    @JsonProperty("tenantId")
    public void setTenantId( String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * Time when this change happened
     * 
     */
    @SuppressWarnings({  })
	@JsonProperty("timestamp")
    public Double getTimestamp() {
        return this.timestamp;
    }

    /**
     * Time when this change happened
     * 
     */
    
	@JsonProperty("timestamp")
    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Reason for the Change
     * 
     */
    
	@JsonProperty("reason")
    public String getReason() {
        return this.reason;
    }

    /**
     * Reason for the Change
     * 
     */
    
    @JsonProperty("reason")
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Action performed on the object Like CREATE, UPDATE, DELETE
     * 
     */
    
    @JsonProperty("action")
    public String getAction() {
        return this.action;
    }

    /**
     * Action performed on the object Like CREATE, UPDATE, DELETE
     * 
     */
    
    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Object Identifier where the change happened
     * 
     */
    
    @JsonProperty("identifier")
    public String getIdentifier() {
        return this.identifier;
    }

    /**
     * Object Identifier where the change happened
     * 
     */
    
    @JsonProperty("identifier")
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Action on element
     * 
     */
    
    @JsonProperty("elementAction")
    public String getElementAction() {
        return this.elementAction;
    }

    /**
     * Action on element
     * 
     */
    
    @JsonProperty("elementAction")
    public void setElementAction(String elementAction) {
        this.elementAction = elementAction;
    }

    /**
     * Element Uri
     * 
     */
    
    @JsonProperty("elementUri")
    public String getElementUri() {
        return this.elementUri;
    }

    /**
     * Element Uri
     * 
     */
    
    @JsonProperty("elementUri")
    public void setElementUri(String elementUri) {
        this.elementUri = elementUri;
    }

    /**
     * Element Old Value
     * 
     */
    
    @JsonProperty("elementOldValue")
    public String getElementOldValue() {
        return this.elementOldValue;
    }

    /**
     * Element Old Value
     * 
     */
    
    @JsonProperty("elementOldValue")
    public void setElementOldValue(String elementOldValue) {
        this.elementOldValue = elementOldValue;
    }

    /**
     * Element New Value
     * 
     */
    
    @JsonProperty("elementNewValue")
    public String getElementNewValue() {
        return this.elementNewValue;
    }

    /**
     * Element New Value
     * 
     */
    
    @JsonProperty("elementNewValue")
    public void setElementNewValue(String elementNewValue) {
        this.elementNewValue = elementNewValue;
    }

    /**
     * Id of the Audit Record
     * 
     */
    
    @JsonProperty("auditId")
    public String getAuditId() {
        return this.auditId;
    }

    /**
     * Id of the Audit Record
     * 
     */
    
    @JsonProperty("auditId")
    public void setAuditId(String auditId) {
        this.auditId = auditId;
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
