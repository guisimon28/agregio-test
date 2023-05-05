package com.agregio.offer.businesslogic.gateways.repositories;

import java.util.List;

import com.agregio.offer.businesslogic.models.MarketOffer;
import com.agregio.offer.businesslogic.models.ProductionPark;

public interface MarketOfferRepository {

	void save(MarketOffer marketOffer);

	List<MarketOffer> findAll();
}
