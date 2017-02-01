package com.ge.predix.solsvc.bootstrap.ams.factories.cf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ge.predix.solsvc.bootstrap.ams.common.BaseFactoryIT;
import com.ge.predix.solsvc.bootstrap.ams.dto.Group;
import com.ge.predix.solsvc.bootstrap.ams.factories.GroupFactoryImpl;

/**
 * 
 * @author predix -
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath*:META-INF/spring/ext-util-scan-context.xml",
		"classpath*:META-INF/spring/asset-bootstrap-client-scan-context.xml",
		"classpath*:META-INF/spring/predix-rest-client-scan-context.xml",
		"classpath*:META-INF/spring/predix-rest-client-sb-properties-context.xml" })
@Component
public class GroupFactoryIT extends BaseFactoryIT {
	@SuppressWarnings("hiding")
	private static final Logger log = LoggerFactory
			.getLogger(GroupFactoryIT.class);

	@Autowired
	private GroupFactoryImpl groupFactory;

	/**
	 * -
	 */
	@Test
	public void testGroupCRUDOperations() {
		List<Header> headers = this.restClient.getSecureTokenForClientId();
		this.restClient.addZoneToHeaders(headers,
				this.assetRestConfig.getZoneId());

		testDeleteGroupsOfLevelSite(headers);
		testDeleteGroupsOfLevelEnterprise(headers);

		testCreateGroupsOfLevelEnterprise(headers);
		testGetGroupsOfLevelEnterprise(headers);
		testUpdateGroupOfLevelEnterprise(headers);

		testCreateGroupsOfLevelSite(headers);
		testGetGroupsOfLevelSite(headers);
		testUpdateGroupOfLevelSite(headers);

		testGetAllGroups(headers);

		testDeleteGroupsOfLevelSite(headers);
		testDeleteGroupsOfLevelEnterprise(headers);
	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	public void testCreateGroupsOfLevelEnterprise(List<Header> headers) {
		Group group = new Group();
		group.setName("AirProducts SA_NV"); //$NON-NLS-1$
		group.setUri("/group/AirProds_SA_NVEnterprise");//$NON-NLS-1$
		group.setLevel("enterprise");//$NON-NLS-1$
		group.setDescription("Air Products SA/NV enterprise");//$NON-NLS-1$

		Group group2 = new Group();
		group2.setName("Suncore Energie");//$NON-NLS-1$
		group2.setUri("/group/Suncore_EnergieEnterprise");//$NON-NLS-1$
		group2.setLevel("enterprise");//$NON-NLS-1$
		group2.setDescription("Suncore Energie enterprise");//$NON-NLS-1$

		List<Group> list = new ArrayList<Group>();
		list.add(group);
		list.add(group2);
		CloseableHttpResponse response = this.groupFactory
				.create(list, headers);
	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	public void testGetGroupsOfLevelEnterprise(List<Header> headers) {
		List<Group> list = this.groupFactory.getGroupsByFilter(null,
				"level", "enterprise", headers);//$NON-NLS-1$//$NON-NLS-2$
		assertTrue(list.size() >= 0);
	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	public void testUpdateGroupOfLevelEnterprise(List<Header> headers) {
		Group group = this.groupFactory.getGroup(
				"AirProds_SA_NVEnterprise", headers);//$NON-NLS-1$
		group.setDescription("Air Products SA/NV enterprise -- updated");//$NON-NLS-1$

		HttpResponse response = this.groupFactory.updateGroup(group, headers);
		Assert.assertEquals(response.getStatusLine().getStatusCode(),
				HttpStatus.SC_NO_CONTENT);
		group = this.groupFactory.getGroup("AirProds_SA_NVEnterprise", headers);//$NON-NLS-1$
		assertEquals(
				"Air Products SA/NV enterprise -- updated", group.getDescription());//$NON-NLS-1$

	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	public void testDeleteGroupsOfLevelEnterprise(List<Header> headers) {
		HttpResponse response = this.groupFactory.deleteGroup(
				"AirProds_SA_NVEnterprise", headers);//$NON-NLS-1$
		Assert.assertEquals(response.getStatusLine().getStatusCode(),
				HttpStatus.SC_NO_CONTENT);

		response = this.groupFactory.deleteGroup(
				"Suncore_EnergieEnterprise", headers); //$NON-NLS-1$
		Assert.assertEquals(response.getStatusLine().getStatusCode(),
				HttpStatus.SC_NO_CONTENT);

	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	public void testGetChildGroups(List<Header> headers) {
		List<Group> list = this.groupFactory.getGroupsByFilter(null,
				"ancestor", "/group/Suncore_EnergieEnterprise", headers);//$NON-NLS-1$ //$NON-NLS-2$
		log.debug(list.toString());
	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	public void testCreateGroupsOfLevelSite(List<Header> headers) {
		Group group = new Group();
		group.setName("Gent, Belgium name");//$NON-NLS-1$
		group.setUri("/group/gent_belgium");//$NON-NLS-1$
		group.setLevel("site");//$NON-NLS-1$
		group.setDescription("Site: Gent, Belgium desc");//$NON-NLS-1$
		group.setParent("/group/AirProds_SA_NVEnterprise"); //NOTE: groups of level site are child of enterprise//$NON-NLS-1$

		Group group2 = new Group();
		group2.setName("Montreal, QC, Canada");//$NON-NLS-1$
		group2.setUri("/group/montreal_qc_canada");//$NON-NLS-1$
		group2.setLevel("site");//$NON-NLS-1$
		group2.setDescription("Site: Montreal, QC, Canada");//$NON-NLS-1$
		group2.setParent("/group/Suncore_EnergieEnterprise"); //NOTE: groups of level site are child of enterprise//$NON-NLS-1$

		List<Group> list = new ArrayList<Group>();
		list.add(group);
		list.add(group2);

		this.groupFactory.create(list, headers);
	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	public void testGetGroupsOfLevelSite(List<Header> headers) {
		List<Group> list = this.groupFactory.getGroupsByFilter(null,
				"level", "site", headers);//$NON-NLS-1$//$NON-NLS-2$
		assertTrue(list.size() > 0);
	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	public void testUpdateGroupOfLevelSite(List<Header> headers) {
		Group group = this.groupFactory.getGroup("gent_belgium", headers); //$NON-NLS-1$
		group.setDescription("Site: Gent, Belgium desc -- updated"); //$NON-NLS-1$
		this.groupFactory.updateGroup(group, headers);

		group = this.groupFactory.getGroup("gent_belgium", headers); //$NON-NLS-1$
		assertEquals(
				"Site: Gent, Belgium desc -- updated", group.getDescription()); //$NON-NLS-1$

	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	public void testDeleteGroupsOfLevelSite(List<Header> headers) {
		HttpResponse response = this.groupFactory.deleteGroup(
				"montreal_qc_canada", headers); //$NON-NLS-1$
		Assert.assertEquals(response.getStatusLine().getStatusCode(),
				HttpStatus.SC_NO_CONTENT);
		response = this.groupFactory.deleteGroup("gent_belgium", headers); //$NON-NLS-1$
		Assert.assertEquals(response.getStatusLine().getStatusCode(),
				HttpStatus.SC_NO_CONTENT);

	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	public void testGetAllGroups(List<Header> headers) {
		List<Group> list = this.groupFactory.getAllGroups(headers);
		assertTrue(list.size() >= 4);
	}

}
