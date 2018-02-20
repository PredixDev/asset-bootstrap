package com.ge.predix.solsvc.bootstrap.ams.factories.cf.testclasses;

//@JsonDeserialize(as = JetEnginePart.class)
/**
 * 
 * @author predix -
 */
public class JetEnginePart{

	private String uri;
	private Integer sNo;
	
	
	/**
	 * @return -
	 */
	public String getUri() {
		return this.uri;
	}
	/**
	 * @param uri -
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
	/**
	 * @return -
	 */
	public Integer getsNo() {
		return this.sNo;
	}
	/**
	 * @param sNo -
	 */
	public void setsNo(Integer sNo) {
		this.sNo = sNo;
	}
}