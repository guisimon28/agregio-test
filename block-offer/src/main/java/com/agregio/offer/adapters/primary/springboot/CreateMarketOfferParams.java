package com.agregio.offer.adapters.primary.springboot;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.agregio.offer.businesslogic.models.MarketType;
import com.agregio.offer.businesslogic.models.ProductionParkType;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CreateMarketOfferParams {
	MarketType type;
	List<CreateMarketOfferPriceBlockParams> priceBlocks;

	public CreateMarketOfferParams(MarketType type, List<CreateMarketOfferPriceBlockParams> priceBlocks) {
		this.type = type;
		this.priceBlocks = priceBlocks;
	}
}