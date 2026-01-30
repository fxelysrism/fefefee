package com.insanecraft.core.ui;

import com.insanecraft.core.item.ItemStack;
import com.insanecraft.core.ore.OreUpgradeRecipe;
import com.insanecraft.core.ore.OreUpgrader;
import com.insanecraft.core.player.PlayerInventory;
import com.insanecraft.core.service.OreUpgradeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * UI helper for the Ore Upgrader block.
 */
public class OreUpgraderUI {
    private final OreUpgrader oreUpgrader;
    private final OreUpgradeService oreUpgradeService;

    public OreUpgraderUI(OreUpgrader oreUpgrader) {
        this.oreUpgrader = oreUpgrader;
        this.oreUpgradeService = new OreUpgradeService(oreUpgrader);
    }

    public UiPanel render(List<ItemStack> inputs, PlayerInventory inventory) {
        Optional<OreUpgradeRecipe> recipe = oreUpgrader.findRecipe(inputs);
        List<UiLine> lines = new ArrayList<>();
        lines.add(new UiLine("Inputs", inputs.toString()));
        lines.add(new UiLine("Upgrade Available", recipe.isPresent() ? "Yes" : "No"));
        lines.add(new UiLine("Inventory Ready", hasAll(inventory, inputs) ? "Yes" : "No"));
        recipe.ifPresent(value -> lines.add(new UiLine("Output", value.getOutput().toString())));
        return new UiPanel(
            "Ore Upgrader",
            "Insert blocks to convert them into higher-tier outputs.",
            lines,
            List.of("Upgrade")
        );
    }

    public UiActionResult<ItemStack> upgrade(List<ItemStack> inputs, PlayerInventory inventory) {
        Optional<ItemStack> result = oreUpgradeService.upgrade(inputs, inventory);
        if (result.isEmpty()) {
            return UiActionResult.failure("Missing recipe or materials for upgrade.");
        }
        return UiActionResult.success("Upgrade complete.", result.get());
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
