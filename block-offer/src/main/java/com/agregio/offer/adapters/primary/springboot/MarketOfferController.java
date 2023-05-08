package com.agregio.offer.adapters.primary.springboot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agregio.offer.businesslogic.models.GlobalMarketOffer;
import com.agregio.offer.businesslogic.models.MarketOfferPriceBlock;
import com.agregio.offer.businesslogic.models.MarketType;
import com.agregio.offer.businesslogic.usecases.CreateMarketOffer;
import com.agregio.offer.businesslogic.usecases.GetGlobalMarketOffer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Offre sur un marché")
@RestController
public class MarketOfferController {

	private final CreateMarketOffer createMarketOffer;

	private final GetGlobalMarketOffer getGlobalMarketOffer;

	public MarketOfferController(CreateMarketOffer createMarketOffer, GetGlobalMarketOffer getGlobalMarketOffer) {
		this.createMarketOffer = createMarketOffer;
		this.getGlobalMarketOffer = getGlobalMarketOffer;
	}

	@Operation(summary = "Créer une offre sur un marché",
			description = "Pour placer une offre sur un marché, il est necessaire de choisir le type de marché et ensuite pour chaque bloc horaire le prix plancher et les parcs de productions choisis")
	@PostMapping(path = "/market/offer/create")
	public ResponseEntity<Void> createMarketOffer(@RequestBody CreateMarketOfferParams createMarketOfferParams) {
		createMarketOffer.handle(createMarketOfferParams.type, convertToPriceBlock(createMarketOfferParams.priceBlocks));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@Operation(summary = "Récupérer les offres d'un marché",
			description = "Lors de la récupération des offres sur un marché, on globalise les énergies proposés par blocs horaires pour tous les parcs de production")
	@GetMapping(path = "/market/offer/{type}")
	public ResponseEntity<GlobalMarketOffer> getGlobalMarketOffer(@PathVariable MarketType type) {
		Optional<GlobalMarketOffer> globalMarketOffer = getGlobalMarketOffer.retrieve(type);
		if (globalMarketOffer.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(globalMarketOffer.get(), HttpStatus.OK);
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
