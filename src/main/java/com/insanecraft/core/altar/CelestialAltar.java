package com.insanecraft.core.altar;

import com.insanecraft.core.item.ItemStack;
import com.insanecraft.core.player.PlayerInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Ritual altar that trades offerings plus starlight for celestial rewards.
 */
public final class CelestialAltar {
    private final List<AltarRecipe> recipes = new ArrayList<>();

    public CelestialAltar() {
        recipes.add(new AltarRecipe(
            "celestial_core",
            List.of(
                new ItemStack("moonstone", 2),
                new ItemStack("nether_star", 1),
                new ItemStack("diamond_block", 2)
            ),
            new ItemStack("celestial_core", 1),
            40
        ));
        recipes.add(new AltarRecipe(
            "astral_focus",
            List.of(
                new ItemStack("ruby_gem", 3),
                new ItemStack("amethyst_block", 1),
                new ItemStack("echo_shard", 2)
            ),
            new ItemStack("astral_focus", 1),
            25
        ));
    }

    public List<AltarRecipe> getRecipes() {
        return List.copyOf(recipes);
    }

    public Optional<ItemStack> perform(List<ItemStack> offerings, PlayerInventory inventory, StarlightPool starlight) {
        for (AltarRecipe recipe : recipes) {
            if (!recipe.matches(offerings, starlight.getStarlight())) {
                continue;
            }
            if (!inventory.consumeAll(recipe.getOfferings())) {
                return Optional.empty();
            }
            if (!starlight.consume(recipe.getStarlightCost())) {
                return Optional.empty();
            }
            return Optional.of(recipe.getOutput());
        }
        return Optional.empty();
    }
}
