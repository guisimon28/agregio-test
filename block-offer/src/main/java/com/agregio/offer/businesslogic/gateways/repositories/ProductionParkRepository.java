package com.agregio.offer.businesslogic.gateways.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.agregio.offer.businesslogic.models.ProductionPark;

public interface ProductionParkRepository {

	void save(ProductionPark productionPark);

	Optional<ProductionPark> findById(UUID id);

	List<ProductionPark> findAll();
}
