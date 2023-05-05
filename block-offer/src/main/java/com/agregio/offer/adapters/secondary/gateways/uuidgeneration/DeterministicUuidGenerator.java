package com.agregio.offer.adapters.secondary.gateways.uuidgeneration;

import java.util.UUID;

import com.agregio.offer.businesslogic.gateways.uuidgeneration.UuidGenerator;

public class DeterministicUuidGenerator implements UuidGenerator {

    private UUID nextId;

    public UUID generate() {
        return nextId;
    }
    public void setNextId(UUID nextId) {
        this.nextId = nextId;
    }
}
