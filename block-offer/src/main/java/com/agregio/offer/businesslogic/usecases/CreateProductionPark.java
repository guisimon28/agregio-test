package com.agregio.offer.businesslogic.usecases;

import java.util.List;

import com.agregio.offer.businesslogic.gateways.repositories.ProductionParkRepository;
import com.agregio.offer.businesslogic.gateways.uuidgeneration.UuidGenerator;
import com.agregio.offer.businesslogic.models.ProductionParkCapacityBlock;
import com.agregio.offer.businesslogic.models.ProductionPark;
import com.agregio.offer.businesslogic.models.ProductionParkType;

public class CreateProductionPark {

	private final ProductionParkRepository productionParkRepository;
	private final UuidGenerator uuidGenerator;

	public CreateProductionPark(ProductionParkRepository productionParkRepository, UuidGenerator uuidGenerator) {
		this.productionParkRepository = productionParkRepository;
		this.uuidGenerator = uuidGenerator;
	}

	public void handle(String name, ProductionParkType type, List<ProductionParkCapacityBlock> capacities) {
		ProductionPark productionPark = new ProductionPark.Builder().withId(uuidGenerator.generate())
				.withName(name)
				.withType(type)
				.withCapacity(capacities)
				.build();

		productionParkRepository.save(productionPark);
	}
}
