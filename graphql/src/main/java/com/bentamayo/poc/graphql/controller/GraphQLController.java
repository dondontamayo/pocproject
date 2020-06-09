package com.bentamayo.poc.graphql.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;

@RestController
@RequestMapping("/api/sample/user")
public class GraphQLController {
	
	
	  @Autowired private GraphQL graphql;
	  
	  @PostMapping public ExecutionResult getAllUser(@RequestBody Map<String,
	  Object> request, HttpServletRequest raw) {
	  
	  @SuppressWarnings("unchecked") ExecutionResult result =
	  graphql.execute(ExecutionInput.newExecutionInput()
	  .query((String)request.get("query"))
	  .operationName((String)request.get("operationName"))
	  .variables((Map<String,Object>)request.get("variables")) .context(raw)
	  .build());
	  
	  return result;
	  
	  }
	 
}
