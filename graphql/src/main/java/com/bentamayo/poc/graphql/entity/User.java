package com.bentamayo.poc.graphql.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;

@Data
@Entity
@Table(name = "USER_DB")
public class User implements Serializable {
	
	private static final Long searialVersionUID = -4832983423934L;
	
	@Id
	@GraphQLQuery(name = "userId", description="User ID")
	private Long userId;
	
	@GraphQLQuery(name = "firstname", description="Student First Nam")
	private String firstname;
	
	@GraphQLQuery(name = "lastname", description="Student Last Name")
	private String lastname;
	
	@GraphQLQuery(name = "address", description="Student address")
	private String address;
	
	@GraphQLQuery(name = "birthDate", description="Student date of birth")
	private LocalDate birthDate;
	

}
