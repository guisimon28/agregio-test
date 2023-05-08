package com.agregio.offer.adapters.primary.springboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agregio.offer.businesslogic.gateways.repositories.ProductionParkRepository;
import com.agregio.offer.businesslogic.models.ProductionPark;
import com.agregio.offer.businesslogic.models.ProductionParkCapacityBlock;
import com.agregio.offer.businesslogic.usecases.CreateProductionPark;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Parc de production éléctrique")
@RestController
public class ProductionParkController {

	private final CreateProductionPark createProductionPark;

	private final ProductionParkRepository productionParkRepository;

	public ProductionParkController(CreateProductionPark createProductionPark, ProductionParkRepository productionParkRepository) {
		this.createProductionPark = createProductionPark;
		this.productionParkRepository = productionParkRepository;
	}

	@Operation(summary = "Création d'un parc de production éléctrique",
			description = "pour créer un parc de production, il faut choisir son type, son nom et sa capacité de production pour chaque bloc horaire")
	@PostMapping(path = "/parks/create")
	public ResponseEntity<Void> createProductionPark(@RequestBody CreateProductionParkParams createProductionParkParams) {
		createProductionPark.handle(createProductionParkParams.name, createProductionParkParams.type,
				convertToCapacityBlock(createProductionParkParams.capacities));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@Operation(summary = "Récupération des parcs de production éléctrique", description = "liste de l'ensemble des parcs de production créés")
	@GetMapping(path = "/parks/find")
	public ResponseEntity<List<ProductionPark>> getAllProductionPark() {
		return new ResponseEntity<>(productionParkRepository.findAll(), HttpStatus.OK);
	}

	private List<ProductionParkCapacityBlock> convertToCapacityBlock(List<CreateProductionParkCapacityBlockParams> params) {
		List<ProductionParkCapacityBlock> capacities = new ArrayList<>();
		params.forEach(param -> capacities.add(new ProductionParkCapacityBlock(param.hour, param.capacityInMwh)));
		return capacities;
	}
}
