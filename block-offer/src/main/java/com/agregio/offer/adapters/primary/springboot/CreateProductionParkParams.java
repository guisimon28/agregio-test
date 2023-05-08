package com.agregio.offer.adapters.primary.springboot;

import java.util.List;

import com.agregio.offer.businesslogic.models.ProductionParkType;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateProductionParkParams {
	@Schema(description = "nom du parc de production")
	@JsonProperty
	String name;

	@Schema(description = "type de parc de production")
	@JsonProperty
	ProductionParkType type;

	@Schema(description = "bloc horaire de capacité de production")
	@JsonProperty
	List<CreateProductionParkCapacityBlockParams> capacities;

	public CreateProductionParkParams(String name, ProductionParkType type, List<CreateProductionParkCapacityBlockParams> capacities) {
		this.name = name;
		this.type = type;
		this.capacities = capacities;
	}
}