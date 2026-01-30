package com.insanecraft.core.ui;

import com.insanecraft.core.item.ItemStack;
import com.insanecraft.core.player.PlayerInventory;
import com.insanecraft.core.ritual.MoonstoneRitual;

import java.util.ArrayList;
import java.util.List;

/**
 * UI helper for the Moonstone Ritual.
 */
public class MoonstoneRitualUI {
    private final MoonstoneRitual ritual;

    public MoonstoneRitualUI(MoonstoneRitual ritual) {
        this.ritual = ritual;
    }

    public UiPanel render(List<ItemStack> requiredMaterials, PlayerInventory inventory) {
        List<UiLine> lines = new ArrayList<>();
        lines.add(new UiLine("Required Materials", requiredMaterials.toString()));
        lines.add(new UiLine("Inventory Ready", hasAll(inventory, requiredMaterials) ? "Yes" : "No"));
        return new UiPanel(
            "Moonstone Ritual",
            "Consume offerings to receive a Moonstone Blessing.",
            lines,
            List.of("Perform Ritual")
        );
    }

    public UiActionResult<ItemStack> perform(List<ItemStack> requiredMaterials, PlayerInventory inventory) {
        ItemStack blessing = ritual.perform(requiredMaterials, inventory);
        if (blessing == null) {
            return UiActionResult.failure("Missing materials for the ritual.");
        }
        inventory.add(blessing);
        return UiActionResult.success("Moonstone blessing granted.", blessing);
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
