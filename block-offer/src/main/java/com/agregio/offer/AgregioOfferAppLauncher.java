package com.agregio.offer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication(scanBasePackages = "com.agregio.offer")
@OpenAPIDefinition
public class AgregioOfferAppLauncher {

	public static void main(String[] args) {
		SpringApplication.run(AgregioOfferAppLauncher.class, args);
	}
}