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
    public void testCreateSimpleAssetsGetToken(List<Header> headers)
    {
        Asset asset = new Asset();
        asset.setAssetId("geturbine_1"); //$NON-NLS-1$
        asset.setDescription("GE turbine");//$NON-NLS-1$
        asset.setUri("/asset/getrb_2");//$NON-NLS-1$
        asset.setGroup("/group/gent_belgium");//$NON-NLS-1$
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
        asset.setAssetId("geturbine_1");//$NON-NLS-1$
        asset.setDescription("GE turbine");//$NON-NLS-1$
        asset.setUri("/asset/getrb_2");//$NON-NLS-1$
        // asset.setGroup("/group/gent_belgium");//$NON-NLS-1$
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
    public void testAssociateGroupToAsset()
    {
        List<Header> headers = this.restClient.getSecureTokenForClientId();
        this.restClient.addZoneToHeaders(headers, this.assetRestConfig.getZoneId());
        this.assetFactory.associateGroupToAsset("/getrb_2/group", //$NON-NLS-1$
                "[\"/group/gent_belgium\"]", headers);//$NON-NLS-1$
        this.assetFactory.associateGroupToAsset("/getrb_1234/group", //$NON-NLS-1$
                "[\"/group/montreal_qc_canada\"]", headers);//$NON-NLS-1$
        this.assetFactory.associateGroupToAsset("/getrb_2345/group", //$NON-NLS-1$
                "[\"/group/montreal_qc_canada\"]", headers);//$NON-NLS-1$

    }

    /**
     * -
     */
    public void testGroupWthAsset()
    {
        List<Header> headers = this.restClient.getSecureTokenForClientId();
        this.restClient.addZoneToHeaders(headers, this.assetRestConfig.getZoneId());
        @SuppressWarnings("unused")
        List<Asset> list = this.assetFactory.getAssetsByFilter(null, "group", //$NON-NLS-1$
                "/group/gent_belgium", headers);//$NON-NLS-1$
        //// $NON-NLS-1$log.debug(list.toString());
    }

    /**
     * -
     * 
     * @param headers
     *            -
     */
    public void testCreateAssetsWithClassifications(List<Header> headers)
    {
        // create Classification first :
        Classification classification = new Classification();
        classification.setName("GEMach");//$NON-NLS-1$
        classification.setUri("/classification/GasTurbineTest");//$NON-NLS-1$
        classification.setDescription("Creating classification GasTurbineTest for testing purpose");//$NON-NLS-1$
        classification.setObsolete(false);
        HttpResponse response = this.classificationFactory.createClassification(classification, headers);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_NO_CONTENT);

        Asset asset = new Asset();
        asset.setAssetId("geturbine_1234");//$NON-NLS-1$
        asset.setDescription("wind turbine 3 blades");//$NON-NLS-1$
        asset.setUri("/asset/getrb_1234");//$NON-NLS-1$
        asset.setAttributes(createTestArributeMapForTurbine());
        asset.setClassificationUri("/classification/GasTurbineTest");//$NON-NLS-1$

        response = this.assetFactory.createAsset(asset, headers);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_NO_CONTENT);

        Asset asset2 = new Asset();
        asset2.setAssetId("geturbine_2345");//$NON-NLS-1$
        asset2.setDescription("water turbine 4 blades");//$NON-NLS-1$
        asset2.setUri("/asset/getrb_2345");//$NON-NLS-1$
        asset2.setAttributes(createTestArributeMapForTurbine());
        asset2.setClassificationUri("/classification/GasTurbineTest");//$NON-NLS-1$

        response = this.assetFactory.createAsset(asset2, headers);
    }

    /**
     * -
     * 
     * @param headers
     *            -
     */
    @SuppressWarnings("resource")
    public void testUpdateAsset(List<Header> headers)
    {
        Asset asset = this.assetFactory.getAsset("getrb_1234", headers);//$NON-NLS-1$
        asset.setDescription("wind turbine 3 blades--Updated");//$NON-NLS-1$
        asset.setParentUri("/asset/getrb_2");//$NON-NLS-1$
        CloseableHttpResponse response = (CloseableHttpResponse) this.assetFactory.updateAsset(asset, headers);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_NO_CONTENT);

        Asset uAsset = this.assetFactory.getAsset("getrb_1234", headers);//$NON-NLS-1$
        assertEquals("wind turbine 3 blades--Updated", uAsset.getDescription());//$NON-NLS-1$
    }

    /**
     * -
     * 
     * @param headers
     *            -
     */
    @SuppressWarnings(
    {
            "unchecked", "unused"
    })
    public void testAssociateTagWithAsset(List<Header> headers)
    {
        Asset asset = this.assetFactory.getAsset("getrb_1234", headers);//$NON-NLS-1$

        AssetTag aTag = new AssetTag();
        aTag.setTagUri("/tag/pressure");//$NON-NLS-1$
        aTag.setOutputMaximum(2.5);
        aTag.setOutputMinimum(1.5);
        aTag.setSourceTagId("getrb_1234.pressure");//$NON-NLS-1$
        Map map = new Map();
        map.put("PRESSURE", aTag);//$NON-NLS-1$
        asset.setAssetTag(map);

        String json = this.jsonMapper.toJson(asset);
        // json = jsonMapper.toJson(aTag);

        HttpResponse response = this.assetFactory.updateAsset(asset, headers);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_NO_CONTENT);
        Asset uAsset = this.assetFactory.getAsset("getrb_1234", headers);//$NON-NLS-1$
        AssetTag returnedATag = (AssetTag) uAsset.getAssetTag().get("PRESSURE"); //$NON-NLS-1$
        assertEquals("getrb_1234.pressure", returnedATag.getSourceTagId());//$NON-NLS-1$
        assertEquals("/tag/pressure", returnedATag.getTagUri());//$NON-NLS-1$

    }

    /**
     * -
     * 
     * @param headers
     *            -
     */
    public void testGetChildAssets(List<Header> headers)
    {
        List<Asset> list = this.assetFactory.getAssetsByFilter(null, "parentUri", //$NON-NLS-1$
                "/asset/getrb_2", headers);//$NON-NLS-1$
        assertEquals(1, list.size());
        assertEquals("/asset/getrb_1234", list.get(0).getUri());//$NON-NLS-1$
    }

    /**
     * -
     * 
     * @param headers
     *            -
     */
    public void testGetAssetsWithSameClassification(List<Header> headers)
    {
        List<Asset> list = this.assetFactory.getAssetsByFilter(null, "classificationUri", //$NON-NLS-1$
                "/classification/GasTurbineTest", //$NON-NLS-1$
                headers);
        assertEquals(2, list.size());
    }

    /**
     * -
     * 
     * @param headers
     *            -
     */
    public void testDeleteAssets(List<Header> headers)
    {
        this.assetFactory.deleteAsset("getrb_2345", headers);//$NON-NLS-1$
        this.assetFactory.deleteAsset("getrb_1234", headers);//$NON-NLS-1$
        this.assetFactory.deleteAsset("getrb_2", headers);//$NON-NLS-1$
        this.assetFactory.deleteAsset("getrb_3", headers);//$NON-NLS-1$
    }

    /**
     * @return -
     */
    @SuppressWarnings("unchecked")
    public Map createTestArributeMapForTurbine()
    {
        Map attributeMap = new Map();
        Attribute dimensions = new Attribute();
        dimensions.setType("string");//$NON-NLS-1$
        dimensions.setRequired(true);
        dimensions.setDisplay(true);

        List<Object> size = new ArrayList<Object>();
        size.add("5mx1mx15m");//$NON-NLS-1$
        dimensions.setValue(size);

        // Notice this attribute is from classification
        Attribute make = new Attribute();
        make.setType("string");//$NON-NLS-1$
        make.setRequired(true);
        make.setDisplay(true);

        List<Object> makes = new ArrayList<Object>();
        makes.add("Toyota");//$NON-NLS-1$
        make.setValue(makes);

        attributeMap.put("make", make);//$NON-NLS-1$
        attributeMap.put("dimensions", dimensions);//$NON-NLS-1$
        return attributeMap;
    }

}
