package com.agregio.offer.adapters.primary.springboot;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateProductionParkCapacityBlockParams {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	@Schema(description = "début du bloc horaire", pattern = "HH:mm:ss")
	@JsonProperty
	LocalTime hour;

	@Schema(description = "capacité de production en Mwh")
	@JsonProperty
	double capacityInMwh;

	public CreateProductionParkCapacityBlockParams(LocalTime hour, double capacityInMwh) {
		this.hour = hour;
		this.capacityInMwh = capacityInMwh;
	}

}