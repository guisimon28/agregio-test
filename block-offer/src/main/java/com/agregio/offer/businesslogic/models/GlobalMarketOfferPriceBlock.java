package com.agregio.offer.businesslogic.models;

import java.util.Objects;

public class GlobalMarketOfferPriceBlock {

	private final HourRange hourRange;
	private final double floorPriceInEuros;
	private final double capacityInMwh;

	public GlobalMarketOfferPriceBlock(Builder builder) {
		this.hourRange = builder.hourRange;
		this.floorPriceInEuros = builder.floorPriceInEuros;
		this.capacityInMwh = builder.capacityInMwh;
	}

	public HourRange getHourRange() {
		return hourRange;
	}

	public double getFloorPriceInEuros() {
		return floorPriceInEuros;
	}

	public double getCapacityInMwh() {
		return capacityInMwh;
	}

	public static class Builder {

		private HourRange hourRange;
		private double floorPriceInEuros;
		private double capacityInMwh;

		public Builder withHourRange(HourRange hourRange) {
			this.hourRange = hourRange;
			return this;
		}

		public Builder withFloorPriceInEuros(double floorPriceInEuros) {
			this.floorPriceInEuros = floorPriceInEuros;
			return this;
		}

		public Builder withCapacityInMwh(double capacityInMwh) {
			this.capacityInMwh = capacityInMwh;
			return this;
		}

		public GlobalMarketOfferPriceBlock build() {
			return new GlobalMarketOfferPriceBlock(this);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		GlobalMarketOfferPriceBlock that = (GlobalMarketOfferPriceBlock) o;
		return Double.compare(that.floorPriceInEuros, floorPriceInEuros) == 0 && Double.compare(that.capacityInMwh, capacityInMwh) == 0 && Objects.equals(
				hourRange, that.hourRange);
	}

	@Override
	public int hashCode() {
		return Objects.hash(hourRange, floorPriceInEuros, capacityInMwh);
	}

	@Override
	public String toString() {
		return "GetComplexMarketOfferPriceBlock{" + "hourRange=" + hourRange + ", floorPriceInEuros=" + floorPriceInEuros + ", capacityInMwh=" + capacityInMwh
				+ '}';
	}
}