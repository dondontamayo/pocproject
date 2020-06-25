package com.dondontamayo.poc.sslsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dondontamayo.poc.sslsecurity.feign.ServerFeign;

@RestController
@RequestMapping("/api/client")
public class ClientController {
	
	private ServerFeign client;
	@Autowired public void setServerFeign(ServerFeign client) {
		this.client = client;
		
	}

	@GetMapping("/greeting")
	public String getauthenticationStatus() {
		return "One Way Authentication...";
	}
	
	@GetMapping("/greeting/server")
	public String getauthenticationStatusFromServer() {
		String response = client.getGreeting();
		return response;
	}
}
