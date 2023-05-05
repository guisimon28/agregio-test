package com.agregio.offer.adapters.secondary.gateways.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.agregio.offer.businesslogic.gateways.repositories.MarketOfferRepository;
import com.agregio.offer.businesslogic.models.MarketOffer;
import com.agregio.offer.businesslogic.models.MarketType;

public class InMemoryMarketOfferStub implements MarketOfferRepository {

    private final List<MarketOffer> marketOffers = new ArrayList<>();

    public void save(MarketOffer marketOffer) {
        marketOffers.add(marketOffer);
    }

    public List<MarketOffer> findAll() {
        return marketOffers;
    }

    public Optional<MarketOffer> findByType(MarketType type){
        return marketOffers.stream().filter(o -> o.getMarketType() == type).findFirst();
    }

    // SECRET METHOD
    public void setOffers(MarketOffer... marketOffers) {
        Collections.addAll(this.marketOffers, marketOffers);
    }
}
