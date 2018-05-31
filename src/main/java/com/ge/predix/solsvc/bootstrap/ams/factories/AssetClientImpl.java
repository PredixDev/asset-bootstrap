package com.ge.predix.solsvc.bootstrap.ams.factories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ge.predix.entity.model.Model;
import com.ge.predix.solsvc.bootstrap.ams.common.IAssetConfig;
import com.ge.predix.solsvc.bootstrap.ams.dto.CustomModel;
import com.ge.predix.solsvc.bootstrap.ams.integration.HttpResponseException;
import com.ge.predix.solsvc.bootstrap.ams.integration.RestConstants;
import com.ge.predix.solsvc.ext.util.JsonMapper;
import com.ge.predix.solsvc.restclient.config.IOauthRestConfig;
import com.ge.predix.solsvc.restclient.impl.RestClient;

/**
 * 
 * @author 212438846
 */
@Component(value = "AssetClient")
@Scope("prototype")
public class AssetClientImpl implements AssetClient, RestConstants {

	private static Logger log = LoggerFactory.getLogger(AssetClientImpl.class);

	/**
	 * 
	 */
	@Autowired
	protected RestMarshal restMarshal;

	/**
	 * 
	 */
	@Autowired
	@Qualifier("assetRestConfig")
	protected IAssetConfig assetConfig;


	/**
	 * 
	 */
	@Autowired
	protected RestClient restClient;

	/**
	 * 
	 */
	@Autowired
	protected JsonMapper jsonMapper;

	/**
	 * -
	 */
	@PostConstruct
	public void init() {
		this.jsonMapper.addAllXmlSeeAlsoSubtypes(CustomModel.class);
		this.restMarshal.setUpRestMarshal(this.assetConfig);
	}
	
	/**
	 * @return the assetConfig
	 */
	@Override
	public IAssetConfig getAssetConfig() {
		return this.assetConfig;
	}

	/**
	 * @param restClient
	 *            the restClient to set
	 */
	@Override
	public void setRestClient(RestClient restClient) {
		this.restClient = restClient;
	}

	/**
	 * @return the restClient
	 */
	public RestClient getRestClient() {
		return this.restClient;
	}

