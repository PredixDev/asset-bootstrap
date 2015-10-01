package com.ge.predix.solsvc.bootstrap.ams.factories.cf.testclasses;

import javax.xml.bind.annotation.XmlSeeAlso;

import org.codehaus.jackson.annotate.JsonTypeInfo;

import com.ge.dsp.pm.ext.entity.model.Model;

/**
 * A sample base class to inherit from.  You can manually create child classes by adding to the @XmlSeeAlso annotation and 
 * marshaling/unmarshaling will happen auto-magically
 * 
 * You can also auto-generate xsd defined annotations using inheritance.  XML or JSON will both be supported at runtime.  The Model class
 * was auto-generated.
 * 
 * @author predix -
 */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, use = JsonTypeInfo.Id.NAME, property = "@type")
@XmlSeeAlso({
    JetEngine.class
})
public class AviationModel extends Model{
	/**
     * 
     */
    private static final long serialVersionUID = -6672711921977449214L;
    private Integer serialNo;
    /**
     * @return the serialNo
     */
    public Integer getSerialNo()
    {
        return this.serialNo;
    }
    /**
     * @param serialNo the serialNo to set
     */
    public void setSerialNo(Integer serialNo)
    {
        this.serialNo = serialNo;
    }
   
    
}
