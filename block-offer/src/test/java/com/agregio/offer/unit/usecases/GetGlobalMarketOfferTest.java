package com.agregio.offer.unit.usecases;

import static java.util.UUID.fromString;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.agregio.offer.adapters.secondary.gateways.repositories.InMemoryMarketOfferStub;
import com.agregio.offer.adapters.secondary.gateways.repositories.InMemoryProductionParkStub;
import com.agregio.offer.adapters.secondary.gateways.uuidgeneration.DeterministicUuidGenerator;
import com.agregio.offer.businesslogic.models.GlobalMarketOffer;
import com.agregio.offer.businesslogic.models.GlobalMarketOfferPriceBlock;
import com.agregio.offer.businesslogic.models.HourRange;
import com.agregio.offer.businesslogic.models.MarketOffer;
import com.agregio.offer.businesslogic.models.MarketOfferPriceBlock;
import com.agregio.offer.businesslogic.models.MarketType;
import com.agregio.offer.businesslogic.models.ProductionPark;
import com.agregio.offer.businesslogic.models.ProductionParkCapacityBlock;
import com.agregio.offer.businesslogic.usecases.GetGlobalMarketOffer;

public class GetGlobalMarketOfferTest {

	private final InMemoryProductionParkStub productionParkRepository = new InMemoryProductionParkStub();
	private final InMemoryMarketOfferStub marketOfferRepository = new InMemoryMarketOfferStub();

	private final DeterministicUuidGenerator uuidGenerator = new DeterministicUuidGenerator();

	private final UUID solarProductionParkId = fromString("279eb3fd-6b98-4e97-b3d8-f8dadec09c9f");

	private final UUID hydroProductionParkId = fromString("e2274132-ed0f-11ed-a05b-0242ac120003");

	private final List<ProductionParkCapacityBlock> capacities = List.of(new ProductionParkCapacityBlock(LocalTime.of(3, 0), 10.3),
			new ProductionParkCapacityBlock(LocalTime.of(12, 0), 11.3), new ProductionParkCapacityBlock(LocalTime.of(18, 0), 12.3));

	private final List<MarketOfferPriceBlock> priceBlocks = List.of(new MarketOfferPriceBlock.Builder().withStartHour(LocalTime.of(12, 0))
			.withFloorPriceInEuros(1234.50)
			.withProductionParkIds(List.of(solarProductionParkId, hydroProductionParkId))
			.build());

	@BeforeEach
	void setUp() {
		uuidGenerator.setNextId(solarProductionParkId);
	}

	@Test
	void should_get_market_offer_with_global_capacities_when_market_offer_is_present() {
		createProductionParks();
		createMarketOffer();

		Optional<GlobalMarketOffer> globalMarketOffer = new GetGlobalMarketOffer(marketOfferRepository, productionParkRepository).retrieve(MarketType.PRIMARY);
		assertThat(globalMarketOffer).isPresent();

		GlobalMarketOfferPriceBlock expectedPriceBlock = new GlobalMarketOfferPriceBlock.Builder().withHourRange(
				new HourRange(LocalTime.of(12, 0), LocalTime.of(15, 0))).withFloorPriceInEuros(1234.50).withCapacityInMwh(22.6).build();
		assertThat(globalMarketOffer.get().getPriceBlocks()).containsExactly(expectedPriceBlock);
	}

	void createProductionParks() {
		productionParkRepository.setProductionParks(
				new ProductionPark.Builder().withId(solarProductionParkId).withName("Solar A").withCapacity(capacities).build(),
				new ProductionPark.Builder().withId(hydroProductionParkId).withName("Hydro A").withCapacity(capacities).build());
	}

	void createMarketOffer() {
		marketOfferRepository.setOffers(
				new MarketOffer.Builder().withMarketType(MarketType.PRIMARY).withId(UUID.randomUUID()).withPriceBlocks(priceBlocks).build());
	}

}
