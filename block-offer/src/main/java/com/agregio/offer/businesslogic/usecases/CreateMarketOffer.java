package com.agregio.offer.businesslogic.usecases;

import java.util.List;

import com.agregio.offer.businesslogic.gateways.repositories.MarketOfferRepository;
import com.agregio.offer.businesslogic.gateways.uuidgeneration.UuidGenerator;
import com.agregio.offer.businesslogic.models.MarketOffer;
import com.agregio.offer.businesslogic.models.MarketOfferPriceBlock;
import com.agregio.offer.businesslogic.models.MarketType;

public class CreateMarketOffer {

	private final MarketOfferRepository marketOfferRepository;
	private final UuidGenerator uuidGenerator;

	public CreateMarketOffer(MarketOfferRepository marketOfferRepository, UuidGenerator uuidGenerator) {
		this.marketOfferRepository = marketOfferRepository;
		this.uuidGenerator = uuidGenerator;
	}

	public void handle(MarketType type, List<MarketOfferPriceBlock> priceBlocks) {
		MarketOffer marketOffer = new MarketOffer.Builder().withId(uuidGenerator.generate())
				.withMarketType(type)
				.withPriceBlocks(priceBlocks)
				.build();

		marketOfferRepository.save(marketOffer);
	}
}
