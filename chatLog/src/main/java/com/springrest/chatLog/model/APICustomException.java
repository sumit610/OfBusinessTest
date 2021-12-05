package com.springrest.chatLog.model;

import java.util.LinkedHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ch.qos.logback.core.status.Status;

public class APICustomException {

	private Object genericError;
	ResponseEntity<HttpStatus> responseEntity;
	
	public Object getGenericError() {
		return genericError;
	}

	public void setGenericError(Object genericError) {
		this.genericError = genericError;
	}
	
	public ResponseEntity<HttpStatus> getResponseEntity() {
		return responseEntity;
	}

	public void setResponseEntity(ResponseEntity<HttpStatus> badRequest) {
		this.responseEntity = badRequest;
	}

	
}
