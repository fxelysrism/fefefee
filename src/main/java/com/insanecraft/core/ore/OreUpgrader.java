package com.insanecraft.core.ore;

import com.insanecraft.core.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Server-side logic placeholder for the Ore Upgrader block.
 */
public class OreUpgrader {
    private final List<OreUpgradeRecipe> recipes = new ArrayList<>();

    public OreUpgrader() {
        recipes.add(new OreUpgradeRecipe(
            List.of(new ItemStack("diamond_block_wall", 1)),
            new ItemStack("diamond_block", 1)
        ));
        recipes.add(new OreUpgradeRecipe(
            List.of(new ItemStack("iron_geode", 1)),
            new ItemStack("iron_block", 4)
        ));
        recipes.add(new OreUpgradeRecipe(
            List.of(
                new ItemStack("crying_obsidian", 1),
                new ItemStack("diamond_block", 8)
            ),
            new ItemStack("overenchantment_table", 1)
        ));
    }

    public void register() {
        // Hook block registration for your mod loader.
    }

    public Optional<OreUpgradeRecipe> findRecipe(List<ItemStack> inputs) {
        for (OreUpgradeRecipe recipe : recipes) {
            if (matches(recipe.getInputs(), inputs)) {
                return Optional.of(recipe);
            }
        }
        return Optional.empty();
    }

    private boolean matches(List<ItemStack> expected, List<ItemStack> actual) {
        if (expected.size() != actual.size()) {
            return false;
        }
        for (int i = 0; i < expected.size(); i++) {
            ItemStack expectedStack = expected.get(i);
            ItemStack actualStack = actual.get(i);
            if (!expectedStack.getItemId().equals(actualStack.getItemId())) {
                return false;
            }
            if (actualStack.getCount() < expectedStack.getCount()) {
                return false;
            }
        }
        return true;
    }
}
