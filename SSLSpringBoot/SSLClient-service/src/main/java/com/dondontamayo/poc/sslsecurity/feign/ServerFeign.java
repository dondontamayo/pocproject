package com.dondontamayo.poc.sslsecurity.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.dondontamayo.poc.sslsecurity.config.ClientConfig;

@FeignClient(name="ssl-server-service", url="https://localhost:8081", configuration = ClientConfig.class)
public interface ServerFeign {
	
	@GetMapping("/api/server/greeting")
	public String getGreeting();
	

}
