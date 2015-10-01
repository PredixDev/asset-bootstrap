package com.ge.predix.solsvc.bootstrap.ams.factories;


import java.io.IOException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.springframework.stereotype.Component;

import com.ge.predix.solsvc.bootstrap.ams.dto.Group;
/**
 * 
 * 
 * @author 212421693
 */
@Component(value="groupFactory")
public class GroupFactoryImpl extends FixtureFactory implements GroupFactory {
	

	@Override
	public  HttpResponse createGroup(Group group, List<Header> headers){
		HttpResponse response = create(group, headers);
		boolean expected = (response==null || response.getStatusLine()==null || response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT)?false:true;
		if ( !expected ) 
			handleException(group, headers, response);
		
		return response;

	}
	

	@Override
	public HttpResponse updateGroup(Group group, List<Header> headers){
		return createGroup(group, headers);
	}
	

	@SuppressWarnings("nls")
    @Override
	public Group getGroup(String uuid, List<Header> headers){
		HttpResponse response =get(Group.class, uuid, headers);
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
	}
	

	@SuppressWarnings("nls")
    @Override
	public List<Group> getGroupsByFilter(String uuid, String filter, String value, List<Header> headers){
		
		HttpResponse response =get(Group.class, uuid, filter, value, headers);
		List<Group> list = null;
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
		return list;
	}
	

	@SuppressWarnings("nls")
    @Override
	public  List<Group> getAllGroups(List<Header> headers){
		HttpResponse response =get(Group.class, headers);
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
	}
	

	@Override
	public HttpResponse deleteGroup(String uuid, List<Header> headers){
		HttpResponse response =delete(Group.class, uuid, headers);
		boolean expected = (response==null || response.getStatusLine()==null || response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT)?false:true;
		if ( !expected ) 
			handleException(uuid, headers, response);
		
		return response;
	}	


}
