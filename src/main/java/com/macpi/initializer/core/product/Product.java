package com.macpi.initializer.core.product;

import lombok.Value;

import java.time.OffsetDateTime;
import java.util.UUID;

@Value
class Product {
    private final UUID id;
    private final String name;
    private final int articleId;
    private final OffsetDateTime lastUpdated;
}
