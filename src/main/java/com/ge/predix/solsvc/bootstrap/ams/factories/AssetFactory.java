package com.ge.predix.solsvc.bootstrap.ams.factories;

import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import com.ge.predix.entity.asset.Asset;



/**
 * 
 * @author 212421693
 */
public interface AssetFactory extends IFixtureFactory{

	/**
	 * 
	 * @param asset Asset 
	 * @param headers -
	 * @return boolean 
	 */
	public HttpResponse createAsset(Asset asset, List<Header> headers) ;

	/**
	 * 
	 * @param uriSegment uri
	 * @param jsonString json
	 * @param headers -
	 * @return boolean
	 */
	public HttpResponse associateGroupToAsset(String uriSegment, String jsonString,
			List<Header> headers);

	
	/**
     * @param asset -
     * @param headers -
     * @return -
     */
    HttpResponse updateAsset(Asset asset, List<Header> headers);

    
    /**
     * @param uuid -
     * @param headers -
     * @return -
     */
    public Asset getAsset(String uuid, List<Header> headers);
	
    /**
     * @param filter -
     * @param headers -
     * @return -
     */
    public List<Asset> getAssetsByFilter(String filter, List<Header> headers);

    /**
     * @param uuid -
     * @param filter -
     * @param value -
     * @param headers -
     * @return -
     */
    List<Asset> getAssetsByFilter(String uuid, String filter, String value, List<Header> headers);


    /**
     * @param headers -
     * @return -
     */
    List<Asset> getAllAssets(List<Header> headers);

	/**
	 *  
	 * @param uuid id 
	 * @param headers - 
	 * @return boolean
	 */
	public HttpResponse deleteAsset(String uuid, List<Header> headers);


}