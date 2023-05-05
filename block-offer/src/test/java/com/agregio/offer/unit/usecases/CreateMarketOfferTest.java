package com.agregio.offer.unit.usecases;

import static java.util.UUID.fromString;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.agregio.offer.adapters.secondary.gateways.repositories.InMemoryMarketOfferStub;
import com.agregio.offer.adapters.secondary.gateways.uuidgeneration.DeterministicUuidGenerator;
import com.agregio.offer.businesslogic.models.MarketOffer;
import com.agregio.offer.businesslogic.models.MarketOfferPriceBlock;
import com.agregio.offer.businesslogic.models.MarketType;
import com.agregio.offer.businesslogic.usecases.CreateMarketOffer;

class CreateMarketOfferTest {
	private final InMemoryMarketOfferStub marketOfferRepository = new InMemoryMarketOfferStub();

	private final DeterministicUuidGenerator uuidGenerator = new DeterministicUuidGenerator();

	private final UUID productionParkId = fromString("279eb3fd-6b98-4e97-b3d8-f8dadec09c9f");

	private final List<MarketOfferPriceBlock> priceBlocks = List.of(new MarketOfferPriceBlock.Builder().withStartHour(LocalTime.of(12, 0))
			.withFloorPriceInEuros(1234.50)
			.withProductionParkIds(List.of(productionParkId))
			.build());

	@BeforeEach
	void setUp() {
		uuidGenerator.setNextId(productionParkId);
	}

	@Test
	void should_get_from_repo_when_create_market_offer() {
		createMarketOffer(MarketType.FAST, priceBlocks);

		assertMarketOffer(new MarketOffer.Builder().withId(productionParkId)
				.withMarketType(MarketType.FAST)
				.withPriceBlocks(priceBlocks)
				.build());
	}

	void createMarketOffer(MarketType type, List<MarketOfferPriceBlock> priceBlocks) {
		new CreateMarketOffer(marketOfferRepository, uuidGenerator).handle(type, priceBlocks);
	}

	private void assertMarketOffer(MarketOffer... marketOffers) {
		assertThat(marketOfferRepository.findAll()).containsExactly(marketOffers);
	}

}
