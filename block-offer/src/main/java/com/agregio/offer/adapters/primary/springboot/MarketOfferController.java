package com.agregio.offer.adapters.primary.springboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agregio.offer.businesslogic.models.MarketOfferPriceBlock;
import com.agregio.offer.businesslogic.usecases.CreateMarketOffer;

@RestController
public class MarketOfferController {

	private final CreateMarketOffer createMarketOffer;

	public MarketOfferController(CreateMarketOffer createMarketOffer) {
		this.createMarketOffer = createMarketOffer;
	}

	@PostMapping(path = "/market/offer/create")
	public ResponseEntity<Void> createMarketOffer(@RequestBody CreateMarketOfferParams createMarketOfferParams) {
		createMarketOffer.handle(createMarketOfferParams.type, convertToPriceBlock(createMarketOfferParams.priceBlocks));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	private List<MarketOfferPriceBlock> convertToPriceBlock(List<CreateMarketOfferPriceBlockParams> params) {
		List<MarketOfferPriceBlock> capacities = new ArrayList<>();
		params.forEach(param -> capacities.add(new MarketOfferPriceBlock.Builder().withFloorPriceInEuros(param.floorPriceInEuros)
				.withStartHour(param.hour)
				.withProductionParkIds(param.productionParkIds)
				.build()));
		return capacities;
	}
}
