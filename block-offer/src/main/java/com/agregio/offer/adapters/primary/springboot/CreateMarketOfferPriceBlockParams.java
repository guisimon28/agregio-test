package com.agregio.offer.adapters.primary.springboot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import com.agregio.offer.businesslogic.models.MarketType;
import com.agregio.offer.businesslogic.models.ProductionParkType;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CreateMarketOfferPriceBlockParams {
	@JsonFormat(pattern = "hh:mm:ss")
	LocalTime hour;

	double floorPriceInEuros;

	List<UUID> productionParkIds;

	public CreateMarketOfferPriceBlockParams(LocalTime hour, double floorPriceInEuros, List<UUID> productionParkIds) {
		this.hour = hour;
		this.floorPriceInEuros = floorPriceInEuros;
		this.productionParkIds = productionParkIds;
	}
}