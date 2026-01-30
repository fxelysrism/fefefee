package com.insanecraft.core.enchant;

import com.insanecraft.core.item.ItemStack;

import java.util.List;
import java.util.Objects;

/**
 * Defines the cost to apply a custom enchantment.
 */
public final class EnhancementRecipe {
    private final String enchantId;
    private final int requiredXpLevels;
    private final List<ItemStack> materials;

    public EnhancementRecipe(String enchantId, int requiredXpLevels, List<ItemStack> materials) {
        this.enchantId = Objects.requireNonNull(enchantId, "enchantId");
        this.requiredXpLevels = Math.max(0, requiredXpLevels);
        this.materials = List.copyOf(Objects.requireNonNull(materials, "materials"));
    }

    public String getEnchantId() {
        return enchantId;
    }

    public int getRequiredXpLevels() {
        return requiredXpLevels;
    }

    public List<ItemStack> getMaterials() {
        return materials;
    }
}
