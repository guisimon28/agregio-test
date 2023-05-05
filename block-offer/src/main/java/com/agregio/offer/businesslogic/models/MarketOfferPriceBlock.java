package com.agregio.offer.businesslogic.models;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class MarketOfferPriceBlock {

	private final HourRange hourRange;

	private final double floorPriceInEuros;
	private final List<UUID> productionParkIds;

	public MarketOfferPriceBlock(Builder builder) {
		LocalTime startHour = builder.startHour.truncatedTo(ChronoUnit.HOURS)
				.withHour((builder.startHour.getHour() / ProductionParkCapacityBlock.BLOCK_HOUR) * ProductionParkCapacityBlock.BLOCK_HOUR);
		this.hourRange = new HourRange(startHour, startHour.plusHours(ProductionParkCapacityBlock.BLOCK_HOUR));
		this.floorPriceInEuros = builder.floorPriceInEuros;
		this.productionParkIds = builder.productionParkIds;
	}

	public HourRange getHourRange() {
		return hourRange;
	}

	public double getFloorPriceInEuros() {
		return floorPriceInEuros;
	}

	public List<UUID> getProductionParkIds() {
		return productionParkIds;
	}

	public static class Builder {

		private LocalTime startHour;
		private double floorPriceInEuros;
		private List<UUID> productionParkIds;

		public Builder withStartHour(LocalTime startHour) {
			this.startHour = startHour;
			return this;
		}

		public Builder withFloorPriceInEuros(double floorPriceInEuros) {
			this.floorPriceInEuros = floorPriceInEuros;
			return this;
		}

		public Builder withProductionParkIds(List<UUID> productionParkIds) {
			this.productionParkIds = productionParkIds;
			return this;
		}

		public MarketOfferPriceBlock build() {
			return new MarketOfferPriceBlock(this);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		MarketOfferPriceBlock that = (MarketOfferPriceBlock) o;
		return Double.compare(that.floorPriceInEuros, floorPriceInEuros) == 0 && Objects.equals(hourRange, that.hourRange) && Objects.equals(productionParkIds,
				that.productionParkIds);
	}

	@Override
	public int hashCode() {
		return Objects.hash(hourRange, floorPriceInEuros, productionParkIds);
	}

	@Override
	public String toString() {
		return "MarketOfferPriceBlock{" + "hourRange=" + hourRange + ", floorPriceInEuros=" + floorPriceInEuros + ", productionParkIds=" + productionParkIds
				+ '}';
	}
}