
package com.ge.predix.solsvc.bootstrap.ams.dto;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * AuditRecord
 * <p>
 * Audit Record which have Audit Data
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "userId",
    "tenantId",
    "timestamp",
    "reason",
    "action",
    "identifier",
    "versionId",
    "oldValue",
    "newValue"
})
public class AuditRecord {

    /**
     * id which can uniquely identify AuditRecord, id shouldn't be null or omitted when send JSon request for creating new audit record in audit DB, id must be sent when send JSon request for updating  new AuditRecord, uri is always returned for creating, updating and retrieving AuditRecord
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
     * Version Number
     * 
     */
    @JsonProperty("versionId")
    private Double versionId;
    /**
     * Object old Value
     * 
     */
    @JsonProperty("oldValue")
    private String oldValue;
    /**
     * Object New Value
     * 
     */
    @JsonProperty("newValue")
    private String newValue;

    /**
     * id which can uniquely identify AuditRecord, id shouldn't be null or omitted when send JSon request for creating new audit record in audit DB, id must be sent when send JSon request for updating  new AuditRecord, uri is always returned for creating, updating and retrieving AuditRecord
     * 
     */
    
    @JsonProperty("id")
    public Double getId() {
        return this.id;
    }

    /**
     * id which can uniquely identify AuditRecord, id shouldn't be null or omitted when send JSon request for creating new audit record in audit DB, id must be sent when send JSon request for updating  new AuditRecord, uri is always returned for creating, updating and retrieving AuditRecord
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
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * Time when this change happened
     * 
     */
    
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
     * Object old Value
     * 
     */
    
    @JsonProperty("oldValue")
    public String getOldValue() {
        return this.oldValue;
    }

    /**
     * Object old Value
     * 
     */
    
    @JsonProperty("oldValue")
    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    /**
     * Object New Value
     * 
     */
    
    @JsonProperty("newValue")
    public String getNewValue() {
        return this.newValue;
    }

    /**
     * Object New Value
     * 
     */
    
    @JsonProperty("newValue")
    public void setNewValue(String newValue) {
        this.newValue = newValue;
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
