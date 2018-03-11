/*
 * Copyright (c) 2015 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.predix.solsvc.bootstrap.ams.common;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Predix Asset properties. Also handles cloud env VCAP concerns. Simply
 * Autowire this in to your bean and the props are accessible.
 * 
 * @author predix
 */
@Component("assetRestConfig")
public class AssetConfig implements EnvironmentAware, IAssetConfig {
	private static Logger log = LoggerFactory.getLogger(AssetConfig.class);

	@Value("${predix.asset.restProtocol:https}")
	private String restProtocol;

	@Value("${predix.asset.uri:localhost}")
	private String assetUri;

	//@author 212672942: We don't need port separation anymore as both HTTP and HTTPS are served by 8080
//	@Value("${predix.asset.restPort:443}")
//	private String assetPort;

	@Value("${predix.asset.zoneid}")
	private String zoneId;

	@Value("${predix.asset.connectionTimeout:10000}")
	private int assetConnectionTimeout;

	@Value("${predix.asset.assetSocketTimeout:10000}")
	private int assetSocketTimeout;

	private String vcapRestUri;
	private String vcapAssetInstanceId;

	/* (non-Javadoc)
	 * @see com.ge.predix.solsvc.bootstrap.ams.common.IAssetConfig#getAssetUri()
	 */
	@Override
	@SuppressWarnings("nls")
	public String getAssetUri() {
		String localAssetUri = null;
		if (this.vcapRestUri == null) {
			// mvp2 Asset does not need any localRestBaseResource
			
			//@author 212672942: we don't need to mention https port separately at the end of the URL now. Plus assetPort is removed in this version.

//			if (StringUtils.isEmpty(this.assetPort) || this.assetPort.equals("80"))
//				assetUri = this.restProtocol + "://" + this.assetUri;
//			else
			localAssetUri = this.restProtocol + "://" + this.assetUri; //+ ":" + this.assetPort;
			
		} else {
			localAssetUri = this.vcapRestUri;
		}
		return localAssetUri;
	}

	/* (non-Javadoc)
	 * @see com.ge.predix.solsvc.bootstrap.ams.common.IAssetConfig#getZoneId()
	 */
	@Override
	@SuppressWarnings("nls")
	public String getZoneId() {
		log.debug("this.vcapAssetInstanceId=" + this.vcapAssetInstanceId);
		log.debug("zoneId=" + this.zoneId);
		if (StringUtils.isEmpty(this.vcapAssetInstanceId)) {
			return this.zoneId;
		}
		return this.vcapAssetInstanceId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.context.EnvironmentAware#setEnvironment(org.
	 * springframework.core.env.Environment)
	 */
	@SuppressWarnings("nls")
	@Override
	public void setEnvironment(Environment environment) {
		try {

			String vcapsAssetUri = null;
			String vcapsAssetInstanceId = null;
			String assetName = environment.getProperty("predix_asset_name"); // this
																				// is
																				// set
																				// on
																				// the
																				// manifest
																				// of
																				// the
																				// application,
																				// use
																				// the
																				// predix
																				// asset
																				// 'name'
																				// not
																				// the
																				// 'label'
																				// (cf
																				// env
																				// <app>)
			String assetSchemaProtocol = environment.getProperty("predix_asset_protocol"); // this
																							// is
																							// set
																							// on
																							// the
																							// manifest
																							// of
																							// the
																							// application
			if (StringUtils.isEmpty(assetName)) {
				vcapsAssetUri = "vcap.services.predixAsset.credentials.uri"; // this
																				// is
																				// set
																				// from
																				// vcaps
																				// of
																				// the
																				// application
				vcapsAssetInstanceId = "vcap.services.predixAsset.credentials.instanceId";
			} else {
				vcapsAssetUri = "vcap.services." + assetName + ".credentials.uri";
				vcapsAssetInstanceId = "vcap.services." + assetName + ".credentials.instanceId";
			}
			this.vcapAssetInstanceId = environment.getProperty(vcapsAssetInstanceId);
			log.info("Asset InstanceId property is   " + vcapsAssetInstanceId);
			log.info("Asset InstanceId from environment is  " + this.vcapAssetInstanceId);

			this.vcapRestUri = environment.getProperty(vcapsAssetUri);
			this.vcapRestUri = (this.vcapRestUri != null && !this.vcapRestUri.startsWith("http"))
					? assetSchemaProtocol + "://" + this.vcapRestUri : this.vcapRestUri;

			if (this.vcapRestUri != null) {
				URI uri = new URI(this.vcapRestUri);
				this.assetUri = uri.getHost();
				//@author: 212672942  We don't need the assetPort- not required since the http and https requests are now both handled by 8080 
		//				if (uri.getPort() > 0) {
		//					this.assetPort = Integer.toString(uri.getPort());
		//				}
				this.restProtocol = uri.getScheme();
			}

			log.info("Asset Url property is   " + vcapsAssetUri);
			log.info("Asset Url from environment is  " + this.vcapRestUri);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ge.predix.solsvc.bootstrap.ams.common.IAssetConfig#getAssetConnectionTimeout()
	 */
	@Override
	public int getAssetConnectionTimeout() {
		return this.assetConnectionTimeout;
	}

	/* (non-Javadoc)
	 * @see com.ge.predix.solsvc.bootstrap.ams.common.IAssetConfig#getAssetSocketTimeout()
	 */
	@Override
	public int getAssetSocketTimeout() {
		return this.assetSocketTimeout;
	}

}
