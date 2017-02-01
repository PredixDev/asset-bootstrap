package com.ge.predix.solsvc.bootstrap.ams.factories;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ge.predix.entity.asset.Asset;
import com.ge.predix.entity.asset.AssetTag;
import com.ge.predix.solsvc.bootstrap.ams.common.IAssetConfig;
import com.ge.predix.solsvc.bootstrap.ams.dto.AssetEvent;
import com.ge.predix.solsvc.bootstrap.ams.dto.AssetNoModel;
import com.ge.predix.solsvc.bootstrap.ams.dto.Attribute;
import com.ge.predix.solsvc.bootstrap.ams.dto.AuditPatch;
import com.ge.predix.solsvc.bootstrap.ams.dto.AuditRecord;
import com.ge.predix.solsvc.bootstrap.ams.dto.Cardinality;
import com.ge.predix.solsvc.bootstrap.ams.dto.Classification;
import com.ge.predix.solsvc.bootstrap.ams.dto.Event;
import com.ge.predix.solsvc.bootstrap.ams.dto.EventType;
import com.ge.predix.solsvc.bootstrap.ams.dto.Group;
import com.ge.predix.solsvc.bootstrap.ams.dto.Message;
import com.ge.predix.solsvc.bootstrap.ams.dto.Name;
import com.ge.predix.solsvc.bootstrap.ams.dto.Part;
import com.ge.predix.solsvc.bootstrap.ams.dto.PartForUIList;
import com.ge.predix.solsvc.bootstrap.ams.dto.State;
import com.ge.predix.solsvc.bootstrap.ams.dto.Structure;
import com.ge.predix.solsvc.bootstrap.ams.dto.Tag;
import com.ge.predix.solsvc.bootstrap.ams.dto.Template;

/**
 * 
 * @author predix
 */
@SuppressWarnings("unchecked")
@Component
class RestMarshal {

	// public final HttpHeaders requestHeaders = new HttpHeaders();
	private Map<Class<?>, String> pathMap;
	private Map<Class<?>, TypeReference<?>> typeReferenceMap;
	private Paths paths;
	
	private IAssetConfig assetConfig;
	
	/**
	 * 
	 */
	public void setUpRestMarshal(IAssetConfig config) {
		this.assetConfig = config;
		initTypeReferenceMap();
        String baseUrl = this.assetConfig.getAssetUri();
		this.paths = new Paths(baseUrl);
		initPathMap();
	}


	/**
	 * 
	 */
	protected void initPathMap() {
		this.pathMap = new HashMap<>();
        this.pathMap.put(Asset.class, this.paths.asset);
        this.pathMap.put(AssetNoModel.class, this.paths.asset);
		this.pathMap.put(AuditRecord.class, this.paths.audit);
		this.pathMap.put(AuditPatch.class, this.paths.auditPatches);
		this.pathMap.put(Part.class, this.paths.part);
		this.pathMap.put(Classification.class, this.paths.classification);
		this.pathMap.put(Template.class, this.paths.template);
		this.pathMap.put(Group.class, this.paths.group);
		this.pathMap.put(Tag.class, this.paths.tag);
	}

	private void initTypeReferenceMap() {
		this.typeReferenceMap = new HashMap<>();
        this.typeReferenceMap.put(Asset.class, new TypeReference<List<Asset>>() {
            //
        });
        this.typeReferenceMap.put(AssetNoModel.class, new TypeReference<List<AssetNoModel>>() {
            //
        });
		this.typeReferenceMap.put(AssetEvent.class,
				new TypeReference<List<AssetEvent>>() {
		    //
				});
		this.typeReferenceMap.put(AssetTag.class,
				new TypeReference<List<AssetTag>>() {
		    //
				});
		this.typeReferenceMap.put(Attribute.class,
				new TypeReference<List<Attribute>>() {
		    //
				});
		this.typeReferenceMap.put(AuditPatch.class,
				new TypeReference<List<AuditPatch>>() {
		    //
				});
		this.typeReferenceMap.put(AuditRecord.class,
				new TypeReference<List<AuditRecord>>() {
		    //
				});
		this.typeReferenceMap.put(Cardinality.class,
				new TypeReference<List<Cardinality>>() {
		    //
				});
		this.typeReferenceMap.put(Classification.class,
				new TypeReference<List<Classification>>() {
		    //
				});
		this.typeReferenceMap.put(Event.class, new TypeReference<List<Event>>() {
		    //
		});
		this.typeReferenceMap.put(EventType.class,
				new TypeReference<List<EventType>>() {
		    //
				});
		this.typeReferenceMap.put(Group.class, new TypeReference<List<Group>>() {
		    //
		});
		this.typeReferenceMap.put(Message.class, new TypeReference<List<Message>>() {
		    //
		});
		this.typeReferenceMap.put(Tag.class, new TypeReference<List<Tag>>() {
		    //
		});
		this.typeReferenceMap.put(Name.class, new TypeReference<List<Name>>() {
		    //
		});
		this.typeReferenceMap.put(Part.class, new TypeReference<List<Part>>() {
		    //
		});
		this.typeReferenceMap.put(PartForUIList.class,
				new TypeReference<List<PartForUIList>>() {
		    //
				});
		this.typeReferenceMap.put(State.class, new TypeReference<List<State>>() {
		    //
		});
		this.typeReferenceMap.put(Structure.class,
				new TypeReference<List<Structure>>() {
		    //
				});
		this.typeReferenceMap.put(Template.class,
				new TypeReference<List<Template>>() {
		    //
				});
	

		this.typeReferenceMap.put(String.class, new TypeReference<List<String>>() {
		    //
		});
	}
	
