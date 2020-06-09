package com.bentamayo.poc.graphql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bentamayo.poc.graphql.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	/*
	 * @GraphQLQuery(name = "AllUserByFilter") public List<User>
	 * getAllUserByFilter(@GraphQLArgument(name = "skip", defaultValue = "0") Number
	 * skip,
	 * 
	 * @GraphQLArgument(name = "first", defaultValue = "10") Number first,
	 * 
	 * @GraphQLArgument(name = "filter", defaultValue = "0") User user) {
	 * 
	 * BooleanExpression exp = null;
	 * 
	 * if (ObjectUtils.isEmpty(user.getFirstname())) { exp =
	 * QUser.user.firstname.containsIgnoreCase(user.getFirstname())
	 * .or(QUser.user.lastname.containsIgnoreCase(user.getLastname())); }
	 * 
	 * PageRequest pageRequest = PageRequest.of(skip.intValue(), first.intValue());
	 * Page<User> result = userRepository.findAll(exp, pageRequest); List<User>
	 * finalResult = result.getContent(); return finalResult; }
	 */

}
