package com.insanecraft.core.enchant;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents an item with applied custom enchantments.
 */
public final class EnchantedItem {
    private final String baseItemId;
    private final Map<String, Integer> enchants;

    public EnchantedItem(String baseItemId, Map<String, Integer> enchants) {
        this.baseItemId = Objects.requireNonNull(baseItemId, "baseItemId");
        this.enchants = new LinkedHashMap<>(Objects.requireNonNull(enchants, "enchants"));
    }

    public String getBaseItemId() {
        return baseItemId;
    }

    public Map<String, Integer> getEnchants() {
        return Map.copyOf(enchants);
    }

    public EnchantedItem withEnchant(String id, int level) {
        Map<String, Integer> updated = new LinkedHashMap<>(enchants);
        updated.put(id, level);
        return new EnchantedItem(baseItemId, updated);
    }

    @Override
    public String toString() {
        return baseItemId + " " + enchants;
    }
}
