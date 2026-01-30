package com.insanecraft.core.harvester;

import com.insanecraft.core.item.ItemStack;
import com.insanecraft.core.player.PlayerInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Void Harvester logic for fuel-powered ore generation.
 */
public final class VoidHarvester {
    private static final String FUEL_ITEM = "void_fuel";
    private static final String OUTPUT_ITEM = "void_fragment";

    private final List<VoidHarvesterUpgrade> upgrades = new ArrayList<>();

    public VoidHarvester() {
        upgrades.add(new VoidHarvesterUpgrade(
            VoidHarvesterTier.BASIC,
            VoidHarvesterTier.ADVANCED,
            List.of(new ItemStack("void_fragment", 8), new ItemStack("ruby_gem", 2))
        ));
        upgrades.add(new VoidHarvesterUpgrade(
            VoidHarvesterTier.ADVANCED,
            VoidHarvesterTier.ELITE,
            List.of(new ItemStack("void_fragment", 16), new ItemStack("moonstone", 1))
        ));
    }

    public List<VoidHarvesterUpgrade> getUpgrades() {
        return List.copyOf(upgrades);
    }

    public boolean addFuel(ItemStack fuelStack, PlayerInventory inventory, VoidHarvesterState state) {
        if (!FUEL_ITEM.equals(fuelStack.getItemId())) {
            return false;
        }
        if (!inventory.consumeAll(List.of(fuelStack))) {
            return false;
        }
        state.addFuel(fuelStack.getCount());
        return true;
    }

    public Optional<ItemStack> harvest(VoidHarvesterState state) {
        VoidHarvesterTier tier = state.getTier();
        if (!state.consumeFuel(tier.getFuelCost())) {
            return Optional.empty();
        }
        return Optional.of(new ItemStack(OUTPUT_ITEM, tier.getOutputPerCycle()));
    }

    public Optional<VoidHarvesterTier> upgrade(VoidHarvesterState state, List<ItemStack> materials, PlayerInventory inventory) {
        for (VoidHarvesterUpgrade upgrade : upgrades) {
            if (upgrade.getFromTier() != state.getTier()) {
                continue;
            }
            if (!matches(upgrade.getMaterials(), materials)) {
                continue;
            }
            if (!inventory.consumeAll(upgrade.getMaterials())) {
                return Optional.empty();
            }
            state.setTier(upgrade.getToTier());
            return Optional.of(upgrade.getToTier());
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
