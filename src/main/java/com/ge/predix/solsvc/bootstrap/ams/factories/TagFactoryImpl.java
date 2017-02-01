package com.ge.predix.solsvc.bootstrap.ams.factories;


import java.io.IOException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ge.predix.solsvc.bootstrap.ams.dto.Tag;
/**
 * 
 * 
 * @author 212421693
 */
@Component(value="tagFactory")
@Scope("prototype")
public class TagFactoryImpl extends FixtureFactory implements TagFactory {
	

	@Override
	public  HttpResponse createTag(Tag tag, List<Header> headers){
		CloseableHttpResponse response = null;
		try { 
			response = create(tag, headers);
			boolean expected = (response==null || response.getStatusLine()==null || response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT )?false:true;
			if ( !expected ) 
				handleException(tag, headers, response);
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
	
	
	//Update Tag happens with a post call exactly like create
	@Override
	public HttpResponse updateTag(Tag tag, List<Header> headers){
		return createTag(tag, headers);
	}
	

	@SuppressWarnings("nls")
    @Override
	public Tag getTag(String uuid, List<Header> headers){
		CloseableHttpResponse response = null;
		try {
		response =get(Tag.class, uuid, headers);
		if(response==null || response.getStatusLine()==null || response.getStatusLine().getStatusCode() != HttpStatus.SC_OK  ) {
			return null;
		}
		try
        {
            return (Tag) getObjectFromJson(Tag.class, response).get(0);
        }
        catch (ParseException | IOException e)
        {
            throw new RuntimeException("uuid="+uuid + " " + e.getMessage(), e);

        }
		}finally {
    		if (response!=null )
				try {
					response.close();
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage(), e);
				}	
    	}
	}
	
	//usage filter=<filter>=<value>
	@SuppressWarnings("nls")
    @Override
	public List<Tag> getTagsByFilter(String uuid, String filter, String value, List<Header> headers){
		CloseableHttpResponse response = null; 
		List<Tag> list = null;
		try { 
		response =get(Tag.class, uuid, filter, value, headers);
		if(response==null || response.getStatusLine()==null){
			return list;
		}
		if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK || 
				response.getStatusLine().getStatusCode() == HttpStatus.SC_PARTIAL_CONTENT) {
			try
            {
                list = getObjectFromJson(Tag.class, response);
            }
            catch (ParseException | IOException e)
            {
                throw new RuntimeException("uuid="+uuid + " filter=" + filter + " " + e.getMessage(), e);
            }
		}
		}finally {
    		if (response!=null )
				try {
					response.close();
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage(), e);
				}	
    	}
		return list;
	}
	
	@Override
	public  List<Tag> getAllTags(List<Header> headers){
		CloseableHttpResponse response = null;  
		try { 
		 response =get(Tag.class, headers);
		if(response==null || response.getStatusLine()==null || response.getStatusLine().getStatusCode() != HttpStatus.SC_OK ) {
			return null;
		}
		try
        {
            return getObjectFromJson(Tag.class, response);
        }
        catch (ParseException | IOException e)
        {
            throw new RuntimeException(e);

        }
		}finally {
    		if (response!=null )
				try {
					response.close();
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage(), e);
				}	
    	}
	}
			
	@Override
	public HttpResponse deleteTag(String uuid, List<Header> headers){
		CloseableHttpResponse response = null;   
		try { 
			response =delete(Tag.class, uuid, headers);
			boolean expected = (response==null || response.getStatusLine()==null || response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT)?false:true;
			if ( !expected ) 
				handleException(uuid, headers, response);
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
	

}
