package com.agregio.offer.unit.usecases;

import static java.util.UUID.fromString;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.agregio.offer.businesslogic.models.ProductionParkCapacityBlock;
import com.agregio.offer.businesslogic.models.ProductionPark;
import com.agregio.offer.businesslogic.models.ProductionParkType;
import com.agregio.offer.businesslogic.usecases.CreateProductionPark;
import com.agregio.offer.adapters.secondary.gateways.repositories.InMemoryProductionParkStub;
import com.agregio.offer.adapters.secondary.gateways.uuidgeneration.DeterministicUuidGenerator;

class CreateProductionParkTest {
	private final InMemoryProductionParkStub productionParkRepository = new InMemoryProductionParkStub();

	private final DeterministicUuidGenerator uuidGenerator = new DeterministicUuidGenerator();

	private final UUID productionParkId = fromString("279eb3fd-6b98-4e97-b3d8-f8dadec09c9f");

	private final List<ProductionParkCapacityBlock> capacities = List.of(new ProductionParkCapacityBlock(LocalTime.of(3, 0), 10.3), new ProductionParkCapacityBlock(LocalTime.of(12, 0), 11.3),
			new ProductionParkCapacityBlock(LocalTime.of(18, 0), 12.3));

	@BeforeEach
	void setUp() {
		uuidGenerator.setNextId(productionParkId);
	}

	@Test
	void should_get_from_repo_when_create_production_park() {
		ProductionParkType type = ProductionParkType.SOLAR;
		String productionParkName = "Solar A";
		createProductionPark(productionParkName, type, capacities);

		assertProductionPark(
				new ProductionPark.Builder().withId(productionParkId).withName(productionParkName).withType(type).withCapacity(capacities).build());

	}

	void createProductionPark(String name, ProductionParkType type, List<ProductionParkCapacityBlock> capacities) {
		new CreateProductionPark(productionParkRepository, uuidGenerator).handle(name, type, capacities);
	}

	private void assertProductionPark(ProductionPark... productionPark) {
		assertThat(productionParkRepository.findAll()).containsExactly(productionPark);
	}

}
