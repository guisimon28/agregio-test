package com.agregio.offer.adapters.secondary.gateways.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.agregio.offer.businesslogic.gateways.repositories.MarketOfferRepository;
import com.agregio.offer.businesslogic.gateways.repositories.ProductionParkRepository;
import com.agregio.offer.businesslogic.models.MarketOffer;
import com.agregio.offer.businesslogic.models.ProductionPark;

public class InMemoryMarketOfferStub implements MarketOfferRepository {

    private final List<MarketOffer> marketOffers = new ArrayList<>();

    public void save(MarketOffer marketOffer) {
        marketOffers.add(marketOffer);
    }

    public List<MarketOffer> findAll() {
        return marketOffers;
    }

    // SECRET METHOD
    public void setProductionParks(MarketOffer... marketOffers) {
        Collections.addAll(this.marketOffers, marketOffers);
    }
}
