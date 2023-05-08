package com.agregio.offer.adapters.primary.springboot;

import java.util.List;

import com.agregio.offer.businesslogic.models.MarketType;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "objet de création d'une offre sur un marché")
public class CreateMarketOfferParams {
	@Schema(description = "Type de marché")
	@JsonProperty
	MarketType type;

	@Schema(description = "Liste des blocs de prix")
	@JsonProperty
	List<CreateMarketOfferPriceBlockParams> priceBlocks;

	public CreateMarketOfferParams(MarketType type, List<CreateMarketOfferPriceBlockParams> priceBlocks) {
		this.type = type;
		this.priceBlocks = priceBlocks;
	}

}