package com.agregio.offer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.agregio.offer")
public class AgregioOfferAppLauncher {

	public static void main(String[] args) {
		SpringApplication.run(AgregioOfferAppLauncher.class, args);
	}
}