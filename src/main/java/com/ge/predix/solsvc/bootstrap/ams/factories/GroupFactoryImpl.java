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

import com.ge.predix.solsvc.bootstrap.ams.dto.Group;
/**
 * 
 * 
 * @author 212421693
 */
@Component(value="groupFactory")
@Scope("prototype")
public class GroupFactoryImpl extends FixtureFactory implements GroupFactory {
	

	@Override
	public  CloseableHttpResponse createGroup(Group group, List<Header> headers){
		CloseableHttpResponse response = null;
		try {
			 response = create(group, headers);
			boolean expected = (response==null || response.getStatusLine()==null || response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT)?false:true;
			if ( !expected ) 
				handleException(group, headers, response);
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
	

	@Override
	public CloseableHttpResponse updateGroup(Group group, List<Header> headers){
		return createGroup(group, headers);
	}
	

	@SuppressWarnings("nls")
    @Override
	public Group getGroup(String uuid, List<Header> headers){
		CloseableHttpResponse response = null ;
		
		try { 
			 response =get(Group.class, uuid, headers);
			if(response==null || response.getStatusLine()==null || response.getStatusLine().getStatusCode() != HttpStatus.SC_OK ) {
				throw new RuntimeException("invalid response=" + response);
			}
			try
	        {
	            return (Group) getObjectFromJson(Group.class, response).get(0);
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
	

	@SuppressWarnings("nls")
    @Override
	public List<Group> getGroupsByFilter(String uuid, String filter, String value, List<Header> headers){
		CloseableHttpResponse response = null ;
		List<Group> list = null;
		
		try { 
			response =get(Group.class, uuid, filter, value, headers);
			if(response==null || response.getStatusLine()==null){
				return list;
			}
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK || 
					response.getStatusLine().getStatusCode() == HttpStatus.SC_PARTIAL_CONTENT) {
				try
	            {
	                list = getObjectFromJson(Group.class, response);
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
	

	@SuppressWarnings("nls")
    @Override
	public  List<Group> getAllGroups(List<Header> headers){
		CloseableHttpResponse response = null ;
		try {
			 response =get(Group.class, headers);
			if(response==null || response.getStatusLine()==null || response.getStatusLine().getStatusCode() != HttpStatus.SC_OK ) {
				throw new RuntimeException("invalid response=" + response);
			}
			try
	        {
	            return getObjectFromJson(Group.class, response);
	        }
	        catch (ParseException | IOException e)
	        {
	            throw new RuntimeException(e.getMessage(), e);
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
	public HttpResponse deleteGroup(String uuid, List<Header> headers){
		CloseableHttpResponse response = null ;
		try {
			response =delete(Group.class, uuid, headers);
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
