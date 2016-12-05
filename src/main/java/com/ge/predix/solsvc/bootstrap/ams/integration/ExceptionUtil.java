package com.ge.predix.solsvc.bootstrap.ams.integration;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ge.predix.solsvc.bootstrap.ams.dto.Message;

/**
 * 
 * 
 * @author 212421693
 */
public abstract class ExceptionUtil {
	
	private static Logger log = LoggerFactory.getLogger(ExceptionUtil.class);
	/**
	 * @param message Message
	 * @return  Map<String, String>
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> extractedErrors(Message message) {
		return (message == null) ? null : (Map<String, String>) message.getErrors();
	}

	/**
	 * 
	 * @param errorResponseBody String
	 * @return Message
	 */
	public static Message getErrorMessageFromJsonString(String errorResponseBody) {
		if (errorResponseBody == null) {
			return null;
		}
		TypeReference<Message> listOfRefType = new TypeReference<Message>() {
			//
		};
		try {
			return new ObjectMapper().readValue(errorResponseBody, listOfRefType);
		} catch (JsonParseException ignore) {
			log.error("ExceptionUtil : JsonParseException" +ignore.getMessage() ); //$NON-NLS-1$
		} catch (IOException ignore) {
			log.error("ExceptionUtil : IOException" +ignore.getMessage() ); //$NON-NLS-1$
		}
		return null;
	}

}