package com.ge.predix.solsvc.bootstrap.ams.factories;

import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import com.ge.predix.solsvc.bootstrap.ams.dto.Group;

/**
 * 
 * 
 * @author 212421693
 */
public interface GroupFactory extends IFixtureFactory {

	
	/**
	 * @param group Group
	 * @param headers -
	 * @return boolean
	 */
	public HttpResponse createGroup(Group group, List<Header> headers);
	
	
	/**
	 * @param group Group
	 * @param headers -
	 * @return boolean
	 */
	public HttpResponse updateGroup(Group group, List<Header> headers);

	
	/**
	 * @param uuid String
	 * @param headers -
	 * @return Group
	 */
	public Group getGroup(String uuid, List<Header> headers);

	// usage filter=<filter>=<value>
	
	/**
	 * @param uuid String
	 * @param filter String
	 * @param value String
	 * @param headers -
	 * @return List<Group>
	 */
	public List<Group> getGroupsByFilter(String uuid, String filter,
			String value, List<Header> headers);

	/**
	 * 
	 * @param headers -
	 * @return List<Group>
	 */
	public List<Group> getAllGroups(List<Header> headers);

	/**

	 * 
	 * @param uuid String 
	 * @param headers -
	 * @return  boolean
	 */
	public HttpResponse deleteGroup(String uuid, List<Header> headers);
    

}