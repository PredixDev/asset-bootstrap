package com.ge.predix.solsvc.bootstrap.ams.integration;

import org.apache.http.HttpResponse;

public class HttpResponseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8261646362855663203L;

	HttpResponse httpResponse;

	public HttpResponseException(String msg, HttpResponse httpResponse) {
		super(msg);
		this.httpResponse = httpResponse;
	}

	public HttpResponse getHttpResponse() {
		return httpResponse;
	}
	
}
