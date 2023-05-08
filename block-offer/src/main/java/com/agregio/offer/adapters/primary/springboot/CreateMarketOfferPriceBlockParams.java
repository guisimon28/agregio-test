package com.agregio.offer.adapters.primary.springboot;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateMarketOfferPriceBlockParams {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	@Schema(description = "Début du bloc horaire", pattern = "HH:mm:ss")
	@JsonProperty
	LocalTime hour;

	@Schema(description = "Prix plancher en euros")
	@JsonProperty
	double floorPriceInEuros;

	@Schema(description = "List des identifiants de parcs producteurs")
	@JsonProperty
	List<UUID> productionParkIds;

	public CreateMarketOfferPriceBlockParams(LocalTime hour, double floorPriceInEuros, List<UUID> productionParkIds) {
		this.hour = hour;
		this.floorPriceInEuros = floorPriceInEuros;
		this.productionParkIds = productionParkIds;
	}
}