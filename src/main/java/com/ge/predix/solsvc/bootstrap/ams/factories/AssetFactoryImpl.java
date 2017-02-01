package com.ge.predix.solsvc.bootstrap.ams.factories;

import java.io.IOException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ge.predix.entity.asset.Asset;



/**
 * 
 * @author 212421693
 */
@Component(value = "assetFactory")
@Scope("prototype")
public class AssetFactoryImpl extends FixtureFactory
        implements AssetFactory
{
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(AssetFactoryImpl.class);


    @Override
    public HttpResponse createAsset(Asset asset, List<Header> headers)
    {
        HttpResponse response = create(asset, headers);
        boolean expected = (response == null || response.getStatusLine() == null || response.getStatusLine()
                .getStatusCode() != HttpStatus.SC_NO_CONTENT) ? false : true;
        if ( !expected ) handleException(asset, headers, response);
        return response;

    }

    // Update Asset happens with a post call exactly like create
    @Override
    public HttpResponse updateAsset(Asset asset, List<Header> headers)
    {
        return createAsset(asset, headers);
    }

    @Override
    public HttpResponse associateGroupToAsset(String uriSegment, String jsonString, List<Header> headers)
    {
    	CloseableHttpResponse response = null ; 
    	try { 
	         response = this.create(Asset.class, uriSegment, jsonString, headers);
	        boolean expected = (response == null || response.getStatusLine() == null
	                || response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT || response.getStatusLine()
	                .getStatusCode() != HttpStatus.SC_OK) ? false : true;
	        if ( !expected ) handleException(jsonString, headers, response);
    	}finally {
    		if (response!=null )
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
    public Asset getAsset(String uuid, List<Header> headers)
    {
    	CloseableHttpResponse response = null ; 
    	try {
    	 response = get(Asset.class, uuid, headers);
        if ( response == null || response.getStatusLine() == null
              ||  (  response.getStatusLine() != null && !( response.getStatusLine().getStatusCode() == HttpStatus.SC_OK || response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_MODIFIED)))
        {
            throw new RuntimeException("invalid response" + response + " when calling uuid=" + uuid + " for headers=" + headers);
        }
            return getObjectFromJson(Asset.class, response).get(0);
    	}
        catch (ParseException | IOException e)
        {
            try
            {
            	if( response ==null ) 
            		throw new RuntimeException("uuid="+uuid + "  json= with error"  + e.getMessage(), e);
            	else{
            		throw new RuntimeException("uuid="+uuid + " json=" +  EntityUtils.toString(response.getEntity()) + e.getMessage(), e);
            	}
            }
            catch (ParseException | IOException e1)
            {
                throw new RuntimeException("uuid="+uuid  + e.getMessage(), e);
            }

        } finally {
    		if (response!=null ) {
				try {
					response.close();
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage(), e);
				}	
    		}
    	}
    }

    @SuppressWarnings("nls")
    @Override
    public List<Asset> getAssetsByFilter(String uuid, String filter, String value, List<Header> headers)
    {
    	List<Asset> list = null;
    	CloseableHttpResponse response = null ;
    	try
        {
	        response = get(Asset.class, uuid, filter, value, headers);
	        
	        if ( response == null || response.getStatusLine() == null )
		        {
		            return list;
		        }
	        if ( response.getStatusLine().getStatusCode() == HttpStatus.SC_OK
	                || response.getStatusLine().getStatusCode() == HttpStatus.SC_PARTIAL_CONTENT )
		        {
		                list = getObjectFromJson(Asset.class, response);
		        }
        }catch (ParseException | IOException e)
        {
            try
            {
            	if( response ==null ) 
            		throw new RuntimeException("uuid="+uuid + " filter=" + filter + "  json= with error"  + e.getMessage(), e);
            	else {
            		throw new RuntimeException("uuid="+uuid + " filter=" + filter + "  json=" +  EntityUtils.toString(response.getEntity()) + e.getMessage(), e);
            	}
            }
            catch (ParseException | IOException e1)
            {
                throw new RuntimeException("uuid="+uuid + " filter=" + filter  + e.getMessage(), e);

            }
        }
    	finally {
    		if (response!=null )
				try {
					response.close();
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage(), e);
				}	
    	}
        
        return list;
    }

    @SuppressWarnings("nls")
    @Override
    public List<Asset> getAssetsByFilter(String filter, List<Header> headers)
    {
    	CloseableHttpResponse response =null;
    	try { 
	    	response = get(Asset.class, filter, headers);
	        
	        if ( response.getStatusLine().getStatusCode() == HttpStatus.SC_OK
	                || response.getStatusLine().getStatusCode() == HttpStatus.SC_PARTIAL_CONTENT )
	        {
	                return getObjectFromJson(Asset.class, response);
	           
	        }
    	} catch (ParseException | IOException e)
        {
            try
            {
            	if( response == null ) 
            		throw new RuntimeException(filter=" + filter + "  + e.getMessage(), e);
            	else { 
            		throw new RuntimeException(" filter=" + filter + " json=" +  EntityUtils.toString(response.getEntity()) + e.getMessage(), e);
            	}
            }
            catch (ParseException | IOException e1)
            {
                throw new RuntimeException(" filter=" + filter + " " + e.getMessage(), e);

            }

        }
    	finally {
    		if (response!=null )
				try {
					response.close();
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage(), e);
				}	
    	}
        // TODO ===> add IOUtils.toString(response.getEntity().getContent(), "UTF-8")
        throw new RuntimeException("filter= " + filter + " Invalid HTTP response = " + response.getStatusLine().getStatusCode());

    }

    @SuppressWarnings("nls")
    @Override
    public List<Asset> getAllAssets(List<Header> headers)
    {
    	CloseableHttpResponse response = null;
    	try { 
    	 response = get(Asset.class, headers);
        if ( response == null || response.getStatusLine() == null
                || response.getStatusLine().getStatusCode() != HttpStatus.SC_OK )
        {
            throw new RuntimeException("invalid response" + response);
        }
            return getObjectFromJson(Asset.class, response);
       } catch (ParseException | IOException e)
        {
            try
            {
            	if( response == null ) 
            		throw new RuntimeException(e.getMessage(), e);
            	else 
            		throw new RuntimeException("json=" +  EntityUtils.toString(response.getEntity()) +e.getMessage(), e);
            }
            catch (ParseException | IOException e1)
            {
                throw new RuntimeException(e.getMessage(), e);
            }

        }
    	finally {
    		if (response!=null )
				try {
					response.close();
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage(), e);
				}	
    	}
    }

    @Override
    public HttpResponse deleteAsset(String uuid, List<Header> headers)
    {
    	CloseableHttpResponse  response = null ;
    	try { 
         response = delete(Asset.class, uuid, headers);
        boolean expected = (response == null || response.getStatusLine() == null || response.getStatusLine()
                .getStatusCode() != HttpStatus.SC_NO_CONTENT) ? false : true;
        if ( !expected ) handleException(uuid, headers, response);

    	} finally {
    		if (response!=null )
				try {
					response.close();
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage(), e);
				}	
    	}
        return response;
    }


}
