package com.ge.predix.solsvc.bootstrap.ams.factories;

import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import com.ge.predix.solsvc.bootstrap.ams.dto.Classification;

/**
 * ClassificationFactory
 * 
 * @author 212421693
 */

public interface ClassificationFactory extends IFixtureFactory
{

    /**
     * @deprecated Method is now deprecated. createClassification method might be renamed and/or removed in future release.
     * @param group Classification
     * @param headers -
     * @return boolean
     */
	 //@author 212672942
	@Deprecated
    public HttpResponse createClassification(Classification group, List<Header> headers);

    // Update Classification happens with a post call exactly like create
    /**
     * @deprecated Method is now deprecated. updateClassification method might be renamed and/or removed in future release.
     * @param group Classification
     * @param headers -
     * @return -
     */
	//@author 212672942
	@Deprecated
    public HttpResponse updateClassification(Classification group, List<Header> headers);


    /**
     * @deprecated Method is now deprecated. getClassification method might be renamed and/or removed in future release.
     * @param uuid String
     * @param headers -
     * @return Classification
     */
	//@author 212672942
	@Deprecated
    public Classification getClassification(String uuid, List<Header> headers);

    /**
     * @deprecated Method is now deprecated. getClassificationsByFilter method might be renamed and/or removed in future release.
     * @param uuid -
     * @param filter -
     * @param value -
     * @param headers -
     * @return -
     */
	//@author 212672942. Method marked deprecated as this is not used by any application
	@Deprecated
    List<Classification> getClassificationsByFilter(String uuid, String filter, String value, List<Header> headers);

    /**
     * @deprecated Method is now deprecated. getClassificationsByFilter method might be renamed and/or removed in future release.
     * @param filter -
     * @param headers -
     * @return -
     */
	//@author 212672942
	@Deprecated
    public List<Classification> getClassificationsByFilter(String filter, List<Header> headers);

    /**
     * @deprecated Method is now deprecated. getAllClassifications method might be renamed and/or removed in future release.
     * @param headers -
     * @return -
     */
	//@author 212672942
	@Deprecated
    public List<Classification> getAllClassifications(List<Header> headers);


    /**
     * @deprecated Method is now deprecated. deleteClassification method might be renamed and/or removed in future release.
     * @param uuid -
     * @param headers -
     * @return -
     */
	//@author 212672942
	@Deprecated
    HttpResponse deleteClassification(String uuid, List<Header> headers);


}
