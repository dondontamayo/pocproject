package com.bentamayo.poc.graphql.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bentamayo.poc.graphql.exception.CustomGraphQLErrorHandler;
import com.bentamayo.poc.graphql.service.UserService;

import graphql.GraphQL;
import graphql.execution.AsyncExecutionStrategy;
import graphql.execution.AsyncSerialExecutionStrategy;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.query.PublicResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;

@Configuration
public class GraphqlConfig {
	
	@Autowired(required = true)
	private UserService userService;
	
	@Bean 
	public GraphQL getGraphQLInstance(UserService userService) {
	  
		GraphQLSchema schema = new GraphQLSchemaGenerator().withResolverBuilders(new AnnotatedResolverBuilder(), new PublicResolverBuilder("com.bentamayo.poc.graphql"))
		  .withOperationsFromSingleton(userService)
		  .withValueMapperFactory(new JacksonValueMapperFactory()).generate();
		  
		return GraphQL.newGraphQL(schema).queryExecutionStrategy(new AsyncExecutionStrategy(new CustomGraphQLErrorHandler()))
		  .mutationExecutionStrategy(new AsyncSerialExecutionStrategy(new CustomGraphQLErrorHandler())).build();
	  
	  }
	 
	
}
