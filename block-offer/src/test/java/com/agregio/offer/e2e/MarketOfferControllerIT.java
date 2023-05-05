package com.agregio.offer.e2e;

import static java.util.UUID.fromString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalTime;
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
import com.agregio.offer.adapters.primary.springboot.CreateMarketOfferParams;
import com.agregio.offer.adapters.primary.springboot.CreateMarketOfferPriceBlockParams;
import com.agregio.offer.businesslogic.gateways.repositories.MarketOfferRepository;
import com.agregio.offer.businesslogic.models.MarketOffer;
import com.agregio.offer.businesslogic.models.MarketOfferPriceBlock;
import com.agregio.offer.businesslogic.models.MarketType;
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
class MarketOfferControllerIT {

	@Autowired
	protected MockMvc mvc;

	@Autowired
	private MarketOfferRepository marketOfferRepository;

	private final UUID productionParkId = fromString("279eb3fd-6b98-4e97-b3d8-f8dadec09c9f");

	@Test
	void shoud_create_market_offer_when_post_from_rest_api() throws Exception {
		CreateMarketOfferParams params = new CreateMarketOfferParams(MarketType.PRIMARY,
				List.of(new CreateMarketOfferPriceBlockParams(LocalTime.of(8, 0), 1235.0, List.of(productionParkId))));

		String content = new ObjectMapper().registerModule(new JavaTimeModule())
				.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
				.writeValueAsString(params);

		mvc.perform(post("/market/offer/create").contentType("application/json").content(content)).andExpect(status().isCreated());

		assertThat(marketOfferRepository.findAll()).containsExactly(new MarketOffer.Builder().withId(UUID.fromString("5b3eb3fa-1f1d-4aac-83b4-ef3a654033ac"))
				.withMarketType(MarketType.PRIMARY)
				.withPriceBlocks(List.of(new MarketOfferPriceBlock.Builder().withFloorPriceInEuros(1235.0)
						.withStartHour(LocalTime.of(6, 0))
						.withProductionParkIds(List.of(productionParkId))
						.build()))
				.build());
	}

}