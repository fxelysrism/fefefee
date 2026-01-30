package com.insanecraft.core.ore;

import com.insanecraft.core.item.ItemStack;

import java.util.List;
import java.util.Objects;

/**
 * Represents a recipe that upgrades one or more input items to a single output.
 */
public final class OreUpgradeRecipe {
    private final List<ItemStack> inputs;
    private final ItemStack output;

    public OreUpgradeRecipe(List<ItemStack> inputs, ItemStack output) {
        if (inputs == null || inputs.isEmpty()) {
            throw new IllegalArgumentException("inputs cannot be empty");
        }
        this.inputs = List.copyOf(inputs);
        this.output = Objects.requireNonNull(output, "output");
    }

    public List<ItemStack> getInputs() {
        return inputs;
    }

    public ItemStack getOutput() {
        return output;
    }
}
