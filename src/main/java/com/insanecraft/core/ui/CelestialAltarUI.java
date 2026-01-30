package com.insanecraft.core.ui;

import com.insanecraft.core.altar.AltarRecipe;
import com.insanecraft.core.altar.CelestialAltar;
import com.insanecraft.core.altar.StarlightPool;
import com.insanecraft.core.item.ItemStack;
import com.insanecraft.core.player.PlayerInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * UI helper for the Celestial Altar ritual.
 */
public class CelestialAltarUI {
    private final CelestialAltar altar;

    public CelestialAltarUI(CelestialAltar altar) {
        this.altar = altar;
    }

    public UiPanel render(List<ItemStack> offerings, PlayerInventory inventory, StarlightPool starlightPool) {
        List<UiLine> lines = new ArrayList<>();
        lines.add(new UiLine("Starlight", String.valueOf(starlightPool.getStarlight())));
        lines.add(new UiLine("Offerings", offerings.toString()));
        lines.add(new UiLine("Inventory Ready", hasAll(inventory, offerings) ? "Yes" : "No"));
        Optional<AltarRecipe> matching = findMatch(offerings, starlightPool);
        matching.ifPresent(recipe -> lines.add(new UiLine("Output", recipe.getOutput().toString())));
        return new UiPanel(
            "Celestial Altar",
            "Channel starlight and offerings into celestial artifacts.",
            lines,
            List.of("Perform Ritual")
        );
    }

    public UiActionResult<ItemStack> perform(
        List<ItemStack> offerings,
        PlayerInventory inventory,
        StarlightPool starlightPool
    ) {
        Optional<ItemStack> result = altar.perform(offerings, inventory, starlightPool);
        if (result.isEmpty()) {
            return UiActionResult.failure("Offerings or starlight insufficient.");
        }
        inventory.add(result.get());
        return UiActionResult.success("Celestial offering completed.", result.get());
    }

    private Optional<AltarRecipe> findMatch(List<ItemStack> offerings, StarlightPool starlightPool) {
        for (AltarRecipe recipe : altar.getRecipes()) {
            if (recipe.matches(offerings, starlightPool.getStarlight())) {
                return Optional.of(recipe);
            }
        }
        return Optional.empty();
    }

    private boolean hasAll(PlayerInventory inventory, List<ItemStack> stacks) {
        for (ItemStack stack : stacks) {
            if (!inventory.has(stack)) {
                return false;
            }
        }
        return true;
    }
}
