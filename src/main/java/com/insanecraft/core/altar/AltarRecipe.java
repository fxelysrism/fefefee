package com.insanecraft.core.altar;

import com.insanecraft.core.item.ItemStack;

import java.util.List;
import java.util.Objects;

/**
 * Defines a celestial altar recipe that consumes offerings plus starlight.
 */
public final class AltarRecipe {
    private final String id;
    private final List<ItemStack> offerings;
    private final ItemStack output;
    private final int starlightCost;

    public AltarRecipe(String id, List<ItemStack> offerings, ItemStack output, int starlightCost) {
        if (offerings == null || offerings.isEmpty()) {
            throw new IllegalArgumentException("offerings cannot be empty");
        }
        if (starlightCost <= 0) {
            throw new IllegalArgumentException("starlightCost must be positive");
        }
        this.id = Objects.requireNonNull(id, "id");
        this.offerings = List.copyOf(offerings);
        this.output = Objects.requireNonNull(output, "output");
        this.starlightCost = starlightCost;
    }

    public String getId() {
        return id;
    }

    public List<ItemStack> getOfferings() {
        return offerings;
    }

    public ItemStack getOutput() {
        return output;
    }

    public int getStarlightCost() {
        return starlightCost;
    }

    public boolean matches(List<ItemStack> providedOfferings, int availableStarlight) {
        if (availableStarlight < starlightCost) {
            return false;
        }
        if (offerings.size() != providedOfferings.size()) {
            return false;
        }
        for (int i = 0; i < offerings.size(); i++) {
            ItemStack expected = offerings.get(i);
            ItemStack actual = providedOfferings.get(i);
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
