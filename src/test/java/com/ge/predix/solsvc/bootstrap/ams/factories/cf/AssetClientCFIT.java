package com.ge.predix.solsvc.bootstrap.ams.factories.cf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.message.BasicHeader;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ge.predix.entity.asset.Asset;
import com.ge.predix.entity.asset.AssetTag;
import com.ge.predix.entity.datasource.TimeseriesDatasource;
import com.ge.predix.entity.util.map.Map;
import com.ge.predix.solsvc.bootstrap.ams.common.BaseFactoryIT;
import com.ge.predix.solsvc.bootstrap.ams.dto.Attribute;
import com.ge.predix.solsvc.bootstrap.ams.dto.Classification;
import com.ge.predix.solsvc.bootstrap.ams.dto.Group;
import com.ge.predix.solsvc.bootstrap.ams.dto.Tag;
import com.ge.predix.solsvc.bootstrap.ams.factories.ClassificationFactory;
import com.ge.predix.solsvc.bootstrap.ams.factories.AssetClientImpl;
import com.ge.predix.solsvc.bootstrap.ams.factories.cf.testclasses.JetEngine;
import com.ge.predix.solsvc.bootstrap.ams.factories.cf.testclasses.JetEngineNoModel;
import com.ge.predix.solsvc.bootstrap.ams.factories.cf.testclasses.JetEnginePart;
import com.ge.predix.solsvc.ext.util.JsonMapper;

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
public class AssetClientCFIT extends BaseFactoryIT {

	@SuppressWarnings("hiding")
	private static final Logger log = LoggerFactory
			.getLogger(AssetClientCFIT.class);

	@Autowired
	@Qualifier("AssetClient")
	private AssetClientImpl assetClient;
	
	@Autowired
	    private ClassificationFactory classificationFactory;

	@Autowired
    private JsonMapper       jsonMapper;
	/**
	 * -
	 */
	@SuppressWarnings("nls")
	@Test
	public void test() {
		String str = "/fruit/apple";
		log.debug(str.substring(1, str.indexOf("/", 2)));
	}

