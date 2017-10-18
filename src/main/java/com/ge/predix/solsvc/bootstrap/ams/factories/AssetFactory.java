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
	 * @deprecated Method is now deprecated. This method might be removed in future release.
	 * @param asset Asset 
	 * @param headers -
	 * @return boolean 
	 */
	 //@author 212672942
	@Deprecated
	public HttpResponse createAsset(Asset asset, List<Header> headers) ;

	/**
	 * @deprecated Method is now deprecated. This method might be removed in future release.
	 * @param uriSegment uri
	 * @param jsonString json
	 * @param headers -
	 * @return boolean
	 */
	 //@author 212672942
	@Deprecated
	public HttpResponse associateGroupToAsset(String uriSegment, String jsonString,
			List<Header> headers);

	
	/**
	 * @deprecated Method is now deprecated. This method might be removed in future release.
     * @param asset -
     * @param headers -
     * @return -
     */
	 //@author 212672942
	@Deprecated
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
     * @deprecated Method is now deprecated. This method might be removed in future release.
     * @param headers -
     * @return -
     */
    //@author 212672942
    @Deprecated
    List<Asset> getAllAssets(List<Header> headers);

	/**
	 *  @deprecated Method is now deprecated. This method might be removed in future release.
	 * @param uuid id 
	 * @param headers - 
	 * @return boolean
	 */
    //@author 212672942
    @Deprecated
	public HttpResponse deleteAsset(String uuid, List<Header> headers);


}
