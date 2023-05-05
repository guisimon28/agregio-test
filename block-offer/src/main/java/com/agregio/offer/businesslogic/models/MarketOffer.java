package com.agregio.offer.businesslogic.models;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class MarketOffer {
	private final UUID id;
	private final MarketType marketType;
	private final List<MarketOfferPriceBlock> priceBlocks;

	public MarketOffer(Builder builder) {
		this.id = builder.id;
		this.marketType = builder.marketType;
		this.priceBlocks = builder.priceBlocks;
	}

	public UUID getId() {
		return id;
	}

	public MarketType getMarketType() {
		return marketType;
	}

	public List<MarketOfferPriceBlock> getPriceBlocks() {
		return priceBlocks;
	}

	public static class Builder {
		private UUID id;
		private MarketType marketType;
		private List<MarketOfferPriceBlock> priceBlocks;

		public Builder() {
			this.id = UUID.randomUUID();
		}

		public Builder withId(UUID id) {
			this.id = id;
			return this;
		}

		public Builder withMarketType(MarketType marketType) {
			this.marketType = marketType;
			return this;
		}

		public Builder withPriceBlocks(List<MarketOfferPriceBlock> priceBlocks) {
			this.priceBlocks = priceBlocks;
			return this;
		}

		public MarketOffer build() {
			return new MarketOffer(this);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		MarketOffer that = (MarketOffer) o;
		return Objects.equals(id, that.id) && marketType == that.marketType && Objects.equals(priceBlocks, that.priceBlocks);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, marketType, priceBlocks);
	}

	@Override
	public String toString() {
		return "MarketOffer{" + "id=" + id + ", marketType=" + marketType + ", priceBlocks=" + priceBlocks + '}';
	}
}