package com.insanecraft.core.service;

import com.insanecraft.core.item.ItemStack;
import com.insanecraft.core.player.PlayerInventory;
import com.insanecraft.core.recipe.CraftingRecipe;
import com.insanecraft.core.recipe.RecipeRegistry;

import java.util.List;
import java.util.Optional;

/**
 * Applies crafting recipes against a player's inventory.
 */
public class CraftingService {
    private final RecipeRegistry recipeRegistry;

    public CraftingService(RecipeRegistry recipeRegistry) {
        this.recipeRegistry = recipeRegistry;
    }

    public Optional<ItemStack> craft(List<ItemStack> inputs, PlayerInventory inventory, int playerXpLevels) {
        Optional<CraftingRecipe> recipe = recipeRegistry.findMatch(inputs);
        if (recipe.isEmpty()) {
            return Optional.empty();
        }
        CraftingRecipe value = recipe.get();
        if (playerXpLevels < value.getRequiredXpLevels()) {
            return Optional.empty();
        }
        if (!inventory.consumeAll(inputs)) {
            return Optional.empty();
        }
        inventory.add(value.getOutput());
        return Optional.of(value.getOutput());
    }
}
