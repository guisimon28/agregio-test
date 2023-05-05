package com.agregio.offer.businesslogic.models;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ProductionPark {
	private final UUID id;
	private final String name;
	private final ProductionParkType type;
	private final List<ProductionParkCapacityBlock> capacity;

	private ProductionPark(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.type = builder.type;
		this.capacity = builder.capacity;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ProductionParkType getType() {
		return type;
	}

	public List<ProductionParkCapacityBlock> getCapacity() {
		return capacity;
	}

	public double getCapacityForRange(HourRange range) {
		return capacity.stream().filter(c -> c.getHourRange().equals(range)).map(ProductionParkCapacityBlock::getCapacityInMwh).findFirst().orElse(0.0);
	}

	public static class Builder {
		private UUID id;
		private String name;
		private ProductionParkType type;
		private List<ProductionParkCapacityBlock> capacity;

		public Builder withId(UUID id) {
			this.id = id;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withType(ProductionParkType type) {
			this.type = type;
			return this;
		}

		public Builder withCapacity(List<ProductionParkCapacityBlock> capacity) {
			this.capacity = capacity;
			return this;
		}

		public ProductionPark build() {
			return new ProductionPark(this);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ProductionPark that = (ProductionPark) o;
		return Objects.equals(id, that.id) && Objects.equals(name, that.name) && type == that.type && Objects.equals(capacity, that.capacity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, type, capacity);
	}

	@Override
	public String toString() {
		return "ProductionPark{" + "id=" + id + ", name='" + name + '\'' + ", type=" + type + ", capacity=" + capacity + '}';
	}
}
