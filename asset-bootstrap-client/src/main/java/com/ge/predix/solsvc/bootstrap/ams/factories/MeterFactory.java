package com.ge.predix.solsvc.bootstrap.ams.factories;

import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import com.ge.predix.solsvc.bootstrap.ams.dto.Meter;

/**
 * 
 * 
 * @author 212421693
 */
public interface MeterFactory
{
    /**
     * 
     * @param meter Meter
     * @param headers -
     * @return boolean
     */
    public HttpResponse createMeter(Meter meter, List<Header> headers);

    /**
     * 
     * @param meter Meter
     * @param headers -
     * @return boolean
     */
    public HttpResponse updateMeter(Meter meter, List<Header> headers);

    /**
     * 
     * @param uuid -
     * @param headers -
     * @return Meter
     */
    public Meter getMeter(String uuid, List<Header> headers);

    /**
     * 
     * @param uuid String
     * @param filter String
     * @param value String
     * @param headers -
     * @return List<Meter>
     */
    public List<Meter> getMetersByFilter(String uuid, String filter, String value, List<Header> headers);

    /**
     * 
     * @param headers -
     * @return List<Meter>
     */
    public List<Meter> getAllMeters(List<Header> headers);

    /**
     * 
     * @param uuid String
     * @param headers -
     * @return boolean
     */
    public HttpResponse deleteMeter(String uuid, List<Header> headers);

}
