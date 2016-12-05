package com.ge.predix.solsvc.bootstrap.ams.factories.cf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ge.predix.solsvc.bootstrap.ams.common.BaseFactoryIT;
import com.ge.predix.solsvc.bootstrap.ams.dto.Attribute;
import com.ge.predix.solsvc.bootstrap.ams.dto.Classification;
import com.ge.predix.solsvc.bootstrap.ams.factories.ClassificationFactory;

/**
 * 
 * 
 * @author 212421693
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath*:META-INF/spring/ext-util-scan-context.xml",
		"classpath*:META-INF/spring/asset-bootstrap-client-scan-context.xml",
		"classpath*:META-INF/spring/predix-rest-client-scan-context.xml",
		"classpath*:META-INF/spring/predix-rest-client-sb-properties-context.xml" })
@Component
public class ClassificationFactoryIT extends BaseFactoryIT {

	@Autowired
	private ClassificationFactory classificationFactory;

	/**
     * 
     */
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * -
	 */
	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	@Test
	public void testClassificationCRUDOperations() {
		List<Header> headers = this.restClient.getSecureTokenForClientId();
		this.restClient.addZoneToHeaders(headers,
				this.assetRestConfig.getZoneId());

		testDeleteClassifications(headers);
		setupClassifications(headers);
		testDeleteClassifications(headers);
	}

	/**
	 * -
	 */
	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	@SuppressWarnings("nls")
	public void setupClassifications(List<Header> headers) {

		// No classifications created at this point. Verify that GET returns 0
		// elements
		// testGetClassifications(0, headers);

		// Creating simple classification with no attributes
		testCreateSimpleClassfication(headers);
		testGetClassification("GEMach1", headers);

		// Creating complex classification w/ attributes
		testCreateClassfication(headers);
		testGetClassification("Turbine", headers);
		testCreateClassficationWithAttributes(headers);
		testGetClassification("TurbineWithAttributes", headers);
		// Create classification with above as parent
		testCreateChildClassficationWithAttributesForGasTurbine(headers);
		testGetClassification("GasTurbine", headers);
		testGetChildClassifications("GEMach1", 1, headers); // the value is 1 as the list //$NON-NLS-1$
		// returns children with the
		// parent included. (parent
		// wth 0 children has count
		// 1)
		testGetChildClassifications("Turbine", 1, headers); // Turbine + GasTurbine //$NON-NLS-1$

		// Update classification Turbine. Give it a parent and update its
		// description
		testUpdateClassification(headers);
		testGetClassificationByUUID("Turbine", headers); //$NON-NLS-1$
		testGetChildClassifications("GEMach1", 1, headers); // GEMach1 + Turbine + //$NON-NLS-1$
		// GasTurbine
		testGetChildClassifications("Turbine", 1, headers); // Turbine + GasTurbine //$NON-NLS-1$
	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	public void testDeleteClassifications(List<Header> headers) {
		testdeleteClassificationByUUID("GasTurbine", headers);//$NON-NLS-1$
		testdeleteClassificationByUUID("Turbine", headers);//$NON-NLS-1$
		testdeleteClassificationByUUID("TurbineWithAttributes", headers);//$NON-NLS-1$
		testdeleteClassificationByUUID("GEMach1", headers);//$NON-NLS-1$
		testdeleteClassificationByUUID("GasTurbineTest", headers);//$NON-NLS-1$
	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	public void testCreateSimpleClassfication(List<Header> headers) {
		Classification classification = new Classification();
		classification.setName("GEMach1");//$NON-NLS-1$
		classification.setUri("/classification/GEMach1");//$NON-NLS-1$
		classification
				.setDescription("Creating classification GEMach1 for testing purpose");//$NON-NLS-1$
		classification.setObsolete(false);
		HttpResponse response = this.classificationFactory
				.createClassification(classification, headers);
		Assert.assertEquals(response.getStatusLine().getStatusCode(),
				HttpStatus.SC_NO_CONTENT);
	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	public void testCreateClassfication(List<Header> headers) {
		Classification classification = new Classification();
		classification.setName("Turbine");//$NON-NLS-1$
		classification.setUri("/classification/Turbine");//$NON-NLS-1$
		classification
				.setDescription("Creating Turbine classification for testing purpose");//$NON-NLS-1$
		classification.setObsolete(false);
		classification.setAttributes(createTestArributeMapForTurbine());
		HttpResponse response = this.classificationFactory
				.createClassification(classification, headers);
		Assert.assertEquals(response.getStatusLine().getStatusCode(),
				HttpStatus.SC_NO_CONTENT);

	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	public void testCreateClassficationWithAttributes(List<Header> headers) {
		Classification classification = new Classification();
		classification.setName("TurbineWithAttributes");//$NON-NLS-1$
		classification.setUri("/classification/TurbineWithAttributes");//$NON-NLS-1$
		classification
				.setDescription("Creating Turbine classification for testing purpose");//$NON-NLS-1$
		classification.setObsolete(false);
		classification.setAttributes(createTestArributeMapForTurbine());
		HttpResponse response = this.classificationFactory
				.createClassification(classification, headers);
		Assert.assertEquals(response.getStatusLine().getStatusCode(),
				HttpStatus.SC_NO_CONTENT);

	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	public void testCreateChildClassficationWithAttributesForGasTurbine(
			List<Header> headers) {
		Classification classification = new Classification();
		classification.setName("GasTurbine");//$NON-NLS-1$
		classification.setUri("/classification/GasTurbine");//$NON-NLS-1$
		classification.setParent("/classification/Turbine");//$NON-NLS-1$
		classification
				.setDescription("Creating GasTurbine classification for testing purpose");//$NON-NLS-1$
		classification.setObsolete(false);
		classification.setAttributes(createTestArributeMapForGasTurbine());
		HttpResponse response = this.classificationFactory
				.createClassification(classification, headers);
		Assert.assertEquals(response.getStatusLine().getStatusCode(),
				HttpStatus.SC_NO_CONTENT);

	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	public void testUpdateClassification(List<Header> headers) {
		Classification classification = this.classificationFactory
				.getClassification("Turbine", headers);//$NON-NLS-1$
		classification.setParent("/classification/GEMach1");//$NON-NLS-1$
		classification
				.setDescription("Creating Turbine classification w attributes for testing purpose--updated Descpt");//$NON-NLS-1$
		HttpResponse response = this.classificationFactory
				.createClassification(classification, headers);
		Assert.assertEquals(response.getStatusLine().getStatusCode(),
				HttpStatus.SC_NO_CONTENT);

	}

	/**
	 * @param uuid
	 *            -
	 * @param headers
	 *            -
	 */
	public void testGetClassificationByUUID(String uuid, List<Header> headers) {
		Classification classification = this.classificationFactory
				.getClassification(uuid, headers);
		assertEquals(
				"Creating Turbine classification w attributes for testing purpose--updated Descpt",//$NON-NLS-1$
				classification.getDescription());
	}

	/**
	 * @param expectedListSize
	 *            -
	 * @param headers
	 *            -
	 */
	public void testGetClassification(String uri, List<Header> headers) {
		Classification classification = this.classificationFactory
				.getClassification(uri, headers);
		Assert.assertNotNull(classification);
	}

	/**
	 * @param parent
	 *            -
	 * @param expectedListSize
	 *            -
	 * @param headers
	 *            -
	 */
	public void testGetChildClassifications(String parent,
			int expectedListSize, List<Header> headers) {
		List<Classification> list = this.classificationFactory
				.getClassificationsByFilter(parent, "showChildren", "999999",//$NON-NLS-1$ //$NON-NLS-2$
						headers);
		assertTrue(list.size() >= expectedListSize);
	}

	/**
	 * @param uuid
	 *            -
	 * @param headers
	 *            -
	 */
	public void testdeleteClassificationWhichHasChildren(String uuid,
			List<Header> headers) {
		// thrown.expect(AmsHttpClientErrorException.class);
		HttpResponse response = this.classificationFactory
				.deleteClassification(uuid, headers);
		Assert.assertEquals(response.getStatusLine().getStatusCode(),
				HttpStatus.SC_NO_CONTENT);

	}

	/**
	 * @param uuid
	 *            -
	 * @param headers
	 *            -
	 */
	public void testdeleteClassificationByUUID(String uuid, List<Header> headers) {
		HttpResponse response = this.classificationFactory
				.deleteClassification(uuid, headers);
		Assert.assertEquals(response.getStatusLine().getStatusCode(),
				HttpStatus.SC_NO_CONTENT);

	}

	/**
	 * @return -
	 */
	public LinkedHashMap<String, Attribute> createTestArributeMapForTurbine() {
		LinkedHashMap<String, Attribute> attributeMap = new LinkedHashMap<String, Attribute>();
		Attribute make = new Attribute();
		make.setType("string");//$NON-NLS-1$
		make.setRequired(true);
		make.setDisplay(true);

		List<Object> makes = new ArrayList<Object>();
		makes.add("Toyota");//$NON-NLS-1$
		makes.add("Audi");//$NON-NLS-1$
		makes.add("BMW");//$NON-NLS-1$
		makes.add("Hyundai");//$NON-NLS-1$
		make.setValue(makes);

		Attribute mileage = new Attribute();
		mileage.setType("string");//$NON-NLS-1$
		mileage.setDisplay(true);
		mileage.setRequired(false);
		mileage.setUom("mpg");//$NON-NLS-1$

		/*
		 * Cardinality cardinality = new Cardinality();
		 * cardinality.setMax("60mpg"); cardinality.setMin("10mpg");
		 * cardinality.setAdditionalProperty("median", "45mpg");
		 * mileage.setCardinality(cardinality);
		 */

		List<Object> source = new ArrayList<Object>();
		source.add("gas");//$NON-NLS-1$
		source.add("electrity");//$NON-NLS-1$
		mileage.setEnumeration(source);

		attributeMap.put("make", make);//$NON-NLS-1$
		attributeMap.put("mileage", mileage);//$NON-NLS-1$

		return attributeMap;
	}

	/**
	 * @return -
	 */
	public LinkedHashMap<String, Attribute> createTestArributeMapForGasTurbine() {
		LinkedHashMap<String, Attribute> attributeMap = new LinkedHashMap<String, Attribute>();
		Attribute size = new Attribute();
		size.setType("string");//$NON-NLS-1$
		size.setRequired(false);
		size.setDisplay(true);

		Attribute features = new Attribute();
		features.setType("string");//$NON-NLS-1$
		features.setDisplay(true);
		features.setRequired(false);
		features.setUom("mpg");//$NON-NLS-1$

		List<Object> feature = new ArrayList<Object>();
		feature.add("doors");//$NON-NLS-1$
		feature.add("seats");//$NON-NLS-1$
		features.setEnumeration(feature);

		attributeMap.put("size", size);//$NON-NLS-1$
		attributeMap.put("features", features);//$NON-NLS-1$

		return attributeMap;
	}

}
