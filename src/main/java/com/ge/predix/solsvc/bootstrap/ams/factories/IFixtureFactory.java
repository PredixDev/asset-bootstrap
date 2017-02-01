/*
 * Copyright (c) 2016 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.predix.solsvc.bootstrap.ams.factories;

import com.ge.predix.solsvc.bootstrap.ams.common.IAssetConfig;
import com.ge.predix.solsvc.restclient.config.IOauthRestConfig;

/**
 * 
 * @author 212438846
 */
public interface IFixtureFactory {

	/**
	 * @param aConfig -
	 * @param rConfig -
	 */
	public abstract void overrideAssetRestConfig(IAssetConfig aConfig,
			IOauthRestConfig rConfig);

}