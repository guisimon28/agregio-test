package com.agregio.offer.adapters.primary.springboot;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CreateProductionParkCapacityBlockParams {
	@JsonFormat(pattern = "hh:mm:ss")
	LocalTime hour;
	double capacityInMwh;

	public CreateProductionParkCapacityBlockParams(LocalTime hour, double capacityInMwh) {
		this.hour = hour;
		this.capacityInMwh = capacityInMwh;
	}

}