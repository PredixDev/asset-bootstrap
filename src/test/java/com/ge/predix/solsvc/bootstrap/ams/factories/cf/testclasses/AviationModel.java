package com.ge.predix.solsvc.bootstrap.ams.factories.cf.testclasses;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ge.predix.entity.model.Model;

/**
 * A sample base class to inherit from.  You can manually create child classes by adding to the @XmlSeeAlso annotation and 
 * marshaling/unmarshaling will happen auto-magically
 * 
 * You can also auto-generate xsd defined annotations using inheritance.  XML or JSON will both be supported at runtime.  The Model class
 * was auto-generated.
 * 
 * @author predix -
 */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, use = JsonTypeInfo.Id.NAME, property = "complexType")
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
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((serialNo == null) ? 0 : serialNo.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AviationModel other = (AviationModel) obj;
		if (serialNo == null) {
			if (other.serialNo != null)
				return false;
		} else if (!serialNo.equals(other.serialNo))
			return false;
		return true;
	}
   
    
}
