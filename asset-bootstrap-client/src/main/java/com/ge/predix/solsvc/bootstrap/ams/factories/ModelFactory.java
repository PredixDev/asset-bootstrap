package com.ge.predix.solsvc.bootstrap.ams.factories;

import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ge.dsp.pm.ext.entity.model.Model;
import com.ge.predix.solsvc.restclient.impl.RestClient;


/**
 * 
 * @author 212438846
 */
public interface ModelFactory {

	
	/**
	 * 
	 * @param objs -
	 * @param <T> -
	 * @param Model model 
	 * @param headers List<Header> headers
	 * @return boolean
	 */
    public <T> HttpResponse createModel(List<T> objs, List<Header> headers);
	
	/**
	  * Update Model happens with a post call exactly like create
	 * @param obj -
	 * @param model -
	 * @param headers List<Header> headers
	 * @return -
	 */
	public <T> HttpResponse updateModel(T obj, List<Header> headers) ;
	
    /**
     * @param obj -
     * @param modelName -
     * @param headers -
     * @return -
     */
    public <T> HttpResponse updateModel(T obj, String modelName, List<Header> headers) ;

	/**
	 * @param filter -
	 * @param model - 
	 * @param uri -
	 * @param valueTypeRef - 
	 * @param uuid id 
	 * @param headers List<Header> headers
	 * @return Model
	 */
	public  <T> List<T> getModels(String filter, String model, TypeReference valueTypeRef, List<Header> headers);
	
    /**
     * @param query -
     * @param modelName -
     * @param model -
     * @param clazz -
     * @param headers -
     * @return -
     */
    public List<Model> getModels(String query, String modelName, List<Header> headers);

    /**
	 *  
	 * @param uri -
	 * @param headers -
	 * @param uuid id 
	 * @return boolean
	 */
	public HttpResponse deleteModel(String uri, List<Header> headers);

    /**
     * @param restClient -
     */
    public void setRestClient(RestClient restClient);



}