	@Override
	public CloseableHttpResponse createModelFromJson(String json, List<Header> headers) {
		CloseableHttpResponse response = null;
		try {
			List<Model> models = this.jsonMapper.fromJsonArray(json, Model.class);
			response = createCustomModels(models, json, headers);
			boolean expected = (response == null || response.getStatusLine() == null
					|| response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT) ? false : true;
			if (!expected)
				handleException(this.restMarshal.createCustomModelUrlForPost(models.get(0)), models, headers, response);
		} finally {
			if (response != null)
				try {
					response.close();
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
		}
		return response;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ge.predix.solsvc.bootstrap.ams.factories.AssetClient#createFromJson(
	 * java .lang.String, java.util.List)
	 */
	@Override
	public CloseableHttpResponse createFromJson(String resource, String json, List<Header> headers) {
		CloseableHttpResponse response = null;
		try {
			List<Header> localheaders = ensureDefaultHeadersArePresent(headers);
			String url = this.restMarshal.getAssetUri(resource);
			response = this.restClient.post(url, json, localheaders, this.assetConfig.getAssetConnectionTimeout(),
					this.assetConfig.getAssetSocketTimeout());
			boolean expected = (response == null || response.getStatusLine() == null
					|| response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT) ? false : true;
			if (!expected)
				handleException(url, url, headers, response);
		} finally {
			if (response != null)
				try {
					response.close();
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
		}
		return response;
	}

	@Override
	public <T> CloseableHttpResponse createModel(List<T> objs, List<Header> headers) {
		CloseableHttpResponse response = null;
		try {
			response = createCustomModels(objs, headers);
			boolean expected = (response == null || response.getStatusLine() == null
					|| response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT) ? false : true;
			if (!expected)
				handleException(this.restMarshal.createCustomModelUrlForPost(objs.get(0)), objs, headers, response);
		} finally {
			if (response != null)
				try {
					response.close();
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
		}
		return response;
	}

	@Override
	public <T> CloseableHttpResponse updateModel(T obj, List<Header> headers) {
		CloseableHttpResponse response = null;
		try {
			response = updateCustomModel(obj, headers);
			boolean expected = (response == null || response.getStatusLine() == null
					|| response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT) ? false : true;
			String url = this.assetConfig.getAssetUri();
			if (!expected)
				handleException(url, obj, headers, response);
		} finally {
			if (response != null)
				try {
					response.close();
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
		}
		return response;
	}

	@Override
	public <T> CloseableHttpResponse updateModel(T obj, String modelName, List<Header> headers) {
		CloseableHttpResponse response = null;
		try {
			response = updateCustomModel(obj, modelName, headers);
			boolean expected = (response == null || response.getStatusLine() == null
					|| response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT) ? false : true;
			String url = this.assetConfig.getAssetUri();
			if (!expected)
				handleException(url, obj, headers, response);
		} finally {
			if (response != null)
				try {
					response.close();
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
		}
		return response;
	}

	@SuppressWarnings({ "nls", "rawtypes" })
	@Override
	public List<String> getModels(String filter, List<Header> headers) {
		CloseableHttpResponse response = null;
		try {
			response = getCustomModels(filter, headers);
			if (response == null || response.getStatusLine() == null
					|| (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK
							&& response.getStatusLine().getStatusCode() != HttpStatus.SC_NOT_FOUND)) {
				String uri = this.assetConfig.getAssetUri();
				handleException(uri, null, headers, response);
			}
			try {
				@SuppressWarnings("null")
				String body = EntityUtils.toString(response.getEntity());
				List<LinkedHashMap> items = this.jsonMapper.fromJsonArray(body, LinkedHashMap.class);
				ArrayList<String> result = new ArrayList<String>();
				for (LinkedHashMap lhm : items) {
					result.add(this.jsonMapper.toJson(lhm));

				}
				return result;
			} catch (ParseException | IOException e) {
				throw new RuntimeException("Failed to unmarshall response. ", e); //$NON-NLS-1$
			}
		} finally {
			if (response != null)
				try {
					response.close();
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ge.predix.solsvc.bootstrap.ams.factories.AssetClient#getModels(java.
	 * lang.String, java.lang.String, java.util.List)
	 */
	@SuppressWarnings({ "nls", "unchecked" })
	@Override
	public <T> List<T> getModels(String filter, Class<T> complexType, List<Header> headers) {
		CloseableHttpResponse response = null;
		try {
			response = getCustomModels(filter, headers);
			if (response == null || response.getStatusLine() == null
					|| (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK
							&& response.getStatusLine().getStatusCode() != HttpStatus.SC_NOT_FOUND
							&& response.getStatusLine().getStatusCode() != HttpStatus.SC_PARTIAL_CONTENT)) {
				String uri = this.assetConfig.getAssetUri();
				handleException(uri, complexType, headers, response);
			}
			try {
				String body = EntityUtils.toString(response.getEntity());
				if (body.equals("[]")) //$NON-NLS-1$
					return null;
				List<Object> models = new ArrayList<Object>();
				List<T> mappedObject2 = this.jsonMapper.fromJsonArray(body, complexType);
				for (Object listItem : mappedObject2) {
					if (listItem instanceof LinkedHashMap) {
						@SuppressWarnings({ "rawtypes" })
						LinkedHashMap map = (LinkedHashMap) listItem;
						LinkedHashMapModel linkedModel = new LinkedHashMapModel();
						linkedModel.setMap(map);
						models.add(linkedModel);
					} else {
						models.add(listItem);
					}
				}
				return (List<T>) models;
			} catch (ParseException | IOException e) {
				throw new RuntimeException("Failed to unmarshall response. ", e); //$NON-NLS-1$
			}
		} finally {
			if (response != null)
				try {
					response.close();
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ge.predix.solsvc.bootstrap.ams.factories.AssetClient#getModels(java.
	 * lang.String, java.lang.String, java.util.List)
	 * 
	 * This can be now renamed to getModelsByFilter as we are generalizing this
	 * to all domain objects now.
	 * 
	 */
	@SuppressWarnings({ "nls", "unchecked" })
	@Override
	public List<Object> getModels(String filter, String complexType, List<Header> headers) {
		CloseableHttpResponse response = null;
		try {
			response = getCustomModels(filter, headers);
			if (response == null || response.getStatusLine() == null
					|| (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK
							&& response.getStatusLine().getStatusCode() != HttpStatus.SC_NOT_FOUND
							&& response.getStatusLine().getStatusCode() != HttpStatus.SC_PARTIAL_CONTENT)) {
				String uri = this.assetConfig.getAssetUri();
				handleException(uri, complexType, headers, response);
			}
			try {
				String body = EntityUtils.toString(response.getEntity());
				if (body.equals("[]")) //$NON-NLS-1$
					return null;
				List<Object> models = new ArrayList<Object>();
				List<Object> mappedObject2 = this.jsonMapper.fromJsonArray(body, complexType);
				for (Object listItem : mappedObject2) {
					if (listItem instanceof LinkedHashMap) {
						@SuppressWarnings({ "rawtypes" })
						LinkedHashMap map = (LinkedHashMap) listItem;
						LinkedHashMapModel linkedModel = new LinkedHashMapModel();
						linkedModel.setMap(map);
						models.add(linkedModel);
					} else {
						models.add(listItem);
					}
				}
				return models;
			} catch (ParseException | IOException e) {
				throw new RuntimeException("Failed to unmarshall response. ", e); //$NON-NLS-1$
			}
		} finally {
			if (response != null)
				try {
					response.close();
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
		}
	}

	@Override
	public <T> List<T> getModelsByFilter(Class<T> complexType, String pathParam, String filter, String value,
			List<Header> headers) {

		CloseableHttpResponse response = null;
		log.debug("From getModels byFilter method: "); //$NON-NLS-1$
		log.debug("Complex type value is : " + complexType); //$NON-NLS-1$
		log.debug("filter value is : " + filter); //$NON-NLS-1$
		log.debug("value value is : " + value); //$NON-NLS-1$

		try {
			response = get(complexType, pathParam, filter, value, headers);

			if (response == null || response.getStatusLine() == null
					|| (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK
							&& response.getStatusLine().getStatusCode() != HttpStatus.SC_NOT_FOUND
							&& response.getStatusLine().getStatusCode() != HttpStatus.SC_PARTIAL_CONTENT)) {
				String uri = this.assetConfig.getAssetUri();
				handleException(uri, complexType, headers, response);
			}
			try {
				String body = EntityUtils.toString(response.getEntity());
				if (body.equals("[]")) //$NON-NLS-1$
					return null;
				List<Object> models = new ArrayList<Object>();
				List<T> mappedObject2 = this.jsonMapper.fromJsonArray(body, complexType);
				for (Object listItem : mappedObject2) {
					if (listItem instanceof LinkedHashMap) {
						@SuppressWarnings({ "rawtypes" })
						LinkedHashMap map = (LinkedHashMap) listItem;
						LinkedHashMapModel linkedModel = new LinkedHashMapModel();
						linkedModel.setMap(map);
						models.add(linkedModel);
					} else {
						models.add(listItem);
					}
				}
				return (List<T>) models;
			} catch (ParseException | IOException e) {
				throw new RuntimeException("Failed to unmarshall response. ", e); //$NON-NLS-1$
			}
		} finally {
			if (response != null)
				try {
					response.close();
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
		}
	}

	@SuppressWarnings("nls")
	@Override
	public CloseableHttpResponse deleteModel(String modelUri, List<Header> headers) {
		CloseableHttpResponse response = null;
		try {
			String assetUri = this.assetConfig.getAssetUri();
			if (!assetUri.endsWith("/") && !modelUri.startsWith("/")) //$NON-NLS-1$ //$NON-NLS-2$
				assetUri += "/" + modelUri; //$NON-NLS-1$
			else
				assetUri += modelUri;
			response = delete(assetUri, headers);
			boolean expected = (response == null || response.getStatusLine() == null
					|| response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT) ? false : true;
			if (!expected)
				handleException(assetUri, assetUri, headers, response);
		} finally {
			if (response != null)
				try {
					response.close();
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
		}
		return response;
	}

	@Override
	public List<Header> setZoneIdInHeaders(List<Header> headers) {
		this.restClient.addZoneToHeaders(headers, this.assetConfig.getZoneId());
		return headers;
	}

	/**
	 * @param clazz
	 *            -
	 * @param httpResponse
	 *            -
	 * @param <T>
	 *            -
	 * @return T -
	 * @throws IOException
	 *             -
	 * @throws ParseException
	 *             -
	 */
	@SuppressWarnings("nls")
	protected <T> List<T> getObjectFromJson(Class<T> clazz, HttpResponse httpResponse)
			throws ParseException, IOException {
		org.apache.http.HttpEntity responseEntity = httpResponse.getEntity();
		String json = EntityUtils.toString(responseEntity);
		log.debug("getObjectFromJson clazz=" + clazz + " response=" + json); //$NON-NLS-1$ //$NON-NLS-2$

		return this.jsonMapper.fromJsonArray(json, clazz);
	}

	/**
	 * @param object
	 *            -
	 * @param headers
	 *            -
	 * @param <T>
	 *            -
	 * @return T -
	 */
	public <T> CloseableHttpResponse create(T object, List<Header> headers) {
		if (object == null)
			return null;
		List<T> list = new ArrayList<>();
		list.add(object);
		return create(list, headers);
	}

	/**
	 * This is the original method for Classification, Groups etc TODO: delete
	 * this and the "map" (see this.config.getPath()) in favor of
	 * createCustomModels
	 * 
	 * @param objects
	 *            -
	 * @param headers
	 *            -
	 * @param <T>
	 *            -
	 * @return T -
	 */
	@SuppressWarnings("nls")
	public <T> CloseableHttpResponse create(List<T> objects, List<Header> headers) {
		if (objects == null || objects.get(0) == null)
			return null;
		List<Header> localheaders = ensureDefaultHeadersArePresent(headers);
		String url = this.restMarshal.getPath(objects.get(0).getClass());
		String body = this.jsonMapper.toJson(objects);
		log.debug("url=" + url + " create json=" + body); //$NON-NLS-1$ //$NON-NLS-2$
		CloseableHttpResponse response = this.restClient.post(url, body, localheaders,
				this.assetConfig.getAssetConnectionTimeout(), this.assetConfig.getAssetSocketTimeout());
		return response;
	}

	/**
	 * @param objects
	 *            -
	 * @param headers
	 *            -
	 * @param <T>
	 *            -
	 * @return T -
	 */
	@SuppressWarnings({ "nls" })
	public <T> CloseableHttpResponse createCustomModels(List<T> objects, List<Header> headers) {
		if (objects == null || objects.get(0) == null)
			return null;
		List<Header> localheaders = ensureDefaultHeadersArePresent(headers);
		String url = this.restMarshal.createCustomModelUrlForPost(objects.get(0));
		String json = this.jsonMapper.toJson(objects);
		log.debug("url=" + url + " create json=" + json); //$NON-NLS-1$ //$NON-NLS-2$
		CloseableHttpResponse response = this.restClient.post(url, json, localheaders,
				this.assetConfig.getAssetConnectionTimeout(), this.assetConfig.getAssetSocketTimeout());
		return response;
	}

	/**
	 * @param objects
	 *            -
	 * @param jsonString
	 *            body
	 * @param headers
	 *            -
	 * @param <T>
	 *            -
	 * @return T -
	 */
	public <T> CloseableHttpResponse createCustomModels(List<T> objects, String jsonString, List<Header> headers) {
		List<Header> localheaders = ensureDefaultHeadersArePresent(headers);
		String url = this.restMarshal.createCustomModelUrlForPost(objects.get(0));
		CloseableHttpResponse response = this.restClient.post(url, jsonString, localheaders,
				this.assetConfig.getAssetConnectionTimeout(), this.assetConfig.getAssetSocketTimeout());
		return response;
	}

	/**
	 * @param object
	 *            -
	 * @param headers
	 *            -
	 * 
	 * @param <T>
	 *            -
	 * @return T -
	 */
	@SuppressWarnings("nls")
	public <T> CloseableHttpResponse updateCustomModel(T object, List<Header> headers) {
		if (object == null)
			return null;

		// String url = this.config.createCustomModelUrlForPost(objects.get(0));
		String url = this.restMarshal.createCustomModelUrlForPut(object);
		List<Header> localheaders = ensureDefaultHeadersArePresent(headers);
		String json = this.jsonMapper.toJson(Arrays.asList(object));
		log.debug("url=" + url + " create json=" + json); //$NON-NLS-1$ //$NON-NLS-2$
		CloseableHttpResponse response = this.restClient.put(url, json, localheaders,
				this.assetConfig.getAssetConnectionTimeout(), this.assetConfig.getAssetSocketTimeout());

		return response;
	}

	/**
	 * @param object
	 *            -
	 * @param complexType
	 *            -
	 * @param headers
	 *            -
	 * @param <T>
	 *            -
	 * @return T
	 */
	@SuppressWarnings("nls")
	public <T> CloseableHttpResponse updateCustomModel(T object, String complexType, List<Header> headers) {
		if (object == null)
			return null;

		// String url = this.config.createCustomModelUrlForPost(objects.get(0));
		String url = null;
		String json = null;
		if (object instanceof String) {
			Object model = this.jsonMapper.fromJson((String) object, Object.class);
			url = this.restMarshal.createCustomModelUrlForPut(model, complexType);
			json = this.jsonMapper.toJson(Arrays.asList(model));
		} else {
			url = this.restMarshal.createCustomModelUrlForPut(object, complexType);
			json = this.jsonMapper.toJson(Arrays.asList(object));
		}
		List<Header> localheaders = ensureDefaultHeadersArePresent(headers);
		log.debug("UUUurl=" + url + " create json=" + json); //$NON-NLS-1$ //$NON-NLS-2$
		CloseableHttpResponse response = this.restClient.put(url, json, localheaders,
				this.assetConfig.getAssetConnectionTimeout(), this.assetConfig.getAssetSocketTimeout());

		return response;
	}

	private List<Header> ensureDefaultHeadersArePresent(List<Header> headers) {
		List<Header> localHeaders = headers;
		boolean contentTypeFound = false;
		for (Header header : headers) {
			if (header.getName().equals("Content-Type")) { //$NON-NLS-1$
				contentTypeFound = true;
				break;
			}
		}
		if (!contentTypeFound) {
			localHeaders.add(new BasicHeader("Content-Type", "application/json")); //$NON-NLS-1$//$NON-NLS-2$
		}
		return localHeaders;
	}

	/**
	 * @param clazz
	 *            Class
	 * @param uriSegment
	 *            uRL
	 * @param jsonString
	 *            body
	 * @param headers
	 *            -
	 * @return HttpResponse
	 */
	public CloseableHttpResponse create(Class<?> clazz, String uriSegment, String jsonString, List<Header> headers) {
		List<Header> localheaders = ensureDefaultHeadersArePresent(headers);
		String url = this.restMarshal.getPath(clazz) + uriSegment;
		CloseableHttpResponse response = this.restClient.post(url, jsonString, localheaders,
				this.assetConfig.getAssetConnectionTimeout(), this.assetConfig.getAssetSocketTimeout());
		return response;
	}

	/**
	 * 
	 * @param object
	 *            T
	 * @param headers
	 *            -
	 * @param <T>
	 *            -
	 * @return T
	 */
	public <T> HttpResponse update(T object, List<Header> headers) {
		if (object == null)
			return null;
		List<T> list = new ArrayList<>();
		list.add(object);
		return update(list, headers);
	}

	/**
	 * 
	 * @param clazz
	 *            -
	 * @param headers
	 *            -
	 * @return HttpResponse
	 */
	public CloseableHttpResponse get(Class<?> clazz, List<Header> headers) {
		// config.setupRestMarshal(context);
		String url = this.restMarshal.getPath(clazz);
		return this.restClient.get(url, headers, this.assetConfig.getAssetConnectionTimeout(),
				this.assetConfig.getAssetSocketTimeout());
	}

	/**
	 * 
	 * @param clazz
	 *            -
	 * 
	 * @param pathParam
	 *            String
	 * @param headers
	 *            headers
	 * @return HttpResponse
	 */
	@SuppressWarnings("nls")
	public CloseableHttpResponse get(Class<?> clazz, String pathParam, List<Header> headers) {
		// config.setupRestMarshal(context);
		String url = this.restMarshal.getPath(clazz);
		if (pathParam != null)
			url += "/" + pathParam; //$NON-NLS-1$
		if (!this.restClient.hasToken(headers))
			throw new UnsupportedOperationException("calls to Predix Asset require an Authorization token header"); //$NON-NLS-1$
		if (!this.restClient.hasZoneId(headers))
			throw new UnsupportedOperationException("calls to Predix Asset require an Predix-Zone-Id header"); //$NON-NLS-1$
		log.debug("Calling URL From Asset BootStrap -->" + url); //$NON-NLS-1$
		return this.restClient.get(url, headers, this.assetConfig.getAssetConnectionTimeout(),
				this.assetConfig.getAssetSocketTimeout());
	}

	/**
	 * @param clazz
	 *            -
	 * @param pathParam
	 *            -
	 * @param filter
	 *            -
	 * @param value
	 *            -
	 * @param headers
	 *            -
	 * @return -
	 */
	public CloseableHttpResponse get(Class<?> clazz, String pathParam, String filter, String value,
			List<Header> headers) {
		String pathSegment = pathParam;
		if (pathParam == null) {
			pathSegment = new String(""); //$NON-NLS-1$
		}
		String url = this.restMarshal.getPath(clazz) + "/" + pathSegment + "?filter=" //$NON-NLS-1$//$NON-NLS-2$
				+ filter + "=" + value; //$NON-NLS-1$
		return this.restClient.get(url, headers, this.assetConfig.getAssetConnectionTimeout(),
				this.assetConfig.getAssetSocketTimeout());
	}

	/**
	 * @param filterUri
	 *            -
	 * @param headers
	 *            -
	 * @return -
	 */
	@SuppressWarnings("nls")
	public CloseableHttpResponse getCustomModels(String filterUri, List<Header> headers) {
		List<Header> localHeaders = ensureDefaultHeadersArePresent(headers);
		if (!this.restClient.hasToken(headers))
			throw new UnsupportedOperationException("calls to Predix Asset require an Authorization token header"); //$NON-NLS-1$
		if (!this.restClient.hasZoneId(headers))
			throw new UnsupportedOperationException("calls to Predix Asset require an Predix-Zone-Id header"); //$NON-NLS-1$
		String assetUri = this.assetConfig.getAssetUri();
		if (!assetUri.endsWith("/") && filterUri != null && !filterUri.startsWith("/")) //$NON-NLS-1$ //$NON-NLS-2$
			assetUri += "/" + filterUri; //$NON-NLS-1$
		else if (filterUri != null)
			assetUri += filterUri;
		return this.restClient.get(assetUri, localHeaders, this.assetConfig.getAssetConnectionTimeout(),
				this.assetConfig.getAssetSocketTimeout());
	}

	/**
	 * 
	 * @param clazz
	 *            -
	 * 
	 * @param pathParam
	 *            pathParam
	 * @param headers
	 *            -
	 * @return HttpResponse
	 */
	public CloseableHttpResponse delete(Class<?> clazz, String pathParam, List<Header> headers) {
		// config.setupRestMarshal(context);
		String url = this.restMarshal.getPath(clazz) + "/" + pathParam; //$NON-NLS-1$
		return this.restClient.delete(url, headers, this.assetConfig.getAssetConnectionTimeout(),
				this.assetConfig.getAssetSocketTimeout());
	}

	/**
	 * @param uri
	 *            -
	 * @param headers
	 *            -
	 * @return -
	 */
	public CloseableHttpResponse delete(String uri, List<Header> headers) {
		return this.restClient.delete(uri, headers, this.assetConfig.getAssetConnectionTimeout(),
				this.assetConfig.getAssetSocketTimeout());
	}

	/**
	 * @param config
	 *            the config to set
	 */
	public void setConfig(RestMarshal config) {
		this.restMarshal = config;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ge.predix.solsvc.bootstrap.ams.factories.IFixtureFactory#
	 * overrideAssetRestConfig(com.ge.predix.solsvc.bootstrap.ams.common.
	 * IAssetConfig, com.ge.predix.solsvc.restclient.config.IOauthRestConfig)
	 *
	 */
	@Override
	public void overrideAssetRestConfig(IAssetConfig aConfig, IOauthRestConfig ouathConfig) {
		this.assetConfig = aConfig;
		this.restClient.overrideRestConfig(ouathConfig);
		this.restMarshal.setUpRestMarshal(this.assetConfig);
	}

	/**
	 * @param assetUri
	 *            -
	 * @param object
	 *            -
	 * @param headers
	 *            -
	 * @param response
	 *            -
	 */
	@SuppressWarnings("nls")
	protected void handleException(String assetUri, Object object, Object headers, HttpResponse response) {
		HttpEntity entity = response.getEntity();
		String responseBody = null;
		try {
			responseBody = EntityUtils.toString(entity, "UTF-8"); //$NON-NLS-1$
		} catch (ParseException e) {
			log.error("Unable to parse HttpResponse Body, swallowing error since we are throwing a few lines down", e); //$NON-NLS-1$
		} catch (IOException e) {
			log.error("Unable to parse HttpResponse Body, swallowing error since we are throwing a few lines down", e); //$NON-NLS-1$
		}
		throw new HttpResponseException(("Unable to call assetUrl" + assetUri + ".  " + response == null //$NON-NLS-1$ //$NON-NLS-2$
				? "No HttpResponse" : response.toString()) + " Response Body=" + responseBody + " Entity=" + object //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ " Headers=" + headers, response); //$NON-NLS-1$
	}

}
