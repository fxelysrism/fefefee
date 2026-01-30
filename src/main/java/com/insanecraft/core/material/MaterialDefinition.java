package com.insanecraft.core.material;

import java.util.Objects;

/**
 * Defines a new ore or material in InsaneCraft.
 */
public final class MaterialDefinition {
    private final String id;
    private final Rarity rarity;
    private final String description;

    public MaterialDefinition(String id, Rarity rarity, String description) {
        this.id = Objects.requireNonNull(id, "id");
        this.rarity = Objects.requireNonNull(rarity, "rarity");
        this.description = Objects.requireNonNull(description, "description");
    }

    public String getId() {
        return id;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return id + " [" + rarity.name() + "]";
    }
}
