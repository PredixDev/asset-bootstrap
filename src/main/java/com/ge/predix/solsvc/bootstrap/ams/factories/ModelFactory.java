package com.ge.predix.solsvc.bootstrap.ams.factories;

import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;

import com.ge.predix.entity.model.Model;
import com.ge.predix.solsvc.restclient.impl.RestClient;


/**
 * 
 * @author 212438846
 */
public interface ModelFactory  extends IFixtureFactory{

	
	/**
	 * Pass through for the json.  Must have a uri though.
	 * @param json -
	 * @param headers List<Header> headers
	 * @return boolean
	 */
    public <T> CloseableHttpResponse createModelFromJson(String json, List<Header> headers);
	
	
	/**
	 * 
	 * @param objs -
	 * @param <T> -
	 * @param Model model 
	 * @param headers List<Header> headers
	 * @return boolean
	 */
    public <T> CloseableHttpResponse createModel(List<T> objs, List<Header> headers);
	
	/**
	  * Update Model happens with a post call exactly like create
	 * @param obj -
	 * @param model -
	 * @param headers List<Header> headers
	 * @return -
	 */
	public <T> CloseableHttpResponse updateModel(T obj, List<Header> headers) ;
	
    /**
     * @param obj -
     * @param modelName -
     * @param headers -
     * @return -
     */
    public <T> CloseableHttpResponse updateModel(T obj, String modelName, List<Header> headers) ;

	/**
	 * This method does a nice little trick so that you can unmarshal any entity that you've stored in Predix Asset.  
	 * 
	 * The JSON unmarshaler needs to know and have a couple of things before it can convert the json to an Object.  The Object and child Object must have
	 * annotations to help the unmarshaler.  The Object has to be registered with the unmarshaler.  
	 * 
	 * Say, JetEngine extends AviationModel extends Model.   And Wing extends AviationModel extends Model.
	 * 
	 * We have a utility in JsonMapper that registers any child objects with the unmarshaler, so JetEngine and Wing are registered with the unmarshaler automatically if you simply provide the parent class.
	 * In this case the parent class is Model, which is already registered, so you just have to ensure JetEngine and Wing extend Model and that the @XmlSeeAlso annotation includes the child.  
	 * 
	 * The json gets a key/value pair like this added.          "complexType": "JetEngine",
	 * which tells the unmarshaler the name of the Child Object.

	 * After querying for json string, we simply place the result in a List<Model> in the json.  Then if the child Object (JetEngin or Wing) is registered with the unmarshaler it simply unmarshals.
	 * 
	 * 
	 * @param filter -
	 * @param model - 
	 * @param clazz -
	 * @param headers List<Header> headers
	 * @return Model
	 */
	public  <T> List<T> getModels(String filter, String model, Class<T> clazz, List<Header> headers);
	
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
	public CloseableHttpResponse deleteModel(String uri, List<Header> headers);

    /**
     * @param restClient -
     */
    public void setRestClient(RestClient restClient);



}