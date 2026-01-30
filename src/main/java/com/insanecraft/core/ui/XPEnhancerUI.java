package com.insanecraft.core.ui;

import com.insanecraft.core.enchant.EnhancementRecipe;
import com.insanecraft.core.enchant.EnchantedItem;
import com.insanecraft.core.enchant.XPEnhancer;
import com.insanecraft.core.enchant.XpEnhancementResult;
import com.insanecraft.core.item.ItemStack;
import com.insanecraft.core.player.PlayerInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * UI helper for the XP Enhancer custom enchantment flow.
 */
public class XPEnhancerUI {
    private final XPEnhancer xpEnhancer;

    public XPEnhancerUI(XPEnhancer xpEnhancer) {
        this.xpEnhancer = xpEnhancer;
    }

    public UiPanel render(String enchantId, int level, int playerXpLevels, List<ItemStack> materials, PlayerInventory inventory) {
        Optional<EnhancementRecipe> recipe = xpEnhancer.findRecipe(enchantId);
        List<UiLine> lines = new ArrayList<>();
        lines.add(new UiLine("Enchant", enchantId));
        lines.add(new UiLine("Level", String.valueOf(level)));
        lines.add(new UiLine("Player XP", String.valueOf(playerXpLevels)));
        lines.add(new UiLine("Materials", materials.toString()));
        if (recipe.isPresent()) {
            EnhancementRecipe value = recipe.get();
            lines.add(new UiLine("XP Required", String.valueOf(value.getRequiredXpLevels())));
            lines.add(new UiLine("Required Materials", value.getMaterials().toString()));
            lines.add(new UiLine("Inventory Ready", hasAll(inventory, value.getMaterials()) ? "Yes" : "No"));
        } else {
            lines.add(new UiLine("XP Required", "Unknown"));
        }
        boolean canApply = xpEnhancer.canApply(enchantId, level, playerXpLevels, materials);
        lines.add(new UiLine("Ready To Apply", canApply ? "Yes" : "No"));
        return new UiPanel(
            "XP Enhancer",
            "Spend XP and rare items to apply custom overenchantments.",
            lines,
            List.of("Apply Enchant")
        );
    }

    public UiActionResult<EnchantedItem> apply(
        String baseItemId,
        String enchantId,
        int level,
        int playerXpLevels,
        List<ItemStack> materials,
        PlayerInventory inventory
    ) {
        Optional<XpEnhancementResult> result = xpEnhancer.applyWithInventory(
            baseItemId,
            enchantId,
            level,
            playerXpLevels,
            materials,
            inventory
        );
        if (result.isEmpty()) {
            return UiActionResult.failure("Requirements not met for enhancement.");
        }
        XpEnhancementResult value = result.get();
        int xpDelta = value.remainingXpLevels() - playerXpLevels;
        return UiActionResult.successWithXp("Enhancement applied.", value.item(), xpDelta);
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