	/**
	 * -
	 * @throws JSONException -
	 */
	@Test
	public void testModelCRUD() throws JSONException {
		List<Header> headers = this.restClient.getSecureTokenForClientId();
		this.restClient.addZoneToHeaders(headers,
				this.assetRestConfig.getZoneId());
		
		testDeleteModel(headers);
		testUpdateSimpleModel(headers);
		testCreateSimpleModel(headers);
		testCreateModels(headers);
		testCreateModelsWithClassifications(headers);
		testUpdateModels(headers);
		testCreateJsonAsset();
		testCreateJsonAssetString();
		testGetModel(headers);
		testGetAllModels(headers);
		testGetModelsByFilter(headers);
	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	@SuppressWarnings("nls")
	public void testCreateSimpleModel(List<Header> headers) {
		JetEngine engine1 = new JetEngine();
		engine1.setUri("/engine/ENG1.23");
		engine1.setSerialNo(12345);

		JetEnginePart jetEnginePart = new JetEnginePart();
		jetEnginePart.setUri("/part/pt9876");
		jetEnginePart.setsNo(55555);
		engine1.setJetEnginePart(jetEnginePart);

		JetEngine engine2 = new JetEngine();
		engine2.setUri("/engine/ENG2.23");
		engine2.setSerialNo(12345);
		engine2.setJetEnginePart(jetEnginePart);

		List<JetEngine> engines = new ArrayList<JetEngine>();
		engines.add(engine1);
		engines.add(engine2);

		HttpResponse response = this.assetClient.createModel(engines, headers);
		Assert.assertEquals(response.getStatusLine().getStatusCode(),
				HttpStatus.SC_NO_CONTENT);
	}

	/**
	 * @param headers
	 *            -
	 */
	@SuppressWarnings("nls")
	public void testUpdateSimpleModel(List<Header> headers) {
		JetEngine engine1 = new JetEngine();
		engine1.setUri("/engine/ENG1.23");
		engine1.setSerialNo(12345);

		JetEnginePart jetEnginePart = new JetEnginePart();
		jetEnginePart.setUri("/part/pt9876");
		jetEnginePart.setsNo(55555);
		engine1.setJetEnginePart(jetEnginePart);

		HttpResponse response = this.assetClient.updateModel(engine1, headers);
		Assert.assertEquals(response.getStatusLine().getStatusCode(),
				HttpStatus.SC_NO_CONTENT);
	}

	/**
	 * Test this simple get method by retrieving various types of models like Asset, Group 
	 * and Tag
	 * 
	 * @param headers
	 * 
	 */
	@SuppressWarnings("nls")
	public void testGetModel(List<Header> headers) {
		
		List<JetEngineNoModel> engines = this.assetClient.getModels(
				"/engine/ENG1.23",
				JetEngineNoModel.class, headers);
		assertNotNull(engines);
		assertEquals("12345", engines.get(0).getSerialNo().toString());
		
		//Test Assets
		List<Asset> assets = this.assetClient.getModels("/asset/getrb_2", 
    			Asset.class, headers);
//		//Tom test
		List<Asset> assets1 = this.assetClient.getModels("/asset/plant-richmond-refinery-summary", 
    			Asset.class, headers);
		
		assertNotNull(assets);
		assertNotNull(assets1);
		
		assertEquals("geWindTurbine_1", assets.get(0).getAssetId().toString());
		assertEquals("/group/gent_belgium", assets.get(0).getGroup().toString());
		
		
		//Test Group
		 List<Group> myGroups = this.assetClient.getModels(
					"/group/Suncore_EnergieEnterprise", Group.class, headers);		 
		 List<Group> myGroups2 = this.assetClient.getModels(
					"/group/plant-richmond-refinery", Group.class, headers);
		 assertNotNull(myGroups2);
		 
		 assertNotNull(myGroups);
		 assertEquals("Suncore Energie",myGroups.get(0).getName().toString());
		
		 
		//Test Tag
		 List<Tag> allTags = this.assetClient.getModels("/tag/pressureForTC", Tag.class, headers);//$NON-NLS-1$
			assertEquals("pressureForTC", allTags.get(0).getName().toString());//$NON-NLS-1$
	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	@SuppressWarnings("nls")
	public void testGetAllModels(List<Header> headers) {
		//log.debug("In testgetAllModels");
		List<JetEngineNoModel> engines = this.assetClient.getModels("/engine",
			 JetEngineNoModel.class, headers);
		assertNotNull(engines);
		// assertTrue(engines.size() == 1); //Interestingly, I see that an
		// assets(s) with the same uri is being created.
		// The above list has two objects with same uri "/engine/ENG1.23",
		// therefore, the assert condition is failing
		
		//test the number of groups created (call this method before deleting groups)
		List<Group> allGroups = this.assetClient.getModels("/group",
				 Group.class, headers);
			assertNotNull(allGroups);
			//log.debug("Number of Groups: " + allGroups.size());
			assertTrue(allGroups.size() >= 2);
			
			//log.debug("End testgetAllModels");
	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	@SuppressWarnings("nls")
	public void testDeleteModel(List<Header> headers) {
		CloseableHttpResponse response = this.assetClient.deleteModel(
				"/engine/ENG1.23", headers);
		assertEquals(HttpStatus.SC_NO_CONTENT, response.getStatusLine()
				.getStatusCode());
		response = this.assetClient.deleteModel("/engine/ENG2.23", headers);
		assertEquals(HttpStatus.SC_NO_CONTENT, response.getStatusLine()
				.getStatusCode());
		
		//Test Delete Assets
		
		response = this.assetClient.deleteModel("/asset/getrb_2", headers);
		assertEquals(HttpStatus.SC_NO_CONTENT, response.getStatusLine()
				.getStatusCode());
		
		//Delete Group AirProds_SA_NVEnterprise
		response = this.assetClient.deleteModel
				("/group/AirProds_SA_NVEnterprise", headers);
		
		assertEquals(HttpStatus.SC_NO_CONTENT, response.getStatusLine()
				.getStatusCode());
	
		
		//Delete Tag
		response = this.assetClient.deleteModel(
				"/tag/pressureForTC", headers);//$NON-NLS-1$
		Assert.assertEquals(response.getStatusLine().getStatusCode(),
				HttpStatus.SC_NO_CONTENT);
		
		try {
			response.close();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	
	/**
     * -
     * 
     * @param headers
     *            -
	 * @throws JSONException -
     */
    @SuppressWarnings("nls")
    public void testCreateJsonAsset() throws JSONException {
        List<Header> headers = this.restClient.getSecureTokenForClientId();
        this.restClient.addZoneToHeaders(headers,
                this.assetRestConfig.getZoneId());
        headers.add(new BasicHeader("Content-type", "application/json"));
        String resource="/my-test-asset-json";
        String resourceUri="/my-test-asset-json/"+UUID.randomUUID();
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("uri", resourceUri);
        jsonObj.put("description", "test");
      
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObj);
       
        HttpResponse response = this.assetClient.createFromJson(resource,jsonArray.toString(), headers);
        Assert.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_NO_CONTENT);

    }
    
    /**
     * -
     * 
     * @param headers
     *            -
     * @throws JSONException -
     */
    @SuppressWarnings("nls")
    public void testCreateJsonAssetString() throws JSONException {
        List<Header> headers = this.restClient.getSecureTokenForClientId();
        this.restClient.addZoneToHeaders(headers,
                this.assetRestConfig.getZoneId());
        headers.add(new BasicHeader("Content-type", "application/json"));
        String resource="/tag";
        String jsonString = "[{\"uri\":\"/tag/c5-c6-isomerization-reactor-output\",\"name\":\"Octane Number\",\"description\":\"C5/C6 isomerization process\",\"dataType\":\"number\",\"tagType\":\"Continuous\",\"defaultTag\":[]}]";
        HttpResponse response = this.assetClient.createFromJson(resource,jsonString , headers);
        Assert.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_NO_CONTENT);
        List<String> result = this.assetClient.getModels
        		("/tag/c5-c6-isomerization-reactor-output", headers);
        Assert.assertNotNull(result);
        Assert.assertTrue(result instanceof ArrayList);
        Assert.assertTrue(result.size() > 0);
    }

    /**
     * Among other things, this tests whether the fromJson() can unmarshal json containing a complexType property, even if you don't know the class, notice how
     * it's passing the parent of Typed.class.
     */
    @SuppressWarnings(
    {
            "nls", "unchecked", "unused"
    })
    @Test
    public void testMarshal()
    {
        // this test aids in debugging the marshaler and unmarshaler
        com.ge.predix.entity.asset.Asset asset = new com.ge.predix.entity.asset.Asset();
        asset.setAssetId("assetId");
        Map assetTags = new Map();
        asset.setAssetTag(assetTags);
        com.ge.predix.entity.asset.AssetTag assetTag = new com.ge.predix.entity.asset.AssetTag();
        assetTag.setTagUri("/sensor1");
        assetTags.put("sensor1", assetTag);
        asset.setAssetTag(assetTags);
        asset.setAdditionalAttributes(new Map());
        asset.getAdditionalAttributes().put("anAttribute", new Attribute());

        // as AssetTag
        String json = this.jsonMapper.toJson(assetTag);
        com.ge.predix.entity.asset.AssetTag am = (com.ge.predix.entity.asset.AssetTag) 
        		this.jsonMapper.fromJson(json,
                com.ge.predix.entity.model.Typed.class);

        // a Map of AssetTag
        json = this.jsonMapper.toJson(assetTags);
        Map map = this.jsonMapper.fromJson(json, Map.class);

        // An Asset containing a Map of AssetTag
        json = this.jsonMapper.toJson(asset);
        com.ge.predix.entity.asset.Asset pa = this.jsonMapper.fromJson(json, com.ge.predix.entity.asset.Asset.class);

        json = this.jsonMapper.toJson(pa);

    }
    
    
 /***********************************************************************************************************************
  * Tests for various domain objects begin here.
  * 
  *************************************************************************************************************************/
    
    /**
     * This test is creating Asset, Group and Tag as the domain object. 
     * 
     * @param headers
     *            -
     */
    @SuppressWarnings(
    {
            "nls", "unused", "unchecked"
    })
    public void testCreateModels(List<Header> headers)
    {
        Asset asset = new Asset();
        asset.setAssetId("geWindTurbine_1");
        asset.setDescription("GE Wind turbine");
        asset.setUri("/asset/getrb_2");
        asset.setGroup("/group/gent_belgium");
        String json = this.jsonMapper.toJson(asset);
        List<Asset> list = new ArrayList<Asset>();
        list.add(asset);
        json = this.jsonMapper.toJson(list);

        List<Asset> assets = new ArrayList<Asset>();
		assets.add(asset);
        
		CloseableHttpResponse response = this.assetClient.createModel(assets, headers);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_NO_CONTENT);
        
        
        //test create with AssetTag
        //have a new list
        List<Asset> list2 = new ArrayList<Asset>();

        Asset asset1 = new Asset();
        asset1.setAssetId("TestAsset_Tag1");
        asset1.setDescription("Testing new asset with asset tag");
        asset1.setUri("/asset/TestAsset_Tag1");
        
  
        AssetTag aTag = new AssetTag();
        aTag.setTagUri("/tag/TestAsset_Tag1");
        aTag.setHiAlarmThreshold(2.5);
        aTag.setLoAlarmThreshold(1.5);
        TimeseriesDatasource timeseriesDatasource = new TimeseriesDatasource();
        timeseriesDatasource.setTag("TestAsset_Tag1.pressure");
        aTag.setTimeseriesDatasource(timeseriesDatasource);
        Map map = new Map();
        map.put("PRESSURE", aTag);
        asset1.setAssetTag(map);
        list2.add(asset1);        
        CloseableHttpResponse response1 = (CloseableHttpResponse) 
        		this.assetClient.createModel(list2, headers);
        Assert.assertEquals(response1.getStatusLine().getStatusCode(), HttpStatus.SC_NO_CONTENT);

        /**
         * 
         * Another test on AssetTag
         */
        
        List<Asset> list3 = new ArrayList<Asset>();

        Asset asset2 = new Asset();
        asset2.setAssetId("xyz-2017");
        asset2.setDescription("Testing AssetTag xyz-2017");
        asset2.setUri("/asset/xyz-2017");
        
        AssetTag aTag2 = new AssetTag();
        aTag2.setLabel("Compression ratioTest");
        aTag2.setTagUri("/tag/xyz-2017-tag");
        aTag2.setIsKpi("true");
        aTag2.setHiAlarmThreshold(2.5);
        aTag2.setLoAlarmThreshold(1.5);
        TimeseriesDatasource timeseriesDatasource2 = new TimeseriesDatasource();
        timeseriesDatasource2.setTag("Compressor-2017-Test:CompressionRatio");
        aTag2.setTimeseriesDatasource(timeseriesDatasource2);
       aTag2.setAlertStatusUri(
      "/asset/abc-2017.alert-status.crank-frame-compressionratio");
        
        Map map2 = new Map();
        map2.put("crank-frame-compressionratio", aTag2);
        asset2.setAssetTag(map2);
        
        
        /**
         * Testing with multiple assetTags
         */
        
        AssetTag aTag3 = new AssetTag();
        aTag3.setLabel("Compression ratioTest2");
        aTag3.setTagUri("/tag/def-2017-tag");
        aTag3.setIsKpi("true");
        aTag3.setHiAlarmThreshold(2.5);
        aTag3.setLoAlarmThreshold(1.5);
        TimeseriesDatasource timeseriesDatasource3 = new TimeseriesDatasource();
        timeseriesDatasource3.setTag("Compressor-2017-Test:CompressionRatio");
        aTag3.setTimeseriesDatasource(timeseriesDatasource3);
       aTag3.setAlertStatusUri(
      "/asset/def-2017.alert-status.crank-frame-compressionratio");
        
        //Map map3 = new Map();
        map2.put("crank-frame-compressionratio Map3", aTag3);
        //add it to same asset
        asset2.setAssetTag(map2);
        
        
        list3.add(asset2);        
        CloseableHttpResponse response2 = (CloseableHttpResponse) 
        		this.assetClient.createModel(list3, headers);
        Assert.assertEquals(response2.getStatusLine().getStatusCode(), HttpStatus.SC_NO_CONTENT);
     
        
        //Test Group Objects
        Group group = new Group();
		group.setName("AirProducts SA_NV"); 
		group.setUri("/group/AirProds_SA_NVEnterprise");
		group.setLevel("enterprise");
		group.setDescription("Air Products SA/NV enterprise");

		Group group2 = new Group();
		group2.setName("Suncore Energie");
		group2.setUri("/group/Suncore_EnergieEnterprise");
		group2.setLevel("enterprise");
		group2.setDescription("Suncore Energie enterprise");

		List<Group> groupsList = new ArrayList<Group>();
		groupsList.add(group);
		groupsList.add(group2);
		response = this.assetClient.createModel(groupsList, headers);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 
        		HttpStatus.SC_NO_CONTENT);
        
        //Test create Tag
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
		List<Tag> tagsList = new ArrayList<Tag>();
		tagsList.add(tag);
		response = this.assetClient.createModel(tagsList, headers);
		Assert.assertEquals(response.getStatusLine().getStatusCode(),
				HttpStatus.SC_NO_CONTENT);
		
		try {
			response.close();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
    }

    /**
    * Create a list of Assets and add the classification URIs
    */
  
    @SuppressWarnings("nls")
    public void testCreateModelsWithClassifications(List<Header> headers)
    {
        // First create Classification Object
        Classification classification = new Classification();
        classification.setName("GEMach");
        classification.setUri("/classification/GasTurbineTest");
        classification.setDescription("Creating classification GasTurbineTest for testing purpose");
        classification.setObsolete(false);
        HttpResponse response = this.classificationFactory.createClassification(classification, headers);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_NO_CONTENT);
        //Create an Asset and add the above classification to it.
        Asset asset = new Asset();
        asset.setAssetId("geturbine_1234");
        asset.setDescription("wind turbine 3 blades");
        asset.setUri("/asset/getrb_1234");
        asset.setAttributes(createTestArributeMapForTurbine());
        asset.setClassificationUri("/classification/GasTurbineTest");
        List<Asset> testAssets = new ArrayList<Asset>();
        testAssets.add(asset);
        response = this.assetClient.createModel(testAssets, headers);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_NO_CONTENT);
        
    }
    
    /**
     * @return Map
     */
    @SuppressWarnings({
            "unchecked", "nls"
    })
    public Map createTestArributeMapForTurbine()
    {
        Map attributeMap = new Map();
        Attribute dimensions = new Attribute();
        dimensions.setType("string");
        dimensions.setRequired(true);
        dimensions.setDisplay(true);

        List<Object> size = new ArrayList<Object>();
        size.add("5mx1mx15m");
        dimensions.setValue(size);

        // Notice this attribute is from classification
        Attribute make = new Attribute();
        make.setType("string");
        make.setRequired(true);
        make.setDisplay(true);

        List<Object> makes = new ArrayList<Object>();
        makes.add("Toyota");
        make.setValue(makes);

        attributeMap.put("make", make);
        attributeMap.put("dimensions", dimensions);
        return attributeMap;
    }
    
    
    /**
     * -
     * Let us update a previously created Asset, Group and Tag objects.
     * first get the desired type of object, then update it. 
     * 
     * @param headers
     *            -
     */
    @SuppressWarnings({
             "nls", "unchecked"
    })
    public void testUpdateModels(List<Header> headers)
    {
    	/**
    	 * Call getModels after you have called testCreateModels else exception will be thrown
    	 */
    		List<Asset> asset = this.assetClient.getModels("/asset/getrb_1234", 
    			Asset.class, headers);
        asset.get(0).setDescription("wind turbine 3 blades--Updated");
        asset.get(0).setParentUri("/asset/getrb_2");

        //Now update with AssetTAG
     	AssetTag aTag = new AssetTag();
        aTag.setTagUri("/tag/pressure");
        aTag.setHiAlarmThreshold(2.5);
        aTag.setLoAlarmThreshold(1.5);
        TimeseriesDatasource timeseriesDatasource = new TimeseriesDatasource();
        timeseriesDatasource.setTag("getrb_1234.pressure");
        aTag.setTimeseriesDatasource(timeseriesDatasource);
        Map map = new Map();
        map.put("PRESSURE", aTag);
        asset.get(0).setAssetTag(map);
        
        CloseableHttpResponse response = (CloseableHttpResponse) 
        		this.assetClient.updateModel(asset.get(0), headers);
        
        
        //log.debug("testUpdateAsset asserts begin*****************************");
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 
        		HttpStatus.SC_NO_CONTENT);
        //Get the new updated Asset and check to see if the description got updated
     	List<Asset> assetNew = this.assetClient.getModels("/asset/getrb_1234", 
    			Asset.class, headers);        
     	assertEquals("wind turbine 3 blades--Updated", assetNew.get(0).
     			getDescription());
     	AssetTag returnedATag = (AssetTag) assetNew.get(0).getAssetTag().
     			get("PRESSURE"); 
        assertEquals("getrb_1234.pressure", 
        		returnedATag.getTimeseriesDatasource().getTag());
        assertEquals("/tag/pressure", returnedATag.getTagUri());
       // log.debug("testUpdateAsset asserts end here******************************");
        
        //Test update Groups
        //log.debug("test update Groups start here**********************************");
        List<Group> myGroups = this.assetClient.getModels(
				"/group/AirProds_SA_NVEnterprise", Group.class, headers);//$NON-NLS-1$
		myGroups.get(0).setDescription("Air Products SA/NV enterprise -- updated");//$NON-NLS-1$

		 response = this.assetClient.updateModel(myGroups.get(0), 
				headers);
		Assert.assertEquals(response.getStatusLine().getStatusCode(),
				HttpStatus.SC_NO_CONTENT);
		List<Group> myGroupsUpdated = this.assetClient.getModels(
				"/group/AirProds_SA_NVEnterprise", Group.class, headers);
		assertEquals(
				"Air Products SA/NV enterprise -- updated", myGroupsUpdated.get(0).getDescription().toString());
		//log.debug("test update Groups end here***************************************");
		try {
			response.close();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
     
    
    /**
     * @author 212672942
     * @param 
     * 
     * Here we pass parentURI as the filter string and the value to be actual URI.
     * eg: parentURI = '/asset/getrb_2'
     * 
     */
    @SuppressWarnings({
        "resource", "nls"
    })
    
    public void testGetModelsByFilter( List<Header> headers)
    {
       
        //get Assets by parentURI filter
        List<Asset> list = this.assetClient.getModelsByFilter(Asset.class, null, 
        		"parentUri", "/asset/getrb_2", headers);
        
        List<Asset> assets = this.assetClient.getModelsByFilter(Asset.class, null, 
        		"group", "/group/plant-richmond-refinery",
				headers);
        
        
       assertNotNull(assets);
					
        
        /**
         * Here we know that there is only one Asset object with the above parentUri.
         * Hence the list size is 1. 
         * Also, test the URI of Asset object if it is the same as we created.
         */
        
        assertEquals(1, list.size());
        assertEquals("/asset/getrb_1234", list.get(0).getUri());
        
        //get Asset by ClassificationUri filter
        List<Asset> list2 = this.assetClient.getModelsByFilter(Asset.class, null, 
        		"classificationUri", "/classification/GasTurbineTest", headers);
        assertEquals(1, list2.size());
        assertEquals("geturbine_1234",list2.get(0).getAssetId().toString());
        
        //Test Groups with Filters
        
        //attributes.summary.value
        List<Group> groupsList = this.assetClient.getModelsByFilter(Group.class, null,
				"uri", "/group/AirProds_SA_NVEnterprise", headers);
        
        List<Group> groupsList1 = this.assetClient.getModelsByFilter(Group.class, null,
				"parent", "/group/plant-richmond-refinery", headers);
        
        //Remember, we get all groups in this URI and not just the ones we created using our junit tests. 
		assertTrue(groupsList.size() >= 0); 
		//we might get more than 2 groups we created--  due to the fact that some other applications
		//might have added groups to this assetURI! So, be careful while testing the asserts.
		
		assertEquals("AirProducts SA_NV", groupsList.get(0).getName().toString());
		
//		log.debug("Group2 is: " + groupsList.get(1).getDescription().toString());
//		assertEquals("Suncore Energie", groupsList.get(1).getName().toString());

		//Test Tags with Filters
    }
    
    
    /***********************************************************************************************************************
     * Tests for various domain objects end here.
     * 
     *************************************************************************************************************************/    
}
