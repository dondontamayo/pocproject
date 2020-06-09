package com.bentamayo.poc.graphql.exception;

public class GenericGraphQLException extends RuntimeException {
	
	private static final long serialVersionUID = -1234093498247L;
	
	private String invalidField;
	
	public GenericGraphQLException(String message) {
		super(message);
	}
	
	public GenericGraphQLException(String message, String invalidField) {
		super(message);
		this.invalidField = invalidField;
	}

}
