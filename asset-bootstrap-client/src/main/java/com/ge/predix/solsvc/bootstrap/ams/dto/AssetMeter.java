
package com.ge.predix.solsvc.bootstrap.ams.dto;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.ge.dsp.pm.ext.entity.model.Model;


/**
 * AssetMeter
 * <p>
 * AssetMeter which is the link between Asset and Meter
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "meterUri",
    "sourceTagId",
    "dataSource",
    "isManual",
    "outputMinimum",
    "outputMaximum",
    "lastCalibrated",
    "isSelfPower",
    "meterClass",
    "locationUUID"
})
public class AssetMeter extends Model {


    /**
     * 
     */
    private static final long serialVersionUID = 5397261905021473687L;
    /**
     * Time series tag id
     * 
     */
    @JsonProperty("sourceTagId")
    private String sourceTagId;
    /**
     * data source
     * (Required)
     * 
     */
    @JsonProperty("dataSource")
    private MeterDatasource meterDatasource;
    /**
     * Indicate if data can be manually input
     * (Required)
     * 
     */
    @JsonProperty("isManual")
    private Boolean isManual = false;
    /**
     * output Minimum Threshold
     * 
     */
    @JsonProperty("outputMinimum")
    private Double outputMinimum;
    /**
     * output Maximum Threshold
     * 
     */
    @JsonProperty("outputMaximum")
    private Double outputMaximum;
    /**
     * lastCalibrated
     * 
     */
    @JsonProperty("lastCalibrated")
    private String lastCalibrated;
    /**
     * isSelfPower
     * 
     */
    @JsonProperty("isSelfPower")
    private Boolean isSelfPower;
    /**
     * meterClass
     * 
     */
    @JsonProperty("meterClass")
    private String meterClass;
    /**
     * locationUUID
     * 
     */
    @JsonProperty("locationUUID")
    private String locationUUID;


    /**
     * Time series tag id
     * @return string
     * 
     */
    @JsonProperty("sourceTagId")
    public String getSourceTagId() {
        return this.sourceTagId;
    }

    /**
     * Time series tag id
     * @param sourceTagId sourceTagId
     * 
     */
    @JsonProperty("sourceTagId")
    public void setSourceTagId(String sourceTagId) {
        this.sourceTagId = sourceTagId;
    }

    /**
     * @return Datasource
     * 
     */
    @JsonProperty("meterDatasource")
    public MeterDatasource getMeterDatasource() {
        return this.meterDatasource;
    }

    /**
     * @param meterDatasource (Required) 
     * 
     */
    @JsonProperty("meterDatasource")
    public void setMeterDatasource(MeterDatasource meterDatasource) {
        this.meterDatasource = meterDatasource;
    }

    /**
     * Indicate if data can be manually input
     * (Required)
     * @return boolean
     * 
     */
    @JsonProperty("isManual")
    public Boolean getIsManual() {
        return this.isManual;
    }

    /**
     * Indicate if data can be manually input
     * (Required)
     * @param isManual manual
     * 
     */
    @JsonProperty("isManual")
    public void setIsManual(Boolean isManual) {
        this.isManual = isManual;
    }

    /**
     * output Minimum Threshold
     * @return Double
     * 
     */
  
	@JsonProperty("outputMinimum")
    public Double getOutputMinimum() {
        return this.outputMinimum;
    }

    /**
     * output Minimum Threshold
     * @param outputMinimum outputMinimum
     */
    @JsonProperty("outputMinimum")
    public void setOutputMinimum(Double outputMinimum) {
        this.outputMinimum = outputMinimum;
    }

    /**
     * output Maximum Threshold
     * @return Double
     */
    @JsonProperty("outputMaximum")
    public Double getOutputMaximum() {
        return this.outputMaximum;
    }

    /**
     * output Maximum Threshold
     * @param outputMaximum output Maximum Threshold
     * 
     */
    @JsonProperty("outputMaximum")
    public void setOutputMaximum(Double outputMaximum) {
        this.outputMaximum = outputMaximum;
    }

    /**
     * lastCalibrated
     * @return lastCalibrated
     * 
     */
    @JsonProperty("lastCalibrated")
    public String getLastCalibrated() {
        return this.lastCalibrated;
    }

    /**
     * lastCalibrated
     * @param lastCalibrated lastCalibrated
     * 
     */
    @JsonProperty("lastCalibrated")
    public void setLastCalibrated(String lastCalibrated) {
        this.lastCalibrated = lastCalibrated;
    }

    /**
     * isSelfPower
     * @return  isSelfPower
     * 
     */
    @JsonProperty("isSelfPower")
    public Boolean getIsSelfPower() {
        return this.isSelfPower;
    }

    /**
     * isSelfPower 
     * @param isSelfPower isSelfPower
     * 
     */
    @JsonProperty("isSelfPower")
    public void setIsSelfPower(Boolean isSelfPower) {
        this.isSelfPower = isSelfPower;
    }

    /**
     * meterClass
     * @return isSelfPower
     * 
     */
    @JsonProperty("meterClass")
    public String getMeterClass() {
        return this.meterClass;
    }

    /**
     * meterClass
     * @param meterClass meterClass
     * 
     */
    @JsonProperty("meterClass")
    public void setMeterClass(String meterClass) {
        this.meterClass = meterClass;
    }

    /**
     * locationUUID
     * @return locationUUID
     * 
     */
    @JsonProperty("locationUUID")
    public String getLocationUUID() {
        return this.locationUUID;
    }

    /**
     * locationUUID
     * @param locationUUID locationUUID
     * 
     */
    @JsonProperty("locationUUID")
    public void setLocationUUID(String locationUUID) {
        this.locationUUID = locationUUID;
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
