package com.ge.predix.solsvc.bootstrap.ams.factories.cf.testclasses;


/**
 * 
 * @author predix -
 */
public class JetEngineNoModel {
	/**
     * 
     */
    private String uri;
    private Integer serialNo;
	private JetEnginePart jetEnginePart;
    /**
     * @return the uri
     */
    public String getUri()
    {
        return this.uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(String uri)
    {
        this.uri = uri;
    }

    /**
	 * @return -
	 */
	public Integer getSerialNo() {
		return this.serialNo;
	}

	/**
	 * @param serialNo - 
	 * 
	 */
	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	
	/**
	 * @return -
	 */
	public JetEnginePart getJetEnginePart() {
		return this.jetEnginePart;
	}

	/**
	 * @param part -
	 */
	public void setJetEnginePart(JetEnginePart part) {
		this.jetEnginePart = part;
	}
}
