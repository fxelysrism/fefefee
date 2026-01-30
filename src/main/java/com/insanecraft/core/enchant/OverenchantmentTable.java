package com.insanecraft.core.enchant;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Placeholder logic for the Overenchantment Table.
 */
public class OverenchantmentTable {
    private final List<EnchantDefinition> supportedEnchants = new ArrayList<>();

    public OverenchantmentTable() {
        supportedEnchants.add(new EnchantDefinition("fortune", 15, false));
        supportedEnchants.add(new EnchantDefinition("looting", 10, false));
        supportedEnchants.add(new EnchantDefinition("vein_miner", 1, true));
        supportedEnchants.add(new EnchantDefinition("lifesteal", 5, true));
        supportedEnchants.add(new EnchantDefinition("auto_smelt", 1, true));
    }

    public void register() {
        // Hook block and menu registration for your mod loader.
    }

    public List<EnchantDefinition> getSupportedEnchants() {
        return List.copyOf(supportedEnchants);
    }

    public Optional<EnchantDefinition> findDefinition(String id) {
        for (EnchantDefinition definition : supportedEnchants) {
            if (definition.getId().equals(id)) {
                return Optional.of(definition);
            }
        }
        return Optional.empty();
    }

    public boolean canApply(String id, int level, boolean allowExclusive) {
        Optional<EnchantDefinition> definition = findDefinition(id);
        if (definition.isEmpty()) {
            return false;
        }
        EnchantDefinition value = definition.get();
        if (!allowExclusive && value.isExclusive()) {
            return false;
        }
        return level > 0 && level <= value.getMaxLevel();
    }
}
