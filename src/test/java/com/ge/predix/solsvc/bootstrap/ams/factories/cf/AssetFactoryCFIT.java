package com.ge.predix.solsvc.bootstrap.ams.factories.cf;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ge.predix.solsvc.bootstrap.ams.factories.AssetFactory;
import com.ge.predix.solsvc.bootstrap.ams.factories.ClassificationFactory;
import com.ge.predix.solsvc.ext.util.JsonMapper;

/**
 * 
 * @author predix -
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{
        "classpath*:META-INF/spring/ext-util-scan-context.xml",
        "classpath*:META-INF/spring/asset-bootstrap-client-scan-context.xml",
        "classpath*:META-INF/spring/predix-rest-client-scan-context.xml",
        "classpath*:META-INF/spring/predix-rest-client-sb-properties-context.xml"
})
@Component
public class AssetFactoryCFIT extends BaseFactoryIT
{

    @Autowired
    private ClassificationFactory classificationFactory;

    @Autowired
    private AssetFactory          assetFactory;

    @Autowired
    private JsonMapper            jsonMapper;

    // This test will fail if classfications are not created before execution.
    /*
     * (non-Javadoc)
     * @see com.ge.predix.solsvc.bootstrap.ams.factories.EntityE2ETest#
     * testCompleteAssetCRUD(org.springframework.http.HttpMethod,
     * java.lang.String, java.lang.String)
     */
    /**
     * -
     */
    @Test
    public void testAssetCRUDOperations()
    {
        List<Header> headers = this.restClient.getSecureTokenForClientId();
        this.restClient.addZoneToHeaders(headers, this.assetRestConfig.getZoneId());
        testDeleteAssets(headers);
        testCreateSimpleAssets(headers);
        testCreateSimpleAssetsGetToken(headers);
        testCreateAssetsWithClassifications(headers);
        testUpdateAsset(headers);
        testAssociateTagWithAsset(headers);
        testGetChildAssets(headers);
        testGetAssetsWithSameClassification(headers);
        testDeleteAssets(headers);
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
        com.ge.predix.entity.asset.AssetTag am = (com.ge.predix.entity.asset.AssetTag) this.jsonMapper.fromJson(json,
                com.ge.predix.entity.model.Typed.class);

        // a Map of AssetTag
        json = this.jsonMapper.toJson(assetTags);
        Map map = this.jsonMapper.fromJson(json, Map.class);

        // An Asset containing a Map of AssetTag
        json = this.jsonMapper.toJson(asset);
        com.ge.predix.entity.asset.Asset pa = this.jsonMapper.fromJson(json, com.ge.predix.entity.asset.Asset.class);

        json = this.jsonMapper.toJson(pa);

    }

    // @Test
    /**
     * -
     * 
     * @param headers
     *            -
     */
    @SuppressWarnings("nls")
    public void testCreateSimpleAssetsGetToken(List<Header> headers)
    {
        Asset asset = new Asset();
        asset.setAssetId("geturbine_1"); 
        asset.setDescription("GE turbine");
        asset.setUri("/asset/getrb_2");
        asset.setGroup("/group/gent_belgium");
        HttpResponse response = this.assetFactory.createAsset(asset, headers);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_NO_CONTENT);
    }

    // @Test
    /**
     * -
     * 
     * @param headers
     *            -
     */
    @SuppressWarnings(
    {
            "nls", "unused", "unchecked"
    })
    public void testCreateSimpleAssets(List<Header> headers)
    {
        Asset asset = new Asset();
        asset.setAssetId("geturbine_1");
        asset.setDescription("GE turbine");
        asset.setUri("/asset/getrb_2");
        // asset.setGroup("/group/gent_belgium");
        String json = this.jsonMapper.toJson(asset);
        List<Asset> list = new ArrayList<Asset>();
        list.add(asset);
        json = this.jsonMapper.toJson(list);

        HttpResponse response = this.assetFactory.createAsset(asset, headers);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_NO_CONTENT);

        Asset ao = new Asset();
        ao.setUri("test");
        Map assetTags = new Map();
        AssetTag amo = new AssetTag();
        assetTags.put("test", amo);
        ao.setAssetTag(assetTags);
        json = this.jsonMapper.toJson(amo);
        json = this.jsonMapper.toJson(assetTags);
        json = this.jsonMapper.toJson(ao);
        json = this.jsonMapper.toJson(ao);

    }

    /**
     * -
     */
    @SuppressWarnings("nls")
    public void testAssociateGroupToAsset()
    {
        List<Header> headers = this.restClient.getSecureTokenForClientId();
        this.restClient.addZoneToHeaders(headers, this.assetRestConfig.getZoneId());
        this.assetFactory.associateGroupToAsset("/getrb_2/group", 
                "[\"/group/gent_belgium\"]", headers);
        this.assetFactory.associateGroupToAsset("/getrb_1234/group", 
                "[\"/group/montreal_qc_canada\"]", headers);
        this.assetFactory.associateGroupToAsset("/getrb_2345/group", 
                "[\"/group/montreal_qc_canada\"]", headers);

    }

    /**
     * -
     */
    @SuppressWarnings("nls")
    public void testGroupWthAsset()
    {
        List<Header> headers = this.restClient.getSecureTokenForClientId();
        this.restClient.addZoneToHeaders(headers, this.assetRestConfig.getZoneId());
        @SuppressWarnings("unused")
        List<Asset> list = this.assetFactory.getAssetsByFilter(null, "group", 
                "/group/gent_belgium", headers);
        //// $NON-NLS-1$log.debug(list.toString());
    }

    /**
     * -
     * 
     * @param headers
     *            -
     */
    @SuppressWarnings("nls")
    public void testCreateAssetsWithClassifications(List<Header> headers)
    {
        // create Classification first :
        Classification classification = new Classification();
        classification.setName("GEMach");
        classification.setUri("/classification/GasTurbineTest");
        classification.setDescription("Creating classification GasTurbineTest for testing purpose");
        classification.setObsolete(false);
        HttpResponse response = this.classificationFactory.createClassification(classification, headers);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_NO_CONTENT);

        Asset asset = new Asset();
        asset.setAssetId("geturbine_1234");
        asset.setDescription("wind turbine 3 blades");
        asset.setUri("/asset/getrb_1234");
        asset.setAttributes(createTestArributeMapForTurbine());
        asset.setClassificationUri("/classification/GasTurbineTest");

        response = this.assetFactory.createAsset(asset, headers);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_NO_CONTENT);

        Asset asset2 = new Asset();
        asset2.setAssetId("geturbine_2345");
        asset2.setDescription("water turbine 4 blades");
        asset2.setUri("/asset/getrb_2345");
        asset2.setAttributes(createTestArributeMapForTurbine());
        asset2.setClassificationUri("/classification/GasTurbineTest");

        response = this.assetFactory.createAsset(asset2, headers);
    }

    /**
     * -
     * 
     * @param headers
     *            -
     */
    @SuppressWarnings({
            "resource", "nls"
    })
    public void testUpdateAsset(List<Header> headers)
    {
        Asset asset = this.assetFactory.getAsset("getrb_1234", headers);
        asset.setDescription("wind turbine 3 blades--Updated");
        asset.setParentUri("/asset/getrb_2");
        CloseableHttpResponse response = (CloseableHttpResponse) this.assetFactory.updateAsset(asset, headers);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_NO_CONTENT);

        Asset uAsset = this.assetFactory.getAsset("getrb_1234", headers);
        assertEquals("wind turbine 3 blades--Updated", uAsset.getDescription());
    }

    /**
     * -
     * 
     * @param headers
     *            -
     */
    @SuppressWarnings(
    {
            "unchecked", "unused", "nls"
    })
    public void testAssociateTagWithAsset(List<Header> headers)
    {
        Asset asset = this.assetFactory.getAsset("getrb_1234", headers);

        AssetTag aTag = new AssetTag();
        aTag.setTagUri("/tag/pressure");
        aTag.setHiAlarmThreshold(2.5);
        aTag.setLoAlarmThreshold(1.5);
        TimeseriesDatasource timeseriesDatasource = new TimeseriesDatasource();
        timeseriesDatasource.setTag("getrb_1234.pressure");
        aTag.setTimeseriesDatasource(timeseriesDatasource);
        Map map = new Map();
        map.put("PRESSURE", aTag);
        asset.setAssetTag(map);

        String json = this.jsonMapper.toJson(asset);
        // json = jsonMapper.toJson(aTag);

        HttpResponse response = this.assetFactory.updateAsset(asset, headers);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_NO_CONTENT);
        Asset uAsset = this.assetFactory.getAsset("getrb_1234", headers);
        AssetTag returnedATag = (AssetTag) uAsset.getAssetTag().get("PRESSURE"); 
        assertEquals("getrb_1234.pressure", returnedATag.getTimeseriesDatasource().getTag());
        assertEquals("/tag/pressure", returnedATag.getTagUri());

    }

    /**
     * -
     * 
     * @param headers
     *            -
     */
    @SuppressWarnings("nls")
    public void testGetChildAssets(List<Header> headers)
    {
        List<Asset> list = this.assetFactory.getAssetsByFilter(null, "parentUri", 
                "/asset/getrb_2", headers);
        assertEquals(1, list.size());
        assertEquals("/asset/getrb_1234", list.get(0).getUri());
    }

    /**
     * -
     * 
     * @param headers
     *            -
     */
    @SuppressWarnings("nls")
    public void testGetAssetsWithSameClassification(List<Header> headers)
    {
        List<Asset> list = this.assetFactory.getAssetsByFilter(null, "classificationUri", 
                "/classification/GasTurbineTest", 
                headers);
        assertEquals(2, list.size());
    }

    /**
     * -
     * 
     * @param headers
     *            -
     */
    @SuppressWarnings("nls")
    public void testDeleteAssets(List<Header> headers)
    {
        this.assetFactory.deleteAsset("getrb_2345", headers);
        this.assetFactory.deleteAsset("getrb_1234", headers);
        this.assetFactory.deleteAsset("getrb_2", headers);
        this.assetFactory.deleteAsset("getrb_3", headers);
    }

    /**
     * @return -
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

}
