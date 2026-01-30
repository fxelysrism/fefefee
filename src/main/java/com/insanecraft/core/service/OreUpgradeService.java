package com.insanecraft.core.service;

import com.insanecraft.core.item.ItemStack;
import com.insanecraft.core.ore.OreUpgrader;
import com.insanecraft.core.ore.OreUpgradeRecipe;
import com.insanecraft.core.player.PlayerInventory;

import java.util.List;
import java.util.Optional;

/**
 * Applies ore upgrade recipes against a player's inventory.
 */
public class OreUpgradeService {
    private final OreUpgrader oreUpgrader;

    public OreUpgradeService(OreUpgrader oreUpgrader) {
        this.oreUpgrader = oreUpgrader;
    }

    public Optional<ItemStack> upgrade(List<ItemStack> inputs, PlayerInventory inventory) {
        Optional<OreUpgradeRecipe> recipe = oreUpgrader.findRecipe(inputs);
        if (recipe.isEmpty()) {
            return Optional.empty();
        }
        if (!inventory.consumeAll(inputs)) {
            return Optional.empty();
        }
        ItemStack output = recipe.get().getOutput();
        inventory.add(output);
        return Optional.of(output);
    }
}
