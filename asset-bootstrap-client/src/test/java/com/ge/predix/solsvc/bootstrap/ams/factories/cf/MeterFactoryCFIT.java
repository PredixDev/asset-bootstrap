package com.ge.predix.solsvc.bootstrap.ams.factories.cf;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ge.predix.solsvc.bootstrap.ams.common.BaseFactoryIT;
import com.ge.predix.solsvc.bootstrap.ams.dto.Meter;
import com.ge.predix.solsvc.bootstrap.ams.factories.MeterFactory;


/**
 * 
 * @author predix -
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{
        "classpath*:META-INF/spring/ext-util-scan-context.xml",
        "classpath*:META-INF/spring/asset-bootstrap-client-scan-context.xml",
        "classpath*:META-INF/spring/predix-rest-client-scan-context.xml",
        "classpath*:META-INF/spring/predix-rest-client-sb-properties-context.xml"
})
@Component
public class MeterFactoryCFIT extends BaseFactoryIT
{

    @Autowired
    private MeterFactory meterFactory;

    /**
     *  -
     */
    @Test
    public void testMeterCRUDOperations()
    {
        List<Header> headers = this.restClient.getSecureTokenForClientId();
        this.restClient.addZoneToHeaders(headers, this.assetRestConfig.getZoneId());

        testDeleteMeter(headers);
        testCreateMeterGetToken(headers);
        testGetMeter(headers);
        testDeleteMeter(headers);
    }

    /**
     *  -
     * @param headers -
     */
    public void testCreateMeterGetToken(List<Header> headers)
    {
        Meter meter = new Meter();
        meter.setName("pressureForTC"); //$NON-NLS-1$
        meter.setDescription("pressure description");//$NON-NLS-1$
        meter.setUom("Pascal");//$NON-NLS-1$
        meter.setMeterType("Continuous");//$NON-NLS-1$
        meter.setDataType("String");//$NON-NLS-1$
        meter.setUri("/meter/pressureForTC");//$NON-NLS-1$
        List<String> tags = new ArrayList<String>();
        tags.add("asset1.pressure");//$NON-NLS-1$
        meter.setTags(tags);  // no point setting. It comes back as an empty list

        HttpResponse response = this.meterFactory.createMeter(meter, headers);
        Assert.assertEquals(response.getStatusLine().getStatusCode(),HttpStatus.SC_NO_CONTENT);

    }

    /**
     *  -
     * @param headers -
     */
    public void testGetMeter(List<Header> headers)
    {
        Meter meter = this.meterFactory.getMeter("pressureForTC", headers);//$NON-NLS-1$
        assertEquals("pressureForTC", meter.getName());//$NON-NLS-1$

    }

    /**
     *  -
     * @param headers -
     */
    public void testDeleteMeter(List<Header> headers)
    {
        HttpResponse response = this.meterFactory.deleteMeter("pressureForTC", headers);//$NON-NLS-1$
        Assert.assertEquals(response.getStatusLine().getStatusCode(),HttpStatus.SC_NO_CONTENT);

    }

}
