package com.ge.predix.solsvc.bootstrap.ams.factories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ge.predix.entity.model.Model;

/**
 * 
 * @author 212438846
 */
@Component(value = "ModelFactory")
@Scope("prototype")
public class ModelFactoryImpl extends FixtureFactory implements ModelFactory {

    private static Logger        log      = LoggerFactory.getLogger(ModelFactoryImpl.class);

	@Override
	public <T> CloseableHttpResponse createModelFromJson(String json, List<Header> headers) {
		CloseableHttpResponse response = null;
		try {
			List<Model> models = this.jsonMapper.fromJsonArray(json, Model.class);
			response = createCustomModels(models, json, headers);
			boolean expected = (response == null || response.getStatusLine() == null
					|| response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT) ? false : true;
			if (!expected)
				handleException(models, headers, response);
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
				handleException(objs, headers, response);
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
			if (!expected)
				handleException(obj, headers, response);
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
			if (!expected)
				handleException(obj, headers, response);
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

	@SuppressWarnings("nls")
	@Override
	public <T> List<T> getModels(String filter, String model,Class<T> clazz, List<Header> headers) {
		CloseableHttpResponse response = null;
		try {
			response = getCustomModels(filter, model, headers);
			if (response == null || response.getStatusLine() == null
					|| (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK
							&& response.getStatusLine().getStatusCode() != HttpStatus.SC_NOT_FOUND)) {
				handleException(model, headers, response);
			}
			try {
				String body = EntityUtils.toString(response.getEntity());
				List<T> item = this.jsonMapper.fromJsonArray(body, clazz);
				return item;
			} catch (ParseException | IOException e) {
				throw new RuntimeException("Failed to unmarshall response. ", e);
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
	 * com.ge.predix.solsvc.bootstrap.ams.factories.ModelFactory#getModels(java.
	 * lang.String, java.lang.String, java.util.List)
	 */
	@SuppressWarnings("nls")
	@Override
	public List<Model> getModels(String filter, String complexType, List<Header> headers) {
		CloseableHttpResponse response = null;
		try {
			response = getCustomModels(filter, complexType, headers);
			if (response == null || response.getStatusLine() == null
					|| (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK
							&& response.getStatusLine().getStatusCode() != HttpStatus.SC_NOT_FOUND)) {
				handleException(complexType, headers, response);
			}
			try {
				String body = EntityUtils.toString(response.getEntity());
				if (body.equals("[]"))
					return null;
				
                List<Model> models = new ArrayList<Model>();
                List mappedObject2 = this.jsonMapper.fromJsonArray(body,complexType);
                for ( Object listItem : mappedObject2 ) {
                    if ( listItem instanceof LinkedHashMap ) {
                        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) listItem;
                        LinkedHashMapModel linkedModel = new LinkedHashMapModel();
                        linkedModel.setMap(map);
                        models.add(linkedModel);
                    }
                    else {
                        models.add((Model) listItem);
                    }
                }

//                Object mappedObject = this.jsonMapper.fromJson(body, Object.class);
//				@SuppressWarnings("unchecked") //Jackson converts to a LinkedHashMap when not marshaling directly to an entity
//				List<LinkedHashMap<String, Object>> list = (List<LinkedHashMap<String, Object>>) mappedObject;
//				//now in the map set the complexType attribute of entity, so Jackson can use the corresponding @JsonTypeInfo annotation
//				LinkedHashMapModel linkedModel = null;
//				for (LinkedHashMap<String, Object> map : list) {
//					linkedModel = new LinkedHashMapModel();
//					linkedModel.setMap(map);
//					map.put("complexType", getBaseModelName(complexType));
//				}
//				String listBody = this.jsonMapper.toJson(list);
//				listBody = "{\"complexType\":\"ModelList\", \"model\":" + listBody + "}";
//				ModelList modelList;
//				try {
//					modelList = this.jsonMapper.fromJson(listBody, ModelList.class);
//					models.addAll(modelList.getModel());
//				} catch (RuntimeException e) {
//					log.warn("unable to convert to " + complexType
//							+ ". we will return the linkedHashMap so you can traverse it manually.  json=" + listBody +  " e=" + e.getMessage());
//					// throw new RuntimeException("unable to convert to " +
//					// modelName,e);
//					models.add(linkedModel);
//				}
				return models;
			} catch (ParseException | IOException e) {
				throw new RuntimeException("Failed to unmarshall response. ", e);
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

	/**
	 * @param modelName
	 * @return -
	 */
	@SuppressWarnings("nls")
	private String getBaseModelName(String modelName) {
		if (modelName.contains("."))
			modelName = modelName.substring(modelName.lastIndexOf(".") + 1);
		modelName = WordUtils.capitalize(modelName);
		return modelName;
	}

	/**
	 * @return -
	 */
	private <M extends Model> TypeReference<List<M>> getTypeRef() {
		TypeReference<List<M>> type = new TypeReference<List<M>>() {
			//
		};
		return type;
	}

	@SuppressWarnings("nls")
	@Override
	public CloseableHttpResponse deleteModel(String modelUri, List<Header> headers) {
		CloseableHttpResponse response = null;
		try {
			String assetUri = this.assetConfig.getAssetUri();
			if (!assetUri.endsWith("/") && !modelUri.startsWith("/"))
				assetUri += "/" + modelUri;
			else
				assetUri += modelUri;
			response = delete(assetUri, headers);
			boolean expected = (response == null || response.getStatusLine() == null
					|| response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT) ? false : true;
			if (!expected)
				handleException(assetUri, headers, response);
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

}
