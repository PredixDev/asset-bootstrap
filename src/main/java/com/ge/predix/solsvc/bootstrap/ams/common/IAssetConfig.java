/*
 * Copyright (c) 2016 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.predix.solsvc.bootstrap.ams.common;

/**
 * 
 * @author 212438846
 */
public interface IAssetConfig {

	/**
	 * @return full URI
	 */
	public abstract String getAssetUri();

	/**
	 * @return -
	 */
	public abstract String getZoneId();

	/**
	 * @return -
	 */
	public abstract int getAssetConnectionTimeout();

	/**
	 * @return -
	 */
	public abstract int getAssetSocketTimeout();

}