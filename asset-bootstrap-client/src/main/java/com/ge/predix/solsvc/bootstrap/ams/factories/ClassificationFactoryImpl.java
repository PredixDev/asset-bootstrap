package com.ge.predix.solsvc.bootstrap.ams.factories;

import java.io.IOException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ge.predix.solsvc.bootstrap.ams.dto.Asset;
import com.ge.predix.solsvc.bootstrap.ams.dto.Classification;

/**
 * 
 * ClassificationFactoryImpl
 * 
 * @author 212421693
 */
@Component(value = "classificationFactory")
public class ClassificationFactoryImpl extends FixtureFactory
        implements ClassificationFactory
{
    private static final Logger log = LoggerFactory.getLogger(ClassificationFactoryImpl.class);

    @Override
    public HttpResponse createClassification(Classification classification, List<Header> headers)
    {
        HttpResponse response = create(classification, headers);
        boolean expected = (response == null || response.getStatusLine() == null || response.getStatusLine()
                .getStatusCode() != HttpStatus.SC_NO_CONTENT) ? false : true;
        if ( !expected ) handleException(classification, headers, response);

        return response;
    }

    @Override
    public HttpResponse updateClassification(Classification classification, List<Header> headers)
    {
        HttpResponse response = update(classification, headers);
        boolean expected = (response == null || response.getStatusLine() == null || response.getStatusLine()
                .getStatusCode() != HttpStatus.SC_NO_CONTENT) ? false : true;
        if ( !expected ) handleException(classification, headers, response);

        return response;
    }

    @SuppressWarnings("nls")
    @Override
    public Classification getClassification(String uuid, List<Header> headers)
    {
        HttpResponse response = get(Classification.class, uuid, headers);
        boolean expected = (response == null || response.getStatusLine() == null
                || response.getStatusLine().getStatusCode() != HttpStatus.SC_OK ? false : true);
        if ( !expected )
        {
            throw new RuntimeException("invalid response=" + response);
        }
        try
        {
            return (Classification) getObjectFromJson(Classification.class, response).get(0);
        }
        catch (ParseException | IOException e)
        {
            throw new RuntimeException("uuid=" + uuid + " " + e.getMessage(), e);

        }
    }

    @SuppressWarnings("nls")
    @Override
    public List<Classification> getClassificationsByFilter(String uuid, String filter, String value,
            List<Header> headers)
    {
        HttpResponse response = get(Classification.class, uuid, filter, value, headers);
        List<Classification> list = null;
        if ( response == null
                || response.getStatusLine() == null
                || (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK && response.getStatusLine()
                        .getStatusCode() != HttpStatus.SC_PARTIAL_CONTENT) ? true : false )
        {
            ClassificationFactoryImpl.log.error("Unexpected HTTP Response=" //$NON-NLS-1$
                    + response.getStatusLine().getStatusCode() + " body=" //$NON-NLS-1$
                    + this.restClient.getResponse(response));
            throw new RuntimeException("invalid response" + response);

        }
        try
        {
            list = getObjectFromJson(Classification.class, response);
        }
        catch (ParseException | IOException e)
        {
            throw new RuntimeException("uuid=" + uuid + " filter=" + filter + " " + e.getMessage(), e);

        }

        return list;
    }

    @SuppressWarnings("nls")
    @Override
    public List<Classification> getClassificationsByFilter(String filter, List<Header> headers)
    {
        HttpResponse response = get(Classification.class, filter, headers);
        List<Classification> list = null;
        if ( response == null || response.getStatusLine() == null )
        {
            return list;
        }
        if ( response.getStatusLine().getStatusCode() == HttpStatus.SC_OK
                || response.getStatusLine().getStatusCode() == HttpStatus.SC_PARTIAL_CONTENT )
        {
            try
            {
                list = getObjectFromJson(Classification.class, response);
            }
            catch (ParseException | IOException e)
            {
                throw new RuntimeException(" filter=" + filter + " " + e.getMessage(), e);
            }
        }
        return list;
    }

    @SuppressWarnings("nls")
    @Override
    public List<Classification> getAllClassifications(List<Header> headers)
    {
        HttpResponse response = get(Classification.class, headers);
        if ( response == null || response.getStatusLine() == null
                || response.getStatusLine().getStatusCode() != HttpStatus.SC_OK ? true : false )
        {
            ClassificationFactoryImpl.log.error("Unexpected HTTP Response=" //$NON-NLS-1$
                    + response.getStatusLine().getStatusCode() + " body=" //$NON-NLS-1$
                    + this.restClient.getResponse(response));
            throw new RuntimeException("invalid response" + response);
        }
        try
        {
            return getObjectFromJson(Classification.class, response);
        }
        catch (ParseException | IOException e)
        {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public HttpResponse deleteClassification(String uuid, List<Header> headers)
    {
        HttpResponse response = delete(Classification.class, uuid, headers);
        boolean expected = (response == null || response.getStatusLine() == null || response.getStatusLine()
                .getStatusCode() != HttpStatus.SC_NO_CONTENT) ? false : true;
        if ( !expected ) handleException(uuid, headers, response);

        return response;
    }

}
