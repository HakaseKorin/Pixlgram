package com.cognizant.crustaceanspostcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CrustaceansPostCrudApplication{

	public static void main(String[] args) {
		SpringApplication.run(CrustaceansPostCrudApplication.class, args);
	}

}
