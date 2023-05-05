package com.agregio.offer.businesslogic.usecases;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.agregio.offer.businesslogic.gateways.repositories.MarketOfferRepository;
import com.agregio.offer.businesslogic.gateways.repositories.ProductionParkRepository;
import com.agregio.offer.businesslogic.models.GlobalMarketOffer;
import com.agregio.offer.businesslogic.models.GlobalMarketOfferPriceBlock;
import com.agregio.offer.businesslogic.models.HourRange;
import com.agregio.offer.businesslogic.models.MarketOffer;
import com.agregio.offer.businesslogic.models.MarketOfferPriceBlock;
import com.agregio.offer.businesslogic.models.MarketType;
import com.agregio.offer.businesslogic.models.ProductionPark;

public class GetGlobalMarketOffer {

	private final MarketOfferRepository marketOfferRepository;

	private final ProductionParkRepository productionParkRepository;

	public GetGlobalMarketOffer(MarketOfferRepository marketOfferRepository, ProductionParkRepository productionParkRepository) {
		this.marketOfferRepository = marketOfferRepository;
		this.productionParkRepository = productionParkRepository;
	}

	public Optional<GlobalMarketOffer> retrieve(MarketType type) {
		Optional<MarketOffer> marketOffer = marketOfferRepository.findByType(type);
		if (marketOffer.isEmpty()) {
			return Optional.empty();
		}

		List<GlobalMarketOfferPriceBlock> blocks = new ArrayList<>();
		for (MarketOfferPriceBlock priceBlock : marketOffer.get().getPriceBlocks()) {
			double capacity = getCapacityForAllProductionParkAtRange(priceBlock);

			blocks.add(new GlobalMarketOfferPriceBlock.Builder().withHourRange(priceBlock.getHourRange())
					.withCapacityInMwh(capacity)
					.withFloorPriceInEuros(priceBlock.getFloorPriceInEuros())
					.build());
		}

		return Optional.of(new GlobalMarketOffer.Builder().withType(type).withPriceBlocks(blocks).build());

	}

	double getCapacityForAllProductionParkAtRange(MarketOfferPriceBlock priceBlock) {
		double result = 0.0;
		for (UUID id : priceBlock.getProductionParkIds()) {
			result += getCapacityForProductionParkAtRange(id, priceBlock.getHourRange());
		}
		return result;
	}

	double getCapacityForProductionParkAtRange(UUID productionParkId, HourRange range) {
		Optional<ProductionPark> productionPark = productionParkRepository.findById(productionParkId);
		return productionPark.map(p -> p.getCapacityForRange(range)).orElse(0.0);
	}
}
