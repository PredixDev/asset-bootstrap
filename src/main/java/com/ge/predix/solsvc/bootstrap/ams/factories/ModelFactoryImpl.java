package com.ge.predix.solsvc.bootstrap.ams.factories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ge.predix.entity.model.Model;

/**
 * 
 * @author 212438846
 */
@Component(value = "ModelFactory")
@Scope("prototype")
public class ModelFactoryImpl extends FixtureFactory
        implements ModelFactory
{

    @SuppressWarnings("unused")
    private static Logger log = LoggerFactory.getLogger(ModelFactoryImpl.class);

    @Override
    public CloseableHttpResponse createModelFromJson(String json, List<Header> headers)
    {
        CloseableHttpResponse response = null;
        try
        {
            List<Model> models = this.jsonMapper.fromJsonArray(json, Model.class);
            response = createCustomModels(models, json, headers);
            boolean expected = (response == null || response.getStatusLine() == null
                    || response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT) ? false : true;
            if ( !expected ) handleException(this.restMarshal.createCustomModelUrlForPost(models.get(0)), models, headers, response);
        }
        finally
        {
            if ( response != null ) try
            {
                response.close();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return response;
    }

    /* (non-Javadoc)
     * @see com.ge.predix.solsvc.bootstrap.ams.factories.ModelFactory#createFromJson(java.lang.String, java.util.List)
     */
    @Override
    public CloseableHttpResponse createFromJson(String resource, String json, List<Header> headers)
    {
        CloseableHttpResponse response = null;
        try
        {
            String url = this.restMarshal.getAssetUri(resource);
            response = this.restClient.post(url, json, headers, this.assetConfig.getAssetConnectionTimeout(), this.assetConfig.getAssetSocketTimeout());
            boolean expected = (response == null || response.getStatusLine() == null
                    || response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT) ? false : true;
            if ( !expected ) handleException(url, url, headers, response);
        }
        finally
        {
            if ( response != null ) try
            {
                response.close();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return response;
    }

    @Override
    public <T> CloseableHttpResponse createModel(List<T> objs, List<Header> headers)
    {
        CloseableHttpResponse response = null;
        try
        {
            response = createCustomModels(objs, headers);
            boolean expected = (response == null || response.getStatusLine() == null
                    || response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT) ? false : true;
            if ( !expected ) handleException(this.restMarshal.createCustomModelUrlForPost(objs.get(0)),objs, headers, response);
        }
        finally
        {
            if ( response != null ) try
            {
                response.close();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return response;
    }

    @Override
    public <T> CloseableHttpResponse updateModel(T obj, List<Header> headers)
    {
        CloseableHttpResponse response = null;
        try
        {
            response = updateCustomModel(obj, headers);
            boolean expected = (response == null || response.getStatusLine() == null
                    || response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT) ? false : true;
            String url = this.assetConfig.getAssetUri();
            if ( !expected ) handleException(url, obj, headers, response);
        }
        finally
        {
            if ( response != null ) try
            {
                response.close();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return response;
    }

    @Override
    public <T> CloseableHttpResponse updateModel(T obj, String modelName, List<Header> headers)
    {
        CloseableHttpResponse response = null;
        try
        {
            response = updateCustomModel(obj, modelName, headers);
            boolean expected = (response == null || response.getStatusLine() == null
                    || response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT) ? false : true;
            String url = this.assetConfig.getAssetUri();
            if ( !expected ) handleException(url, obj, headers, response);
        }
        finally
        {
            if ( response != null ) try
            {
                response.close();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return response;
    }

    @SuppressWarnings({
            "nls", "rawtypes"
    })
    @Override
    public List<String> getModels(String filter,List<Header> headers)
    {
        CloseableHttpResponse response = null;
        try
        {
            response = getCustomModels(filter, headers);
            if ( response == null || response.getStatusLine() == null
                    || (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK
                            && response.getStatusLine().getStatusCode() != HttpStatus.SC_NOT_FOUND) )
            {
                String uri = this.assetConfig.getAssetUri();
                handleException(uri, null, headers, response);
            }
            try
            {
                @SuppressWarnings("null")
                String body = EntityUtils.toString(response.getEntity());
                List<LinkedHashMap> items = this.jsonMapper.fromJsonArray(body, LinkedHashMap.class);
                ArrayList<String> result = new ArrayList<String>();
                for ( LinkedHashMap lhm : items ) {
                    result.add(this.jsonMapper.toJson(lhm));
                    
                }
                return result;
            }
            catch (ParseException | IOException e)
            {
                throw new RuntimeException("Failed to unmarshall response. ", e);
            }
        }
        finally
        {
            if ( response != null ) try
            {
                response.close();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }
    
    /*
     * (non-Javadoc)
     * @see
     * com.ge.predix.solsvc.bootstrap.ams.factories.ModelFactory#getModels(java.
     * lang.String, java.lang.String, java.util.List)
     */
    @SuppressWarnings({
            "nls", "unchecked"
    })
    @Override
    public <T> List<T> getModels(String filter, Class<T> complexType, List<Header> headers)
    {
        CloseableHttpResponse response = null;
        try
        {
            response = getCustomModels(filter, headers);
            if ( response == null || response.getStatusLine() == null
                    || (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK
                            && response.getStatusLine().getStatusCode() != HttpStatus.SC_NOT_FOUND && response.getStatusLine().getStatusCode() != HttpStatus.SC_PARTIAL_CONTENT) )
            {
                String uri = this.assetConfig.getAssetUri();
                handleException(uri, complexType, headers, response);
            }
            try
            {
                String body = EntityUtils.toString(response.getEntity());
                if ( body.equals("[]") ) return null;
                List<Object> models = new ArrayList<Object>();                
                List<T> mappedObject2 = this.jsonMapper.fromJsonArray(body, complexType);
                for (Object listItem : mappedObject2)
                {
                    if ( listItem instanceof LinkedHashMap )
                    {
                        @SuppressWarnings({
                                "rawtypes"
                        })
                        LinkedHashMap map = (LinkedHashMap) listItem;
                        LinkedHashMapModel linkedModel = new LinkedHashMapModel();
                        linkedModel.setMap(map);
                        models.add(linkedModel);
                    }
                    else
                    {
                        models.add(listItem);
                    }
                }
                return (List<T>) models;
            }
            catch (ParseException | IOException e)
            {
                throw new RuntimeException("Failed to unmarshall response. ", e);
            }
        }
        finally
        {
            if ( response != null ) try
            {
                response.close();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }
    
    /*
     * (non-Javadoc)
     * @see
     * com.ge.predix.solsvc.bootstrap.ams.factories.ModelFactory#getModels(java.
     * lang.String, java.lang.String, java.util.List)
     */
    @SuppressWarnings({
            "nls", "unchecked"
    })
    @Override
    public List<Object> getModels(String filter, String complexType, List<Header> headers)
    {
        CloseableHttpResponse response = null; 
        try
        {
            response = getCustomModels(filter, headers);
            if ( response == null || response.getStatusLine() == null
                    || (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK
                            && response.getStatusLine().getStatusCode() != HttpStatus.SC_NOT_FOUND && response.getStatusLine().getStatusCode() != HttpStatus.SC_PARTIAL_CONTENT) )
            {
                String uri = this.assetConfig.getAssetUri();
                handleException(uri, complexType, headers, response);
            }
            try
            {
                String body = EntityUtils.toString(response.getEntity());
                if ( body.equals("[]") ) return null;
                List<Object> models = new ArrayList<Object>();                
                List<Object> mappedObject2 = this.jsonMapper.fromJsonArray(body, complexType);
                for (Object listItem : mappedObject2)
                {
                    if ( listItem instanceof LinkedHashMap )
                    {
                        @SuppressWarnings({
                                "rawtypes"
                        })
                        LinkedHashMap map = (LinkedHashMap) listItem;
                        LinkedHashMapModel linkedModel = new LinkedHashMapModel();
                        linkedModel.setMap(map);
                        models.add(linkedModel);
                    }
                    else
                    {
                        models.add(listItem);
                    }
                }
                return models;
            }
            catch (ParseException | IOException e)
            {
                throw new RuntimeException("Failed to unmarshall response. ", e);
            }
        }
        finally
        {
            if ( response != null ) try
            {
                response.close();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }

    @SuppressWarnings("nls")
    @Override
    public CloseableHttpResponse deleteModel(String modelUri, List<Header> headers)
    {
        CloseableHttpResponse response = null;
        try
        {
            String assetUri = this.assetConfig.getAssetUri();
            if ( !assetUri.endsWith("/") && !modelUri.startsWith("/") )
                assetUri += "/" + modelUri;
            else
                assetUri += modelUri;
            response = delete(assetUri, headers);
            boolean expected = (response == null || response.getStatusLine() == null
                    || response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT) ? false : true;
            if ( !expected ) handleException(assetUri, assetUri, headers, response);
        }
        finally
        {
            if ( response != null ) try
            {
                response.close();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return response;
    }

    @Override
    public List<Header> setZoneIdInHeaders(List<Header> headers)
    {
        this.restClient.addZoneToHeaders(headers, this.assetConfig.getZoneId());
        return headers;
    }

}
