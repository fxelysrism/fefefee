package com.insanecraft.core.totem;

import com.insanecraft.core.item.ItemStack;

import java.util.List;
import java.util.Objects;

/**
 * Defines how to charge a storm totem.
 */
public final class StormTotemRecipe {
    private final StormTier tier;
    private final List<ItemStack> reagents;

    public StormTotemRecipe(StormTier tier, List<ItemStack> reagents) {
        if (reagents == null || reagents.isEmpty()) {
            throw new IllegalArgumentException("reagents cannot be empty");
        }
        this.tier = Objects.requireNonNull(tier, "tier");
        this.reagents = List.copyOf(reagents);
    }

    public StormTier getTier() {
        return tier;
    }

    public List<ItemStack> getReagents() {
        return reagents;
    }

    public boolean matches(List<ItemStack> provided) {
        if (reagents.size() != provided.size()) {
            return false;
        }
        for (int i = 0; i < reagents.size(); i++) {
            ItemStack expected = reagents.get(i);
            ItemStack actual = provided.get(i);
            if (!expected.getItemId().equals(actual.getItemId())) {
                return false;
            }
            if (actual.getCount() < expected.getCount()) {
                return false;
            }
        }
        return true;
    }
}
