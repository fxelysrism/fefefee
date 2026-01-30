package com.insanecraft.core.ui;

import com.insanecraft.core.enchant.EnchantDefinition;
import com.insanecraft.core.enchant.OverenchantmentTable;

import java.util.ArrayList;
import java.util.List;

/**
 * UI helper for the Overenchantment Table.
 */
public class OverenchantmentTableUI {
    private final OverenchantmentTable table;

    public OverenchantmentTableUI(OverenchantmentTable table) {
        this.table = table;
    }

    public UiPanel render(boolean allowExclusive) {
        List<UiLine> lines = new ArrayList<>();
        for (EnchantDefinition definition : table.getSupportedEnchants()) {
            String label = definition.getId() + " (max " + definition.getMaxLevel() + ")";
            String value = definition.isExclusive() ? "Exclusive" : "Standard";
            lines.add(new UiLine(label, value));
        }
        lines.add(new UiLine("Exclusive Allowed", allowExclusive ? "Yes" : "No"));
        return new UiPanel(
            "Overenchantment Table",
            "Choose powerful enchantments beyond vanilla limits.",
            lines,
            List.of("Select Enchantment")
        );
    }
}
