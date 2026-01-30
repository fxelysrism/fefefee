package com.insanecraft.core.forge;

import com.insanecraft.core.item.ItemStack;
import com.insanecraft.core.material.GearAttributeService;
import com.insanecraft.core.material.GearAttributes;
import com.insanecraft.core.material.RubyTier;
import com.insanecraft.core.player.PlayerInventory;

import java.util.List;
import java.util.Optional;

/**
 * Unique system: Ruby Forge upgrades gear to higher ruby tiers.
 */
public class RubyForge {
    private final GearAttributeService gearAttributeService;

    public RubyForge(GearAttributeService gearAttributeService) {
        this.gearAttributeService = gearAttributeService;
    }

    public Optional<GearAttributes> upgrade(
        RubyTier tier,
        double baseDamage,
        double baseSpeed,
        List<ItemStack> requiredMaterials,
        PlayerInventory inventory
    ) {
        if (!inventory.consumeAll(requiredMaterials)) {
            return Optional.empty();
        }
        return Optional.of(gearAttributeService.buildRubyAttributes(tier, baseDamage, baseSpeed));
    }
}
