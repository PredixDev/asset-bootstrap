package com.ge.predix.solsvc.bootstrap.ams.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import com.ge.predix.solsvc.restclient.impl.RestClient;

/**
 * 
 * @author 212421693
 *
 */
@ActiveProfiles(profiles = "local")
public abstract class BaseFactoryIT {

	/**
     * 
     */
	protected static final Logger log = LoggerFactory
			.getLogger(BaseFactoryIT.class);

	// private static final Map<String, String> tenantUserMap = ImmutableMap.of(
	// "411", "demo:Demo,135", "777", "777_user:777_user", "511",
	// "511_user:511_user");


	/**
     * 
     */
	@Autowired
	protected IAssetConfig assetRestConfig;

	/**
	 * 
	 */
	@Autowired
	protected RestClient restClient;

}
