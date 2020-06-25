package com.dondontamayo.poc.sslsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SslSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslSecurityApplication.class, args);
	}

}
