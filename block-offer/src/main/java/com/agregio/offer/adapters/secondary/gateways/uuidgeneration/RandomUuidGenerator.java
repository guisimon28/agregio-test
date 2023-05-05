package com.agregio.offer.adapters.secondary.gateways.uuidgeneration;

import java.util.UUID;

import com.agregio.offer.businesslogic.gateways.uuidgeneration.UuidGenerator;

public class RandomUuidGenerator implements UuidGenerator {
    @Override
    public UUID generate() {
        return UUID.randomUUID();
    }
}
