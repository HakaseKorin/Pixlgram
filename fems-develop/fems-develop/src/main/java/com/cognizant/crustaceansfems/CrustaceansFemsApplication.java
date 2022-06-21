package com.cognizant.crustaceansfems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CrustaceansFemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrustaceansFemsApplication.class, args);
	}

}
