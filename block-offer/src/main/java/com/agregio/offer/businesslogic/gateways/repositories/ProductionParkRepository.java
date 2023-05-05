package com.agregio.offer.businesslogic.gateways.repositories;

import java.util.List;

import com.agregio.offer.businesslogic.models.ProductionPark;

public interface ProductionParkRepository {

	void save(ProductionPark productionPark);

	List<ProductionPark> findAll();
}
