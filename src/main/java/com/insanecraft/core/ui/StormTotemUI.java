package com.insanecraft.core.ui;

import com.insanecraft.core.item.ItemStack;
import com.insanecraft.core.player.PlayerInventory;
import com.insanecraft.core.totem.StormTotem;
import com.insanecraft.core.totem.StormTotemCharge;
import com.insanecraft.core.totem.StormTotemRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * UI helper for Storm Totem charging.
 */
public class StormTotemUI {
    private final StormTotem stormTotem;

    public StormTotemUI(StormTotem stormTotem) {
        this.stormTotem = stormTotem;
    }

    public UiPanel render(List<ItemStack> reagents, PlayerInventory inventory) {
        List<UiLine> lines = new ArrayList<>();
        lines.add(new UiLine("Reagents", reagents.toString()));
        lines.add(new UiLine("Inventory Ready", hasAll(inventory, reagents) ? "Yes" : "No"));
        Optional<StormTotemRecipe> match = findMatch(reagents);
        match.ifPresent(recipe -> lines.add(new UiLine("Storm Tier", recipe.getTier().name())));
        return new UiPanel(
            "Storm Totem",
            "Charge totems to unleash storm events.",
            lines,
            List.of("Charge Totem")
        );
    }

    public UiActionResult<StormTotemCharge> charge(List<ItemStack> reagents, PlayerInventory inventory) {
        Optional<StormTotemCharge> result = stormTotem.charge(reagents, inventory);
        if (result.isEmpty()) {
            return UiActionResult.failure("Unable to charge storm totem.");
        }
        return UiActionResult.success("Storm totem charged.", result.get());
    }

    private Optional<StormTotemRecipe> findMatch(List<ItemStack> reagents) {
        for (StormTotemRecipe recipe : stormTotem.getRecipes()) {
            if (recipe.matches(reagents)) {
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
