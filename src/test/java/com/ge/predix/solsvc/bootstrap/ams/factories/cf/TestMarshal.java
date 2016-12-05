package com.ge.predix.solsvc.bootstrap.ams.factories.cf;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ge.predix.entity.model.Model;
import com.ge.predix.entity.model.ModelList;
import com.ge.predix.solsvc.bootstrap.ams.common.IAssetConfig;
import com.ge.predix.solsvc.bootstrap.ams.factories.ModelFactory;
import com.ge.predix.solsvc.bootstrap.ams.factories.cf.testclasses.AviationModel;
import com.ge.predix.solsvc.bootstrap.ams.factories.cf.testclasses.JetEngine;
import com.ge.predix.solsvc.bootstrap.ams.factories.cf.testclasses.JetEngineNoModel;
import com.ge.predix.solsvc.bootstrap.ams.factories.cf.testclasses.Pump;
import com.ge.predix.solsvc.ext.util.JsonMapper;
import com.ge.predix.solsvc.restclient.impl.RestClient;

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
@ActiveProfiles(profiles = "local")
@Component
public class TestMarshal {

	private static final Logger log = LoggerFactory
			.getLogger(TestMarshal.class);

	@Mock
	private RestClient restClient;

	@Autowired
	private ModelFactory modelFactory;

	@Autowired
	private IAssetConfig assetRestConfig;

	@Autowired
	private JsonMapper jsonMapper;

	/**
	 * @throws java.lang.Exception
	 *             -
	 */
	@Before
	public void setUp() throws Exception {
		// make sure the correct RestClient is wired to serviceBase
		// It gets changed by mock testing PredixAssetClient
		MockitoAnnotations.initMocks(this);
		this.modelFactory.setRestClient(this.restClient);
	}

	/**
	 * -
	 */
	@SuppressWarnings("nls")
	@Test
	public void testMarshal() {
		Pump pump = new Pump();
		String uri = "/engine/ENG1.23";
		pump.setUri(uri);
		pump.setFlowRate(1.2);

		String json = this.jsonMapper.toJson(pump);
		log.debug(json);
		Assert.assertEquals(json,
				"{\"uri\":\"/engine/ENG1.23\",\"flowRate\":1.2}");

	}

	/**
	 * -
	 */
	@SuppressWarnings("nls")
	@Test
	public void testExtendsModelMarshal() {
		JetEngine engine1 = new JetEngine();
		String uri = "/engine/ENG1.23";
		engine1.setUri(uri);
		engine1.setSerialNo(12345);

		String json = this.jsonMapper.toJson(engine1);
		log.debug(json);
		Assert.assertEquals(json,
				"{\"complexType\":\"JetEngine\",\"uri\":\"/engine/ENG1.23\",\"serialNo\":12345}");
	}

	/**
	 * -
	 */
	@SuppressWarnings("nls")
	@Test
	public void testExtendsModelUnmarshal() {
		JetEngine engine1 = new JetEngine();
		String uri = "/engine/ENG1.23";
		engine1.setUri(uri);
		engine1.setSerialNo(12345);

		String json = this.jsonMapper.toJson(engine1);
		log.debug(json);

		JetEngine engine2 = this.jsonMapper.fromJson(json, JetEngine.class);
		Assert.assertEquals(engine1.getUri(), engine2.getUri());
	}

	/**
	 * - in ReferenceApp we don't know what models you'll acutally create. So
	 * let's be more anonymous.
	 */
	@SuppressWarnings("nls")
	@Test
	public void testPolymorphicModelUnmarshal() {
		ModelList modelList = new ModelList();
		JetEngine engine1 = new JetEngine();
		String uri = "/engine/ENG1.23";
		engine1.setUri(uri);
		engine1.setSerialNo(12345);
		modelList.getModel().add(engine1);

		String json = this.jsonMapper.toJson(modelList);
		log.debug(json);

		this.jsonMapper.addSubtype(JetEngine.class);
		ModelList modelList2 = this.jsonMapper.fromJson(json, ModelList.class);
		Assert.assertEquals(modelList.getModel().get(0), modelList2.getModel()
				.get(0));
	}

	/**
	 * - in ReferenceApp we don't know what models you'll acutally create. So
	 * let's be more anonymous.
	 */
	@SuppressWarnings("nls")
	@Test
	public void testPolymorphicBaseModelUnmarshal() {
		ModelList modelList = new ModelList();
		JetEngine engine1 = new JetEngine();
		String uri = "/engine/ENG1.23";
		engine1.setUri(uri);
		engine1.setSerialNo(12345);
		modelList.getModel().add(engine1);

		String json = this.jsonMapper.toJson(modelList);
		log.debug(json);

		this.jsonMapper.addAllXmlSeeAlsoSubtypes(AviationModel.class);
		ModelList modelList2 = this.jsonMapper.fromJson(json, ModelList.class);
		Assert.assertEquals(modelList.getModel().get(0), modelList2.getModel()
				.get(0));
	}

	/**
	 * - in ReferenceApp we don't know what models you'll acutally create. So
	 * let's be more anonymous.
	 */
	@SuppressWarnings("nls")
	@Test
	public void testPolymorphicBaseModelListUnmarshal() {
		JetEngine engine1 = new JetEngine();
		String uri = "/engine/ENG1.23";
		engine1.setUri(uri);
		engine1.setSerialNo(12345);
		List<Model> models = new ArrayList<Model>();
		models.add(engine1);

		String json = this.jsonMapper.toJson(models);
		log.debug(json);

		this.jsonMapper.addAllXmlSeeAlsoSubtypes(AviationModel.class);
		List<JetEngine> modelList2 = this.jsonMapper.fromJsonArray(json,
				JetEngine.class);
		Assert.assertEquals(((AviationModel) models.get(0)).getSerialNo(),
				modelList2.get(0).getSerialNo());
	}

	/**
	 * - in ReferenceApp we don't know what models you'll acutally create. So
	 * let's be more anonymous.
	 */
	@SuppressWarnings("nls")
	@Test
	public void testNonPolymorphicSaveBeforePolymorphicModelUnmarshal() {
		List<JetEngineNoModel> list = new ArrayList<JetEngineNoModel>();
		JetEngineNoModel engine1 = new JetEngineNoModel();
		String uri = "/engine/ENG1.23";
		engine1.setUri(uri);
		engine1.setSerialNo(12345);
		list.add(engine1);

		String json = this.jsonMapper.toJson(list);
		log.debug(json);

		this.jsonMapper.addAllXmlSeeAlsoSubtypes(AviationModel.class);
		List<JetEngine> modelList2 = this.jsonMapper.fromJsonArray(json,
				JetEngine.class);
		Assert.assertEquals(list.get(0).getSerialNo(), modelList2.get(0)
				.getSerialNo());
	}
}
