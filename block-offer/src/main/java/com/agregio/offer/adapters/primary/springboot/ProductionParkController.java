package com.agregio.offer.adapters.primary.springboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agregio.offer.businesslogic.models.ProductionParkCapacityBlock;
import com.agregio.offer.businesslogic.usecases.CreateProductionPark;

@RestController
public class ProductionParkController {

	private final CreateProductionPark createProductionPark;

	public ProductionParkController(CreateProductionPark createProductionPark) {
		this.createProductionPark = createProductionPark;
	}

	@PostMapping(path = "/parks/create")
	public ResponseEntity<Void> createProductionPark(@RequestBody CreateProductionParkParams createProductionParkParams) {
		createProductionPark.handle(createProductionParkParams.name, createProductionParkParams.type,
				convertToCapacityBlock(createProductionParkParams.capacities));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	private List<ProductionParkCapacityBlock> convertToCapacityBlock(List<CreateProductionParkCapacityBlockParams> params) {
		List<ProductionParkCapacityBlock> capacities = new ArrayList<>();
		params.forEach(param -> capacities.add(new ProductionParkCapacityBlock(param.hour, param.capacityInMwh)));
		return capacities;
	}
}
