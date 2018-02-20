package com.ge.predix.solsvc.bootstrap.ams.factories.cf.testclasses;


/**
 * 
 * @author predix -
 */
public class JetEngine extends AviationModel{
	/**
     * 
     */
    private static final long serialVersionUID = -6672711921977449214L;
	private JetEnginePart jetEnginePart;
	
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
