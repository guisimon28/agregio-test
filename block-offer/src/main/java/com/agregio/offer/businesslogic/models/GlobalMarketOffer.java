package com.agregio.offer.businesslogic.models;

import java.util.List;
import java.util.Objects;

public class GlobalMarketOffer {
	private final MarketType type;
	private final List<GlobalMarketOfferPriceBlock> priceBlocks;

	public GlobalMarketOffer(Builder builder) {
		this.type = builder.type;
		this.priceBlocks = builder.priceBlocks;
	}

	public MarketType getType() {
		return type;
	}

	public List<GlobalMarketOfferPriceBlock> getPriceBlocks() {
		return priceBlocks;
	}

	public static class Builder {

		private MarketType type;

		private List<GlobalMarketOfferPriceBlock> priceBlocks;

		public Builder withType(MarketType type) {
			this.type = type;
			return this;
		}

		public Builder withPriceBlocks(List<GlobalMarketOfferPriceBlock> priceBlocks) {
			this.priceBlocks = priceBlocks;
			return this;
		}

		public GlobalMarketOffer build() {
			return new GlobalMarketOffer(this);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		GlobalMarketOffer that = (GlobalMarketOffer) o;
		return type == that.type && Objects.equals(priceBlocks, that.priceBlocks);
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, priceBlocks);
	}

	@Override
	public String toString() {
		return "GlobalMarketOffer{" + "type=" + type + ", priceBlocks=" + priceBlocks + '}';
	}
}
