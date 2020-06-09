package com.bentamayo.poc.graphql.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphql.ExceptionWhileDataFetching;
import graphql.execution.ExecutionPath;
import graphql.language.SourceLocation;

public class CustomGraphQLError extends ExceptionWhileDataFetching {
	
	private static final long serialVersionUID = -1234093498347L;

	public CustomGraphQLError(ExecutionPath path, Throwable exception, SourceLocation sourceLocation) {
		super(path, exception, sourceLocation);
	}
	
	@Override
	public String getMessage() {
		Throwable exception = getException();
		if (exception instanceof GenericGraphQLException) {
			String message = ((GenericGraphQLException) exception).getMessage();
			return message;
		}
		return null;
	}
	
	@Override
	public Map<String, Object> getExtensions() {
		Throwable exception = getException();
		if (exception instanceof GenericGraphQLException) {
			String message = ((GenericGraphQLException) exception).getMessage();
			Map<String, Object> attributes = new HashMap<>();
			return attributes;
		}
		return null;
	}
	
	@Override
	public List<SourceLocation> getLocations() {
		return null;
	}

}
