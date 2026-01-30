package com.insanecraft.core.ui;

import com.insanecraft.core.item.ItemStack;
import com.insanecraft.core.player.PlayerInventory;
import com.insanecraft.core.recipe.CraftingRecipe;
import com.insanecraft.core.recipe.RecipeRegistry;
import com.insanecraft.core.service.CraftingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * UI helper for crafting workflows.
 */
public class CraftingUI {
    private final CraftingService craftingService;
    private final RecipeRegistry recipeRegistry;

    public CraftingUI(CraftingService craftingService, RecipeRegistry recipeRegistry) {
        this.craftingService = craftingService;
        this.recipeRegistry = recipeRegistry;
    }

    public UiPanel render(List<ItemStack> inputs, int playerXpLevels, PlayerInventory inventory) {
        Optional<CraftingRecipe> recipe = recipeRegistry.findMatch(inputs);
        List<UiLine> lines = new ArrayList<>();
        lines.add(new UiLine("Inputs", inputs.toString()));
        lines.add(new UiLine("Player XP", String.valueOf(playerXpLevels)));
        if (recipe.isPresent()) {
            CraftingRecipe value = recipe.get();
            lines.add(new UiLine("Output", value.getOutput().toString()));
            lines.add(new UiLine("XP Required", String.valueOf(value.getRequiredXpLevels())));
            lines.add(new UiLine("Inventory Ready", hasAll(inventory, value.getInputs()) ? "Yes" : "No"));
        } else {
            lines.add(new UiLine("Output", "Unknown"));
            lines.add(new UiLine("XP Required", "Unknown"));
        }
        return new UiPanel(
            "Crafting Bench",
            "Combine materials and XP to craft powerful components.",
            lines,
            List.of("Craft")
        );
    }

    public UiActionResult<ItemStack> craft(List<ItemStack> inputs, PlayerInventory inventory, int playerXpLevels) {
        Optional<CraftingRecipe> recipe = recipeRegistry.findMatch(inputs);
        if (recipe.isEmpty()) {
            return UiActionResult.failure("No matching crafting recipe.");
        }
        CraftingRecipe value = recipe.get();
        if (playerXpLevels < value.getRequiredXpLevels()) {
            return UiActionResult.failure("Not enough XP to craft.");
        }
        Optional<ItemStack> result = craftingService.craft(inputs, inventory, playerXpLevels);
        if (result.isEmpty()) {
            return UiActionResult.failure("Missing materials to craft.");
        }
        return UiActionResult.successWithXp(
            "Crafting complete.",
            result.get(),
            -value.getRequiredXpLevels()
        );
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
