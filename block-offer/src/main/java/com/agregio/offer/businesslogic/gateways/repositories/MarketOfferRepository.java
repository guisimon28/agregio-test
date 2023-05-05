package com.agregio.offer.businesslogic.gateways.repositories;

import java.util.List;
import java.util.Optional;

import com.agregio.offer.businesslogic.models.MarketOffer;
import com.agregio.offer.businesslogic.models.MarketType;
import com.agregio.offer.businesslogic.models.ProductionPark;

public interface MarketOfferRepository {

	void save(MarketOffer marketOffer);

	Optional<MarketOffer> findByType(MarketType type);

	List<MarketOffer> findAll();
}
