package com.agregio.offer.adapters.primary.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.agregio.offer.adapters.secondary.gateways.repositories.InMemoryProductionParkStub;
import com.agregio.offer.adapters.secondary.gateways.uuidgeneration.RandomUuidGenerator;
import com.agregio.offer.businesslogic.gateways.repositories.ProductionParkRepository;
import com.agregio.offer.businesslogic.gateways.uuidgeneration.UuidGenerator;
import com.agregio.offer.businesslogic.usecases.CreateProductionPark;

@Configuration
@Profile("inmemory")
public class DependenciesConfiguration {

	@Bean
	public CreateProductionPark createProductionPark(ProductionParkRepository productionParkRepository, UuidGenerator uuidGenerator) {
		return new CreateProductionPark(productionParkRepository, uuidGenerator);
	}

	@Bean
	public ProductionParkRepository productionParkRepository() {
		return new InMemoryProductionParkStub();
	}

	@Bean
	public UuidGenerator uuidGenerator() {
		return new RandomUuidGenerator();
	}

}