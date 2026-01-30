package com.insanecraft.core.recipe;

import com.insanecraft.core.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Central recipe registry for InsaneCraft crafting.
 */
public class RecipeRegistry {
    private final List<CraftingRecipe> recipes = new ArrayList<>();

    public void register(CraftingRecipe recipe) {
        recipes.add(recipe);
    }

    public List<CraftingRecipe> getRecipes() {
        return List.copyOf(recipes);
    }

    public Optional<CraftingRecipe> findMatch(List<ItemStack> inputs) {
        for (CraftingRecipe recipe : recipes) {
            if (recipe.matches(inputs)) {
                return Optional.of(recipe);
            }
        }
        return Optional.empty();
    }
}
