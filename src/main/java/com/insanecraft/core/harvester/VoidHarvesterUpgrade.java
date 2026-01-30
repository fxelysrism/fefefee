package com.insanecraft.core.harvester;

import com.insanecraft.core.item.ItemStack;

import java.util.List;
import java.util.Objects;

/**
 * Defines the materials needed to upgrade a Void Harvester tier.
 */
public final class VoidHarvesterUpgrade {
    private final VoidHarvesterTier fromTier;
    private final VoidHarvesterTier toTier;
    private final List<ItemStack> materials;

    public VoidHarvesterUpgrade(VoidHarvesterTier fromTier, VoidHarvesterTier toTier, List<ItemStack> materials) {
        if (materials == null || materials.isEmpty()) {
            throw new IllegalArgumentException("materials cannot be empty");
        }
        this.fromTier = Objects.requireNonNull(fromTier, "fromTier");
        this.toTier = Objects.requireNonNull(toTier, "toTier");
        this.materials = List.copyOf(materials);
    }

    public VoidHarvesterTier getFromTier() {
        return fromTier;
    }

    public VoidHarvesterTier getToTier() {
        return toTier;
    }

    public List<ItemStack> getMaterials() {
        return materials;
    }
}
