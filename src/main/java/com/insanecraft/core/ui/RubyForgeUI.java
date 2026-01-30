package com.insanecraft.core.ui;

import com.insanecraft.core.forge.RubyForge;
import com.insanecraft.core.item.ItemStack;
import com.insanecraft.core.material.GearAttributes;
import com.insanecraft.core.material.RubyTier;
import com.insanecraft.core.player.PlayerInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * UI helper for the Ruby Forge upgrade system.
 */
public class RubyForgeUI {
    private final RubyForge rubyForge;

    public RubyForgeUI(RubyForge rubyForge) {
        this.rubyForge = rubyForge;
    }

    public UiPanel render(
        RubyTier tier,
        double baseDamage,
        double baseSpeed,
        List<ItemStack> requiredMaterials,
        PlayerInventory inventory
    ) {
        List<UiLine> lines = new ArrayList<>();
        lines.add(new UiLine("Tier", tier.name()));
        lines.add(new UiLine("Base Damage", String.valueOf(baseDamage)));
        lines.add(new UiLine("Base Speed", String.valueOf(baseSpeed)));
        lines.add(new UiLine("Required Materials", requiredMaterials.toString()));
        lines.add(new UiLine("Inventory Ready", hasAll(inventory, requiredMaterials) ? "Yes" : "No"));
        return new UiPanel(
            "Ruby Forge",
            "Upgrade ruby gear tiers with rare materials.",
            lines,
            List.of("Upgrade Gear")
        );
    }

    public UiActionResult<GearAttributes> upgrade(
        RubyTier tier,
        double baseDamage,
        double baseSpeed,
        List<ItemStack> requiredMaterials,
        PlayerInventory inventory
    ) {
        Optional<GearAttributes> result = rubyForge.upgrade(tier, baseDamage, baseSpeed, requiredMaterials, inventory);
        if (result.isEmpty()) {
            return UiActionResult.failure("Missing materials for ruby forge upgrade.");
        }
        return UiActionResult.success("Ruby gear upgraded.", result.get());
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
