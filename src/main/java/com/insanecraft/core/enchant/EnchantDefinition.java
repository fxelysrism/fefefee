package com.insanecraft.core.enchant;

import java.util.Objects;

/**
 * Definition for a custom enchantment and its allowed max level.
 */
public final class EnchantDefinition {
    private final String id;
    private final int maxLevel;
    private final boolean exclusive;

    public EnchantDefinition(String id, int maxLevel, boolean exclusive) {
        if (maxLevel <= 0) {
            throw new IllegalArgumentException("maxLevel must be positive");
        }
        this.id = Objects.requireNonNull(id, "id");
        this.maxLevel = maxLevel;
        this.exclusive = exclusive;
    }

    public String getId() {
        return id;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    @Override
    public String toString() {
        return id + " (max " + maxLevel + ")" + (exclusive ? " [exclusive]" : "");
    }
}
