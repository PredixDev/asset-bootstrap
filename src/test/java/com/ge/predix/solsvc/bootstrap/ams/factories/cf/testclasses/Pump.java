package com.ge.predix.solsvc.bootstrap.ams.factories.cf.testclasses;


/**
 * 
 * @author predix -
 */
public class Pump {

    private String uri;
    private Double flowRate;
    
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
     * @return the flowRate
     */
    public Double getFlowRate()
    {
        return this.flowRate;
    }

    /**
     * @param flowRate the flowRate to set
     */
    public void setFlowRate(Double flowRate)
    {
        this.flowRate = flowRate;
    }

   
}
