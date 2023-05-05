package com.agregio.offer.businesslogic.models;

public enum MarketType {
	PRIMARY("Réserve Primaire"),
	SECONDARY("Réserve Secondaire"),
	FAST("Réserve rapide");

	private final String description;

	MarketType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
