package com.agregio.offer.adapters.primary.springboot;

import java.util.List;

import com.agregio.offer.businesslogic.models.ProductionParkType;

public class CreateProductionParkParams {
	String name;
	ProductionParkType type;
	List<CreateProductionParkCapacityBlockParams> capacities;

	public CreateProductionParkParams(String name, ProductionParkType type, List<CreateProductionParkCapacityBlockParams> capacities) {
		this.name = name;
		this.type = type;
		this.capacities = capacities;
	}
}