	/**
	 * @param obj -
	 * @return -
	 */
	@SuppressWarnings("nls")
    public String getUri(Object obj){
		String uri = null;
		try {
			if (obj instanceof Map) {
				Object uriValue = ((Map)obj).get("uri");
				if (uriValue != null) {
					uri = uriValue.toString();
				}
			} else {
				Method m = obj.getClass().getMethod("getUri", null);
				uri = ((m != null) ? (String) m.invoke(obj, null) : null);
			}
		} catch (NoSuchMethodException | SecurityException| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new RuntimeException("Generating URI from Object failed. ",e);
		}
		return uri;
	}
	
	/**
	 * @param obj -
	 * @return -
	 */
	@SuppressWarnings("nls")
    public String createCustomModelUrlForPost(Object obj){
		String baseUrl = this.assetConfig.getAssetUri();
		String uri = getUri(obj);	
		if ( uri == null )
		    throw new UnsupportedOperationException("invalid uri=null");
		return baseUrl+uri.substring(0, uri.indexOf("/", 2));
	}	

    /**
     * @param obj -
     * @return -
     */
    @SuppressWarnings("nls")
    public String createCustomModelUrlForPut(Object obj){
        String baseUrl = this.assetConfig.getAssetUri();
        String uri = getUri(obj);   
        if ( uri == null )
            throw new UnsupportedOperationException("invalid uri=null");
        if ( !uri.startsWith("/"))
            uri = "/" + uri;
        return baseUrl+uri;
    }   

    public String createCustomModelUrlForPut(Object obj, String modelName){
        String baseUrl = this.assetConfig.getAssetUri();
        String uri = getUri(obj);   
        if ( uri == null )
            uri = modelName;
        if ( !uri.startsWith("/"))
            uri = "/" + uri;
        return baseUrl+uri;
    }   

    /**
	 * @param clazz -
	 * @return -
	 */
	public String getPath(Class<?> clazz) {
		return this.pathMap.get(clazz);
	}

	/**
	 * @param clazz -
	 * @return -
	 */
	public <T> TypeReference<List<T>> getTypeReference(Class<?> clazz) {
		return (TypeReference<List<T>>) this.typeReferenceMap.get(clazz);
	}

	/**
	 * @param uri -
	 * @return -
	 */
	@SuppressWarnings("nls")
    public Class<?> getClassFromUri(String uri) {
		String path = this.paths.prefix + uri.substring(0, uri.indexOf("/", 2));
		for (Map.Entry<Class<?>, String> entry : this.pathMap.entrySet()) {
			if (entry.getValue().equals(path)) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * 
	 * @author predix
	 */
	public class Paths {
		/**
		 * 
		 */
		public final String prefix;

		/**
		 * 
		 */
		public final String asset;
		/**
		 * 
		 */
		public final String group;
		/**
		 * 
		 */
		public final String tag;
	
		/**
		 * 
		 */
		public final String audit;
		/**
		 * 
		 */
		public final String auditPatches;
		/**
		 * 
		 */
		public final String info;
		/**
		 * 
		 */
		public final String part;
		/**
		 * 
		 */
		public final String classification;
		/**
		 * 
		 */
		public final String template;

		/**
		 * @param prefix -
		 */
		@SuppressWarnings("nls")
        Paths(String prefix) {
			this.prefix = prefix;
			this.asset = prefix + "/asset";
			this.group = prefix + "/group";
			this.tag = prefix + "/tag";
			this.audit = prefix + "/audit";
			this.auditPatches = prefix + "/audit/patches";
			this.info = prefix + "/info";
			this.part = prefix + "/part";
			this.classification = prefix + "/classification";
			this.template = prefix + "/template";
		}

	}
}
