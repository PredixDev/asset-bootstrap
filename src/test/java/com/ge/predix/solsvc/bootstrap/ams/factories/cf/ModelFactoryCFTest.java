package com.ge.predix.solsvc.bootstrap.ams.factories.cf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ge.predix.solsvc.bootstrap.ams.common.IAssetConfig;
import com.ge.predix.solsvc.bootstrap.ams.factories.ModelFactory;
import com.ge.predix.solsvc.bootstrap.ams.factories.cf.testclasses.JetEngine;
import com.ge.predix.solsvc.bootstrap.ams.factories.cf.testclasses.JetEngineNoModel;
import com.ge.predix.solsvc.bootstrap.ams.factories.cf.testclasses.JetEnginePart;
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
public class ModelFactoryCFTest {

	private static final Logger log = LoggerFactory
			.getLogger(ModelFactoryCFTest.class);

	@Mock
	private RestClient restClient;

	@Autowired
	private ModelFactory modelFactory;

	@Autowired
	private IAssetConfig assetRestConfig;

	@Autowired
	private JsonMapper jsonMapper;

	private CloseableHttpResponse response;

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
		response = Mockito.mock(CloseableHttpResponse.class);
	}

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
	 * 
	 * @throws IOException
	 *             -
	 * @throws IllegalStateException
	 *             -
	 */
	@SuppressWarnings("nls")
	@Test
	public void testModelCRUD() throws IllegalStateException, IOException {
		/*
		 * ProtocolVersion proto = new ProtocolVersion("HTTP", 1, 1);
		 * BasicStatusLine line = new BasicStatusLine(proto,
		 * HttpStatus.SC_NO_CONTENT, "test reason"); CloseableHttpResponse
		 * response = (CloseableHttpResponse) new BasicHttpResponse(line);
		 */

		Mockito.when(
				this.restClient.delete(Matchers.anyString(),
						Matchers.anyListOf(Header.class), Matchers.anyInt(), Matchers.anyInt())).thenReturn(response);
		Mockito.when(
				this.restClient.put(Matchers.anyString(), Matchers.anyString(),
						Matchers.anyListOf(Header.class),Matchers.anyInt(), Matchers.anyInt())).thenReturn(response);
		Mockito.when(this.restClient.hasToken(Matchers.anyListOf(Header.class)))
				.thenReturn(true);
		Mockito.when(
				this.restClient.hasZoneId(Matchers.anyListOf(Header.class)))
				.thenReturn(true);

		List<Header> headers = this.restClient.getSecureTokenForClientId();
		this.restClient.addZoneToHeaders(headers,
				this.assetRestConfig.getZoneId());

		// testDeleteModel(headers);
		// testUpdateSimpleModel(headers);
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

		HttpResponse resp = this.modelFactory.createModel(engines, headers);
		assertNotNull(resp);
		assertEquals(HttpStatus.SC_NO_CONTENT, resp.getStatusLine()
				.getStatusCode());
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

		@SuppressWarnings("unused")
		String body = this.jsonMapper.toJson(engine1);

		JetEnginePart jetEnginePart = new JetEnginePart();
		jetEnginePart.setUri("/part/pt9876");
		jetEnginePart.setsNo(55555);
		engine1.setJetEnginePart(jetEnginePart);

		ProtocolVersion proto = new ProtocolVersion("HTTP", 1, 1);
		BasicStatusLine line = new BasicStatusLine(proto,
				HttpStatus.SC_NO_CONTENT, "test reason");
		HttpResponse response = new BasicHttpResponse(line);
		Mockito.when(this.modelFactory.updateModel(engine1, headers))
				.thenReturn((CloseableHttpResponse) response);

		HttpResponse resp = this.modelFactory.updateModel(engine1, headers);
		assertNotNull(resp);
		assertEquals(HttpStatus.SC_NO_CONTENT, resp.getStatusLine()
				.getStatusCode());
	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 * @throws IOException
	 *             -
	 * @throws IllegalStateException
	 *             -
	 */
	@SuppressWarnings("nls")
	public void testGetModel(List<Header> headers)
			throws IllegalStateException, IOException {
		/*
		 * ProtocolVersion proto = new ProtocolVersion("HTTP", 1, 1);
		 * BasicStatusLine line = new BasicStatusLine(proto, HttpStatus.SC_OK,
		 * "test reason"); HttpResponse response = new BasicHttpResponse(line);
		 */
		Mockito.when(response.getStatusLine()).thenReturn(
				new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1),
						HttpStatus.SC_OK, "test reason!"));
		HttpEntity entity = Mockito.mock(HttpEntity.class);
		response.setEntity(entity);
		Mockito.when(
				this.restClient.get(Matchers.anyString(),
						Matchers.anyListOf(Header.class),Matchers.anyInt(), Matchers.anyInt())).thenReturn(response);
		String body = "[{\"uri\":\"/engine/ENG1.23\",\"serialNo\":12345}]";
		InputStream stream = new ByteArrayInputStream(body.getBytes());
		Mockito.when(entity.getContent()).thenReturn(stream);
		Mockito.when(entity.getContentLength()).thenReturn(
				new Long(body.length()));
		Mockito.when(response.getEntity()).thenReturn(entity);

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
	 * @throws IOException
	 *             -
	 * @throws IllegalStateException
	 *             -
	 */
	@SuppressWarnings("nls")
	public void testGetAllModels(List<Header> headers)
			throws IllegalStateException, IOException {
		/*
		 * ProtocolVersion proto = new ProtocolVersion("HTTP", 1, 1);
		 * BasicStatusLine line = new BasicStatusLine(proto, HttpStatus.SC_OK,
		 * "test reason2"); HttpResponse response = new BasicHttpResponse(line);
		 */
		Mockito.when(response.getStatusLine()).thenReturn(
				new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1),
						HttpStatus.SC_OK, "test reason!"));

		HttpEntity entity = Mockito.mock(HttpEntity.class);
		response.setEntity(entity);
		Mockito.when(
				this.restClient.get(Matchers.anyString(),
						Matchers.anyListOf(Header.class),Matchers.anyInt(), Matchers.anyInt())).thenReturn(
				(CloseableHttpResponse) response);
		String body = "[{\"uri\":\"/engine/ENG1.23\",\"serialNo\":12345},{\"uri\":\"/engine/ENG1.23\",\"serialNo\":12345}]";
		InputStream stream = new ByteArrayInputStream(body.getBytes());
		Mockito.when(entity.getContent()).thenReturn(stream);
		Mockito.when(entity.getContentLength()).thenReturn(
				new Long(body.length()));
		Mockito.when(response.getEntity()).thenReturn(entity);

		List<JetEngineNoModel> engines = this.modelFactory.getModels("/engine",
				"engine", JetEngineNoModel.class, headers);
		assertNotNull(engines);
		assertTrue(engines.size() > 1);
	}

	/**
	 * -
	 * 
	 * @param headers
	 *            -
	 */
	@SuppressWarnings("nls")
	public void testDeleteModel(List<Header> headers) {
		/*
		 * ProtocolVersion proto = new ProtocolVersion("HTTP", 1, 1);
		 * BasicStatusLine line = new BasicStatusLine(proto,
		 * HttpStatus.SC_NO_CONTENT, "test reason"); HttpResponse response = new
		 * BasicHttpResponse(line);
		 */
		Mockito.when(response.getStatusLine()).thenReturn(
				new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1),
						HttpStatus.SC_NO_CONTENT, "FINE!"));
		Mockito.when(
				this.modelFactory.deleteModel(Matchers.anyString(),
						Matchers.anyListOf(Header.class))).thenReturn(response);
		response = this.modelFactory.deleteModel("/engine/ENG1.23", headers);
		assertEquals(HttpStatus.SC_NO_CONTENT, response.getStatusLine()
				.getStatusCode());
		response = this.modelFactory.deleteModel("/engine/ENG2.23", headers);
		assertEquals(HttpStatus.SC_NO_CONTENT, response.getStatusLine()
				.getStatusCode());
	}

}
