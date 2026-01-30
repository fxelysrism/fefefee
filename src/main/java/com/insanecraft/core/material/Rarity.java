package com.insanecraft.core.material;

/**
 * Rarity tiers for new materials like rubies.
 */
public enum Rarity {
    COMMON("diamond-level"),
    RARE("+speed"),
    EPIC("+strength"),
    LEGENDARY("special ability");

    private final String bonus;

    Rarity(String bonus) {
        this.bonus = bonus;
    }

    public String getBonus() {
        return bonus;
    }
}
