package com.ge.predix.solsvc.bootstrap.ams.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright General Electric 2014
 * All rights reserved. Proprietary and confidential
 * User: Nirmal Gupta
 * Date: 2/25/14
 * Time: 1:26 PM
 */
public class RequestContext {
    private static ThreadLocal<Map<String, Object>> localValueHolder =  new ThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    /**
     * @param key -
     * @return -
     */
    @SuppressWarnings({ "unchecked",  })
	public static <T> T get(String key) {
        return (T) localValueHolder.get().get(key);
    }

    
	/**
	 * @param key -
	 * @param value -
	 */
	public static void put(String key, Object value) {
        localValueHolder.get().put(key, value);
    }

    
	/**
	 * @param key -
	 * @return -
	 */
	public static Object remove(String key) {
        return localValueHolder.get().remove(key);
    }

    
	/**
	 *  -
	 */
	public static void clear() {
        localValueHolder.get().clear();
    }
}

