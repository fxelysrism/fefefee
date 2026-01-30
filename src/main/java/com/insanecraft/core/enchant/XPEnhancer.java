package com.insanecraft.core.enchant;

import com.insanecraft.core.item.ItemStack;
import com.insanecraft.core.player.PlayerInventory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Handles XP enhancer costs for applying custom enchants.
 */
public class XPEnhancer {
    private final OverenchantmentTable table;
    private final List<EnhancementRecipe> recipes;

    public XPEnhancer(OverenchantmentTable table, List<EnhancementRecipe> recipes) {
        this.table = table;
        this.recipes = List.copyOf(recipes);
    }

    public Optional<EnhancementRecipe> findRecipe(String enchantId) {
        for (EnhancementRecipe recipe : recipes) {
            if (recipe.getEnchantId().equals(enchantId)) {
                return Optional.of(recipe);
            }
        }
        return Optional.empty();
    }

    public boolean canApply(String enchantId, int level, int playerXpLevels, List<ItemStack> materials) {
        if (!table.canApply(enchantId, level, true)) {
            return false;
        }
        Optional<EnhancementRecipe> recipe = findRecipe(enchantId);
        if (recipe.isEmpty()) {
            return false;
        }
        EnhancementRecipe value = recipe.get();
        if (playerXpLevels < value.getRequiredXpLevels()) {
            return false;
        }
        return hasMaterials(value.getMaterials(), materials);
    }

    public Optional<EnchantedItem> apply(
        String baseItemId,
        String enchantId,
        int level,
        int playerXpLevels,
        List<ItemStack> materials
    ) {
        if (!canApply(enchantId, level, playerXpLevels, materials)) {
            return Optional.empty();
        }
        EnchantedItem item = new EnchantedItem(baseItemId, Map.of(enchantId, level));
        return Optional.of(item);
    }

    public Optional<XpEnhancementResult> applyWithInventory(
        String baseItemId,
        String enchantId,
        int level,
        int playerXpLevels,
        List<ItemStack> materials,
        PlayerInventory inventory
    ) {
        Optional<EnhancementRecipe> recipe = findRecipe(enchantId);
        if (recipe.isEmpty()) {
            return Optional.empty();
        }
        EnhancementRecipe value = recipe.get();
        if (!table.canApply(enchantId, level, true)) {
            return Optional.empty();
        }
        if (playerXpLevels < value.getRequiredXpLevels()) {
            return Optional.empty();
        }
        if (!hasMaterials(value.getMaterials(), materials)) {
            return Optional.empty();
        }
        if (!inventory.consumeAll(value.getMaterials())) {
            return Optional.empty();
        }
        EnchantedItem item = new EnchantedItem(baseItemId, Map.of(enchantId, level));
        int remainingXp = playerXpLevels - value.getRequiredXpLevels();
        return Optional.of(new XpEnhancementResult(item, remainingXp));
    }

    private boolean hasMaterials(List<ItemStack> required, List<ItemStack> provided) {
        Map<String, Integer> providedCounts = new HashMap<>();
        for (ItemStack stack : provided) {
            providedCounts.merge(stack.getItemId(), stack.getCount(), Integer::sum);
        }
        for (ItemStack requiredStack : required) {
            int available = providedCounts.getOrDefault(requiredStack.getItemId(), 0);
            if (available < requiredStack.getCount()) {
                return false;
            }
        }
        return true;
    }
}
