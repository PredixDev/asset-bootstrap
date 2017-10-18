package com.ge.predix.solsvc.bootstrap.ams.factories;

import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import com.ge.predix.solsvc.bootstrap.ams.dto.Tag;

/**
 * 
 * 
 * @author 212421693
 */
public interface TagFactory extends IFixtureFactory
{
    /**
     * @deprecated Method is now deprecated. This method might be removed in future release.
     * @param tag Tag
     * @param headers -
     * @return boolean
     */
	//@author 212672942
	@Deprecated
    public HttpResponse createTag(Tag tag, List<Header> headers);

    /**
     * @deprecated Method is now deprecated. This method might be removed in future release.
     * @param tag Tag
     * @param headers -
     * @return boolean
     */
	//@author 212672942
	@Deprecated
    public HttpResponse updateTag(Tag tag, List<Header> headers);

    /**
     * 
     * @param uuid -
     * @param headers -
     * @return Tag
     */
    public Tag getTag(String uuid, List<Header> headers);

    /**
     * @deprecated Method is now deprecated. This method might be removed in future release.
     * @param uuid String
     * @param filter String
     * @param value String
     * @param headers -
     * @return List<Tag>
     */
    //@author 212672942
  	@Deprecated
    public List<Tag> getTagsByFilter(String uuid, String filter, String value, List<Header> headers);

    /**
     * @deprecated Method is now deprecated. This method might be removed in future release.
     * @param headers -
     * @return List<Tag>
     */
  	//@author 212672942
  	@Deprecated
    public List<Tag> getAllTags(List<Header> headers);

    /**
     * @deprecated Method is now deprecated. This method might be removed in future release.
     * @param uuid String
     * @param headers -
     * @return boolean
     */
  	//@author 212672942
  	@Deprecated
    public HttpResponse deleteTag(String uuid, List<Header> headers);

}
