package com.ge.predix.solsvc.bootstrap.ams.dto;

import com.ge.dsp.pm.ext.entity.model.Model;

/**
 * 
 * @author predix -
 */
public class MeterDatasource {
	/**
     * 
     */
    private static final long serialVersionUID = -7948259148807095273L;
    private String nodeName;
	private String isKpi;
	private String fieldUri;
	private String controllerUri;
	private String machineUri;
	private String meterExtensionsUri;


	/**
	 * @return -
	 */
	public String getNodeName() {
		return this.nodeName;
	}

	/**
	 * @param nodeName -
	 */
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
	/**
	 * @return -
	 */
	public String getIsKpi() {
		return this.isKpi;
	}

	/**
	 * @param isKpi -
	 */
	public void setIsKpi(String isKpi) {
		this.isKpi = isKpi;
	}

	/**
	 * @return -
	 */
	public String getFieldUri() {
		return this.fieldUri;
	}

	/**
	 * @param fieldUri -
	 */
	public void setFieldUri(String fieldUri) {
		this.fieldUri = fieldUri;
	}

	/**
	 * @return -
	 */
	public String getControllerUri() {
		return this.controllerUri;
	}

	/**
	 * @param controllerUri -
	 */
	public void setControllerUri(String controllerUri) {
		this.controllerUri = controllerUri;
	}

	/**
	 * @return -
	 */
	public String getMachineUri() {
		return this.machineUri;
	}

	/**
	 * @param machineUri -
	 */
	public void setMachineUri(String machineUri) {
		this.machineUri = machineUri;
	}
	
	/**
	 * @return -
	 */
	public String getMeterExtensionsUri() {
		return this.meterExtensionsUri;
	}

	/**
	 * @param meterExtensionsUri -
	 */
	public void setMeterExtensionsUri(String meterExtensionsUri) {
		this.meterExtensionsUri = meterExtensionsUri;
	}


}
