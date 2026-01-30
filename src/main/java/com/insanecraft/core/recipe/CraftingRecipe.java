package com.insanecraft.core.recipe;

import com.insanecraft.core.item.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a shapeless crafting recipe for the mod.
 */
public final class CraftingRecipe {
    private final String id;
    private final List<ItemStack> inputs;
    private final ItemStack output;
    private final int requiredXpLevels;

    public CraftingRecipe(String id, List<ItemStack> inputs, ItemStack output, int requiredXpLevels) {
        if (inputs == null || inputs.isEmpty()) {
            throw new IllegalArgumentException("inputs cannot be empty");
        }
        this.id = Objects.requireNonNull(id, "id");
        this.inputs = List.copyOf(inputs);
        this.output = Objects.requireNonNull(output, "output");
        this.requiredXpLevels = Math.max(0, requiredXpLevels);
    }

    public String getId() {
        return id;
    }

    public List<ItemStack> getInputs() {
        return inputs;
    }

    public ItemStack getOutput() {
        return output;
    }

    public int getRequiredXpLevels() {
        return requiredXpLevels;
    }

    public boolean matches(List<ItemStack> provided) {
        return toCountMap(inputs).equals(toCountMap(provided));
    }

    private Map<String, Integer> toCountMap(List<ItemStack> stacks) {
        Map<String, Integer> counts = new HashMap<>();
        for (ItemStack stack : stacks) {
            counts.merge(stack.getItemId(), stack.getCount(), Integer::sum);
        }
        return counts;
    }
}
