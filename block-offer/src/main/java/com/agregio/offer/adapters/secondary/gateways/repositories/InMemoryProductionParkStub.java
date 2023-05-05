package com.agregio.offer.adapters.secondary.gateways.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.agregio.offer.businesslogic.gateways.repositories.ProductionParkRepository;
import com.agregio.offer.businesslogic.models.ProductionPark;

public class InMemoryProductionParkStub implements ProductionParkRepository {

    private final List<ProductionPark> productionParks = new ArrayList<>();

    public void save(ProductionPark productionPark) {
        productionParks.add(productionPark);
    }

    public List<ProductionPark> findAll() {
        return productionParks;
    }

    // SECRET METHOD
    public void setProductionParks(ProductionPark... productionParks) {
        Collections.addAll(this.productionParks, productionParks);
    }
}
