package com.agregio.offer.adapters.primary.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.agregio.offer.adapters.secondary.gateways.repositories.InMemoryMarketOfferStub;
import com.agregio.offer.adapters.secondary.gateways.repositories.InMemoryProductionParkStub;
import com.agregio.offer.adapters.secondary.gateways.uuidgeneration.RandomUuidGenerator;
import com.agregio.offer.businesslogic.gateways.repositories.MarketOfferRepository;
import com.agregio.offer.businesslogic.gateways.repositories.ProductionParkRepository;
import com.agregio.offer.businesslogic.gateways.uuidgeneration.UuidGenerator;
import com.agregio.offer.businesslogic.usecases.CreateMarketOffer;
import com.agregio.offer.businesslogic.usecases.CreateProductionPark;
import com.agregio.offer.businesslogic.usecases.GetGlobalMarketOffer;

@Configuration
@Profile("inmemory")
public class DependenciesConfiguration {

	@Bean
	public CreateProductionPark createProductionPark(ProductionParkRepository productionParkRepository, UuidGenerator uuidGenerator) {
		return new CreateProductionPark(productionParkRepository, uuidGenerator);
	}

	@Bean
	public CreateMarketOffer createMarketOffer(MarketOfferRepository marketOfferRepository, UuidGenerator uuidGenerator) {
		return new CreateMarketOffer(marketOfferRepository, uuidGenerator);
	}

	@Bean
	public GetGlobalMarketOffer getGlobalMarketOffer(MarketOfferRepository marketOfferRepository, ProductionParkRepository productionParkRepository) {
		return new GetGlobalMarketOffer(marketOfferRepository, productionParkRepository);
	}

	@Bean
	public MarketOfferRepository marketOfferRepository() {
		return new InMemoryMarketOfferStub();
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