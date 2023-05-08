package com.agregio.offer.businesslogic.models;

import java.time.LocalTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

public class HourRange {

	@JsonFormat(pattern = "HH:mm:ss")
	private final LocalTime from;

	@JsonFormat(pattern = "HH:mm:ss")
	private final LocalTime to;

	public HourRange(LocalTime from, LocalTime to) {
		this.from = from;
		this.to = to;
	}

	public LocalTime getFrom() {
		return from;
	}

	public LocalTime getTo() {
		return to;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		HourRange hourRange = (HourRange) o;
		return Objects.equals(from, hourRange.from) && Objects.equals(to, hourRange.to);
	}

	@Override
	public int hashCode() {
		return Objects.hash(from, to);
	}

	@Override
	public String toString() {
		return "HourRange{" + "from=" + from + ", to=" + to + '}';
	}
}
