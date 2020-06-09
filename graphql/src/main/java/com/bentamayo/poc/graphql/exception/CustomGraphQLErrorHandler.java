package com.bentamayo.poc.graphql.exception;

import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.ExecutionPath;
import graphql.language.SourceLocation;

public class CustomGraphQLErrorHandler implements DataFetcherExceptionHandler {

	@Override
	public void accept(DataFetcherExceptionHandlerParameters handlerParameters) {
		Throwable exception = handlerParameters.getException();
		SourceLocation sourceLocation = handlerParameters.getField().getSourceLocation();
		ExecutionPath path = handlerParameters.getPath();
		
		CustomGraphQLError error = new CustomGraphQLError(path, exception, sourceLocation);
		handlerParameters.getExecutionContext().addError(error);
		
	}

}
