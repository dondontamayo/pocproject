package com.dondontamayo.poc.sslsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/server")
public class ServerController {
	
	@GetMapping("/greeting")
	public String getauthenticationStatus() {
		return "One Way Authentication...from Serverside";
	}

}
