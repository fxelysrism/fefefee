package com.insanecraft.core.ui;

import com.insanecraft.core.harvester.VoidHarvester;
import com.insanecraft.core.harvester.VoidHarvesterState;
import com.insanecraft.core.harvester.VoidHarvesterTier;
import com.insanecraft.core.harvester.VoidHarvesterUpgrade;
import com.insanecraft.core.item.ItemStack;
import com.insanecraft.core.player.PlayerInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * UI helper for the Void Harvester block.
 */
public class VoidHarvesterUI {
    private final VoidHarvester harvester;

    public VoidHarvesterUI(VoidHarvester harvester) {
        this.harvester = harvester;
    }

    public UiPanel render(VoidHarvesterState state, PlayerInventory inventory) {
        List<UiLine> lines = new ArrayList<>();
        lines.add(new UiLine("Tier", state.getTier().name()));
        lines.add(new UiLine("Fuel Stored", String.valueOf(state.getStoredFuel())));
        lines.add(new UiLine("Fuel Cost", String.valueOf(state.getTier().getFuelCost())));
        lines.add(new UiLine("Output Per Cycle", String.valueOf(state.getTier().getOutputPerCycle())));
        lines.add(new UiLine("Void Fuel", String.valueOf(inventory.getCount("void_fuel"))));
        VoidHarvesterUpgrade upgrade = findUpgrade(state.getTier());
        if (upgrade != null) {
            lines.add(new UiLine("Next Upgrade", upgrade.getToTier().name()));
            lines.add(new UiLine("Upgrade Cost", upgrade.getMaterials().toString()));
        }
        return new UiPanel(
            "Void Harvester",
            "Consume void fuel to harvest fragments from the void.",
            lines,
            List.of("Add Fuel", "Harvest", "Upgrade")
        );
    }

    public UiActionResult<VoidHarvesterState> addFuel(ItemStack fuelStack, PlayerInventory inventory, VoidHarvesterState state) {
        boolean added = harvester.addFuel(fuelStack, inventory, state);
        if (!added) {
            return UiActionResult.failure("Unable to add fuel.");
        }
        return UiActionResult.success("Fuel added to harvester.", state);
    }

    public UiActionResult<ItemStack> harvest(PlayerInventory inventory, VoidHarvesterState state) {
        Optional<ItemStack> result = harvester.harvest(state);
        if (result.isEmpty()) {
            return UiActionResult.failure("Not enough fuel to harvest.");
        }
        inventory.add(result.get());
        return UiActionResult.success("Void fragments harvested.", result.get());
    }

    public UiActionResult<VoidHarvesterTier> upgrade(
        VoidHarvesterState state,
        List<ItemStack> materials,
        PlayerInventory inventory
    ) {
        Optional<VoidHarvesterTier> result = harvester.upgrade(state, materials, inventory);
        if (result.isEmpty()) {
            return UiActionResult.failure("Upgrade requirements not met.");
        }
        return UiActionResult.success("Void harvester upgraded.", result.get());
    }

    private VoidHarvesterUpgrade findUpgrade(VoidHarvesterTier tier) {
        for (VoidHarvesterUpgrade upgrade : harvester.getUpgrades()) {
            if (upgrade.getFromTier() == tier) {
                return upgrade;
            }
        }
        return null;
    }
}
