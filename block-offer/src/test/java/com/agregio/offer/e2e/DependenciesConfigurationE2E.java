package com.agregio.offer.e2e;

import static java.util.UUID.fromString;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.agregio.offer.adapters.secondary.gateways.repositories.InMemoryProductionParkStub;
import com.agregio.offer.adapters.secondary.gateways.uuidgeneration.DeterministicUuidGenerator;
import com.agregio.offer.businesslogic.gateways.repositories.ProductionParkRepository;
import com.agregio.offer.businesslogic.gateways.uuidgeneration.UuidGenerator;
import com.agregio.offer.businesslogic.usecases.CreateProductionPark;

@Configuration
@Profile("inmemory-e2e")
public class DependenciesConfigurationE2E {

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
		var uuidGenerator = new DeterministicUuidGenerator();
		uuidGenerator.setNextId(fromString("5b3eb3fa-1f1d-4aac-83b4-ef3a654033ac"));
		return uuidGenerator;
	}

}