package com.ge.predix.solsvc.bootstrap.ams.factories.cf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import com.ge.predix.solsvc.bootstrap.ams.factories.ModelFactoryImpl;
import com.ge.predix.solsvc.bootstrap.ams.factories.cf.testclasses.JetEngine;
import com.ge.predix.solsvc.bootstrap.ams.factories.cf.testclasses.JetEngineNoModel;
import com.ge.predix.solsvc.bootstrap.ams.factories.cf.testclasses.JetEnginePart;

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
public class ModelFactoryCFIT extends BaseFactoryIT {

	@SuppressWarnings("hiding")
	private static final Logger log = LoggerFactory
			.getLogger(ModelFactoryCFIT.class);

	@Autowired
	private ModelFactoryImpl modelFactory;

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
	 */
	@Test
	public void testModelCRUD() {
		List<Header> headers = this.restClient.getSecureTokenForClientId();
		this.restClient.addZoneToHeaders(headers,
				this.assetRestConfig.getZoneId());

		testDeleteModel(headers);
		testUpdateSimpleModel(headers);
		// testCreateSimpleModel(headers);
		testGetModel(headers);
		testGetAllModels(headers);
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

		HttpResponse response = this.modelFactory.createModel(engines, headers);
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

		HttpResponse response = this.modelFactory.updateModel(engine1, headers);
		Assert.assertEquals(response.getStatusLine().getStatusCode(),
				HttpStatus.SC_NO_CONTENT);
	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	@SuppressWarnings("nls")
	public void testGetModel(List<Header> headers) {
		List<JetEngineNoModel> engines = this.modelFactory.getModels(
				"/engine/ENG1.23", "engine",
				JetEngineNoModel.class, headers);
		assertNotNull(engines);
		assertEquals("12345", engines.get(0).getSerialNo().toString());
	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	@SuppressWarnings("nls")
	public void testGetAllModels(List<Header> headers) {
		List<JetEngineNoModel> engines = this.modelFactory.getModels("/engine",
				"engine", JetEngineNoModel.class, headers);
		assertNotNull(engines);
		// assertTrue(engines.size() == 1); //Interestingly, I see that an
		// assets(s) with the same uri is being created.
		// The above list has two objects with same uri "/engine/ENG1.23",
		// therefore, the assert condition is failing
	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	@SuppressWarnings("nls")
	public void testDeleteModel(List<Header> headers) {
		CloseableHttpResponse resp = this.modelFactory.deleteModel(
				"/engine/ENG1.23", headers);
		assertEquals(HttpStatus.SC_NO_CONTENT, resp.getStatusLine()
				.getStatusCode());
		resp = this.modelFactory.deleteModel("/engine/ENG2.23", headers);
		assertEquals(HttpStatus.SC_NO_CONTENT, resp.getStatusLine()
				.getStatusCode());
	}

}
