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
     * @param group Classification
     * @param headers -
     * @return boolean
     */
    public HttpResponse createClassification(Classification group, List<Header> headers);

    // Update Classification happens with a post call exactly like create
    /**
     * @param group Classification
     * @param headers -
     * @return -
     */
    public HttpResponse updateClassification(Classification group, List<Header> headers);


    /**
     * @param uuid String
     * @param headers -
     * @return Classification
     */
    public Classification getClassification(String uuid, List<Header> headers);

    /**
     * @param uuid -
     * @param filter -
     * @param value -
     * @param headers -
     * @return -
     */
    List<Classification> getClassificationsByFilter(String uuid, String filter, String value, List<Header> headers);

    /**
     * @param filter -
     * @param headers -
     * @return -
     */
    public List<Classification> getClassificationsByFilter(String filter, List<Header> headers);

    /**
     * @param headers -
     * @return -
     */
    public List<Classification> getAllClassifications(List<Header> headers);


    /**
     * @param uuid -
     * @param headers -
     * @return -
     */
    HttpResponse deleteClassification(String uuid, List<Header> headers);


}
