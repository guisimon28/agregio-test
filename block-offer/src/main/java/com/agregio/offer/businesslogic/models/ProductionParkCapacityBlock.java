package com.agregio.offer.businesslogic.models;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class ProductionParkCapacityBlock {
	private final HourRange hourRange;
	private final double capacityInMwh;

	public static final Integer BLOCK_HOUR = 3;

	public ProductionParkCapacityBlock(LocalTime hour, double capacityInMwh) {
		LocalTime startHour = hour.truncatedTo(ChronoUnit.HOURS).withHour((hour.getHour() / BLOCK_HOUR) * BLOCK_HOUR);
		this.hourRange = new HourRange(startHour, startHour.plusHours(BLOCK_HOUR));
		this.capacityInMwh = capacityInMwh;
	}

	public HourRange getHourRange() {
		return hourRange;
	}

	public double getCapacityInMwh() {
		return capacityInMwh;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ProductionParkCapacityBlock that = (ProductionParkCapacityBlock) o;
		return Double.compare(that.capacityInMwh, capacityInMwh) == 0 && Objects.equals(hourRange, that.hourRange);
	}

	@Override
	public int hashCode() {
		return Objects.hash(hourRange, capacityInMwh);
	}

	@Override
	public String toString() {
		return "CapacityBlock{" + "hourRange=" + hourRange + ", capacityInMwh=" + capacityInMwh + '}';
	}
}