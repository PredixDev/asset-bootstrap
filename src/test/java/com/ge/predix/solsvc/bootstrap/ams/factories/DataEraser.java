package com.ge.predix.solsvc.bootstrap.ams.factories;

import java.util.List;

import org.apache.http.Header;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ge.predix.solsvc.bootstrap.ams.common.BaseFactoryIT;
import com.ge.predix.solsvc.bootstrap.ams.factories.cf.AssetFactoryCFIT;
import com.ge.predix.solsvc.bootstrap.ams.factories.cf.GroupFactoryIT;

/**
 * 
 * 
 * @author 212421693
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath*:META-INF/spring/TEST-asset-bootstrap-karaf-context.xml",
		"classpath*:META-INF/spring/predix-rest-client-scan-context.xml" })
public class DataEraser extends BaseFactoryIT {

	@Autowired
	private GroupFactoryIT groupFactoryTH;

	@Autowired
	private AssetFactoryCFIT assetFactoryTH;

	/**
	 * -
	 */
	@Test
	public void testDeleteData() {
		List<Header> headers = this.restClient.getSecureTokenForClientId();
		this.restClient.addZoneToHeaders(headers,
				this.assetRestConfig.getZoneId());

		/* Deleting all assets first* */
		this.assetFactoryTH.testDeleteAssets(headers);

		this.groupFactoryTH.testCreateGroupsOfLevelSite(headers);
		this.groupFactoryTH.testCreateGroupsOfLevelEnterprise(headers);
		/* Deleting all groups****** */
		this.groupFactoryTH.testDeleteGroupsOfLevelSite(headers);
		this.groupFactoryTH.testDeleteGroupsOfLevelEnterprise(headers);
	}
}
