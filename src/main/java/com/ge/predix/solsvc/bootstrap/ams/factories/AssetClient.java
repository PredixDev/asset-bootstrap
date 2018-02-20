package com.ge.predix.solsvc.bootstrap.ams.factories;

import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;

import com.ge.predix.solsvc.bootstrap.ams.common.IAssetConfig;
import com.ge.predix.solsvc.restclient.config.IOauthRestConfig;
import com.ge.predix.solsvc.restclient.impl.RestClient;

/**
 * 
 * @author 212438846
 */
public interface AssetClient {

	/**
	 * Pass through for the json. Must have a uri though.
	 * 
     * @param json -
     * @param headers -
     * @return boolean
     */
	public CloseableHttpResponse createModelFromJson(String json, List<Header> headers);

	/**
	 * Create Asset json
	 * 
	 * @param resource
	 *            -
	 * @param json
	 *            -
	 * @param headers
	 *            -
	 * @return -
	 */
	public CloseableHttpResponse createFromJson(String resource, String json, List<Header> headers);

	/**
	 * 
	 * @param objs
	 *            -
	 * @param <T>
	 *            -
	 * @param headers -
	 * @return boolean
	 */
	public <T> CloseableHttpResponse createModel(List<T> objs, List<Header> headers);

	/**
	 * Update Model happens with a post call exactly like create
	 * 
	 * @param obj  -
	 * @param headers -       
	 * @return -
	 */
	public <T> CloseableHttpResponse updateModel(T obj, List<Header> headers);

	/**
	 * @param obj
	 *            -
	 * @param modelName
	 *            -
	 * @param headers
	 *            -
	 * @param <T>
	 *            -
	 * @return -
	 */
	public <T> CloseableHttpResponse updateModel(T obj, String modelName, List<Header> headers);

	/**
	 * This method does a nice little trick so that you can unmarshal any entity
	 * that you've stored in Predix Asset.
	 * 
	 * The JSON unmarshaler needs to know and have a couple of things before it
	 * can convert the json to an Object. The Object and child Object must have
	 * annotations to help the unmarshaler. The Object has to be registered with
	 * the unmarshaler.
	 * 
	 * Say, JetEngine extends AviationModel extends Model. And Wing extends
	 * AviationModel extends Model.
	 * 
	 * We have a utility in JsonMapper that registers any child objects with the
	 * unmarshaler, so JetEngine and Wing are registered with the unmarshaler
	 * automatically if you simply provide the parent class. In this case the
	 * parent class is Model, which is already registered, so you just have to
	 * ensure JetEngine and Wing extend Model and that the @XmlSeeAlso
	 * annotation includes the child.
	 * 
	 * The json gets a key/value pair like this added. "complexType":
	 * "JetEngine", which tells the unmarshaler the name of the Child Object.
	 * 
	 * After querying for json string, we simply place the result in a
	 * List&lt;Model&gt; in the json. Then if the child Object (JetEngin or
	 * Wing) is registered with the unmarshaler it simply unmarshals.
	 * 
	 * @param <T>
	 *            -
	 * @param filter
	 *            -
	 * @param complexType
	 *            -
	 * @param headers
	 *            List&lt;Header&gt; headers
	 * @return Model
	 */
	public <T> List<T> getModels(String filter, Class<T> complexType, List<Header> headers);

	/**
	 * This method does a nice little trick so that you can unmarshal any entity
	 * that you've stored in Predix Asset.
	 * 
	 * The JSON unmarshaler needs to know and have a couple of things before it
	 * can convert the json to an Object. The Object and child Object must have
	 * annotations to help the unmarshaler. The Object has to be registered with
	 * the unmarshaler.
	 * 
	 * Say, JetEngine extends AviationModel extends Model. And Wing extends
	 * AviationModel extends Model.
	 * 
	 * We have a utility in JsonMapper that registers any child objects with the
	 * unmarshaler, so JetEngine and Wing are registered with the unmarshaler
	 * automatically if you simply provide the parent class. In this case the
	 * parent class is Model, which is already registered, so you just have to
	 * ensure JetEngine and Wing extend Model and that the @XmlSeeAlso
	 * annotation includes the child.
	 * 
	 * The json gets a key/value pair like this added. "complexType":
	 * "JetEngine", which tells the unmarshaler the name of the Child Object.
	 * 
	 * After querying for json string, we simply place the result in a List
	 * &lt;Model&gt; in the json. Then if the child Object (JetEngin or Wing) is
	 * registered with the unmarshaler it simply unmarshals.
	 * 
	 * @param filter
	 *            -
	 * @param complexType
	 *            -
	 * @param headers
	 *            -
	 * @return Model
	 */
	public List<Object> getModels(String filter, String complexType, List<Header> headers);

	/**
	 * @param filter
	 *            -
	 * @param headers
	 *            -
	 * @return -
	 */
	public List<String> getModels(String filter, List<Header> headers);

	/**
	 * @param complexType
	 *            -
	 * @param pathParam
	 *            -
	 * @param filter
	 *            -
	 * @param value
	 *            -
	 * @param headers
	 *            -
	 * @param <T>
	 *            -
	 * 
	 * @return T -
	 */
	public <T> List<T> getModelsByFilter(Class<T> complexType, String pathParam, String filter, String value,
			List<Header> headers);

	/**
	 * 
	 * @param uri
	 *            -
	 * @param headers
	 *            -
	 * @return boolean
	 */
	public CloseableHttpResponse deleteModel(String uri, List<Header> headers);

	/**
	 * @param restClient
	 *            -
	 */
	public void setRestClient(RestClient restClient);

	/**
	 * @param headers
	 *            -
	 * @return -
	 */
	public List<Header> setZoneIdInHeaders(List<Header> headers);

	/**
	 * @param aConfig
	 *            -
	 * @param rConfig
	 *            -
	 * 
	 */
	public abstract void overrideAssetRestConfig(IAssetConfig aConfig, IOauthRestConfig rConfig);

}