package com.ge.predix.solsvc.bootstrap.ams.factories.cf;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ge.predix.solsvc.bootstrap.ams.common.BaseFactoryIT;
import com.ge.predix.solsvc.bootstrap.ams.dto.Tag;
import com.ge.predix.solsvc.bootstrap.ams.factories.TagFactory;

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
public class TagFactoryCFIT extends BaseFactoryIT {

	@Autowired
	private TagFactory tagFactory;

	/**
	 * -
	 */
	@Test
	public void testTagCRUDOperations() {
		List<Header> headers = this.restClient.getSecureTokenForClientId();
		this.restClient.addZoneToHeaders(headers,
				this.assetRestConfig.getZoneId());

		testDeleteTag(headers);
		testCreateTagGetToken(headers);
		testGetTag(headers);
		testDeleteTag(headers);
	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	public void testCreateTagGetToken(List<Header> headers) {
		Tag tag = new Tag();
		tag.setName("pressureForTC"); //$NON-NLS-1$
		tag.setDescription("pressure description");//$NON-NLS-1$
		tag.setUom("Pascal");//$NON-NLS-1$
		tag.setTagType("Continuous");//$NON-NLS-1$
		tag.setDataType("String");//$NON-NLS-1$
		tag.setUri("/tag/pressureForTC");//$NON-NLS-1$
		List<String> tags = new ArrayList<String>();
		tags.add("asset1.pressure");//$NON-NLS-1$
		tag.setDefaultTag(tags); // no point setting. It comes back as an empty list

		HttpResponse response = this.tagFactory.createTag(tag, headers);
		Assert.assertEquals(response.getStatusLine().getStatusCode(),
				HttpStatus.SC_NO_CONTENT);

	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	public void testGetTag(List<Header> headers) {
		Tag tag = this.tagFactory.getTag("pressureForTC", headers);//$NON-NLS-1$
		assertEquals("pressureForTC", tag.getName());//$NON-NLS-1$

	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	public void testDeleteTag(List<Header> headers) {
		HttpResponse response = this.tagFactory.deleteTag(
				"pressureForTC", headers);//$NON-NLS-1$
		Assert.assertEquals(response.getStatusLine().getStatusCode(),
				HttpStatus.SC_NO_CONTENT);

	}

}
