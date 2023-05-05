package com.agregio.offer.e2e;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.agregio.offer.AgregioOfferAppLauncher;
import com.agregio.offer.adapters.primary.springboot.CreateProductionParkCapacityBlockParams;
import com.agregio.offer.adapters.primary.springboot.CreateProductionParkParams;
import com.agregio.offer.businesslogic.gateways.repositories.ProductionParkRepository;
import com.agregio.offer.businesslogic.models.ProductionParkCapacityBlock;
import com.agregio.offer.businesslogic.models.ProductionPark;
import com.agregio.offer.businesslogic.models.ProductionParkType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("inmemory-e2e")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AgregioOfferAppLauncher.class)
@TestPropertySource(locations = "classpath:application-inmemory.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@Import({ DependenciesConfigurationE2E.class })
@AutoConfigureMockMvc
class ProductionParkControllerIT {

	@Autowired
	protected MockMvc mvc;

	@Autowired
	private ProductionParkRepository productionParkRepository;

	@Test
	void shoud_create_production_park_when_post_from_rest_api() throws Exception {
		CreateProductionParkParams params = new CreateProductionParkParams("My Park", ProductionParkType.SOLAR,
				List.of(new CreateProductionParkCapacityBlockParams(LocalTime.of(8, 0), 10.0)));

		String content = new ObjectMapper().registerModule(new JavaTimeModule())
				.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
				.writeValueAsString(params);

		mvc.perform(post("/parks/create").contentType("application/json").content(content)).andExpect(status().isCreated());

		assertThat(productionParkRepository.findAll()).containsExactly(
				new ProductionPark.Builder().withId(UUID.fromString("5b3eb3fa-1f1d-4aac-83b4-ef3a654033ac"))
						.withType(ProductionParkType.SOLAR)
						.withName("My Park")
						.withCapacity(Collections.singletonList(new ProductionParkCapacityBlock(LocalTime.of(6, 0), 10.0)))
						.build());
	}

}
