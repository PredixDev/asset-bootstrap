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
 * @author predix -
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath*:META-INF/spring/predix-rest-client-sb-properties-context.xml",
		"classpath*:META-INF/spring/predix-rest-client-scan-context.xml" })
public class DataGenerator extends BaseFactoryIT {

	@Autowired
	private GroupFactoryIT groupFactoryTH;

	@Autowired
	private AssetFactoryCFIT assetFactoryTH;

	@Autowired
	private AssetFactory assetFactory;

	@Autowired
	private GroupFactory groupFactory;

	/**
	 * -
	 */
	@Test
	public void testCreateData() {

		List<Header> headers = this.restClient.getSecureTokenForClientId();
		this.restClient.addZoneToHeaders(headers,
				this.assetRestConfig.getZoneId());
		this.assetFactory.deleteAsset("getrb_2345", headers); //$NON-NLS-1$
		this.assetFactory.deleteAsset("getrb_1234", headers); //$NON-NLS-1$	
		this.assetFactory.deleteAsset("getrb_2", headers);//$NON-NLS-1$

		this.groupFactory.deleteGroup("gent_belgium", headers);//$NON-NLS-1$
		this.groupFactory.deleteGroup("montreal_qc_canada", headers);//$NON-NLS-1$

		this.groupFactory.deleteGroup("AirProds_SA_NVEnterprise", headers);//$NON-NLS-1$
		this.groupFactory.deleteGroup("Suncore_EnergieEnterprise", headers); //$NON-NLS-1$	

		/** Creating groups of levels enterprise and site **/
		this.groupFactoryTH.testCreateGroupsOfLevelEnterprise(headers);
		this.groupFactoryTH.testCreateGroupsOfLevelSite(headers);

		/* Creating simple and complex assets*********** */
		this.assetFactoryTH.testCreateSimpleAssets(headers);
		this.assetFactoryTH.testCreateAssetsWithClassifications(headers);

		/* associating assets to groups**************** */
		this.assetFactoryTH.testAssociateGroupToAsset();
	}
}
