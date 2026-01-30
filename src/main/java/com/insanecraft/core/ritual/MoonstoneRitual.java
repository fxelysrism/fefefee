package com.insanecraft.core.ritual;

import com.insanecraft.core.item.ItemStack;
import com.insanecraft.core.player.PlayerInventory;

import java.util.List;

/**
 * Unique system: Moonstone ritual grants a temporary buff token.
 */
public class MoonstoneRitual {
    public ItemStack perform(List<ItemStack> requiredMaterials, PlayerInventory inventory) {
        if (!inventory.consumeAll(requiredMaterials)) {
            return null;
        }
        return new ItemStack("moonstone_blessing", 1);
    }
}
