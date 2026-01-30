package com.insanecraft.core.totem;

import com.insanecraft.core.item.ItemStack;
import com.insanecraft.core.player.PlayerInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Storm Totem logic for charging and releasing weather events.
 */
public final class StormTotem {
    private final List<StormTotemRecipe> recipes = new ArrayList<>();

    public StormTotem() {
        recipes.add(new StormTotemRecipe(
            StormTier.BREEZE,
            List.of(new ItemStack("storm_shard", 2), new ItemStack("lapis_block", 1))
        ));
        recipes.add(new StormTotemRecipe(
            StormTier.TEMPEST,
            List.of(new ItemStack("storm_core", 1), new ItemStack("lightning_rod", 1))
        ));
        recipes.add(new StormTotemRecipe(
            StormTier.CATACLYSM,
            List.of(new ItemStack("storm_core", 2), new ItemStack("nether_star", 1))
        ));
    }

    public List<StormTotemRecipe> getRecipes() {
        return List.copyOf(recipes);
    }

    public Optional<StormTotemCharge> charge(List<ItemStack> reagents, PlayerInventory inventory) {
        for (StormTotemRecipe recipe : recipes) {
            if (!recipe.matches(reagents)) {
                continue;
            }
            if (!inventory.consumeAll(recipe.getReagents())) {
                return Optional.empty();
            }
            StormTier tier = recipe.getTier();
            return Optional.of(new StormTotemCharge(
                tier,
                tier.getDurationSeconds(),
                tier.getLightningChance()
            ));
        }
        return Optional.empty();
    }
}
