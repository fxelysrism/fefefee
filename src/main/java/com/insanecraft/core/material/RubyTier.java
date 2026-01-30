package com.insanecraft.core.material;

/**
 * Gear tiers for ruby equipment.
 */
public enum RubyTier {
    COMMON(1.0, 0.0),
    RARE(1.1, 0.05),
    EPIC(1.2, 0.1),
    LEGENDARY(1.35, 0.15);

    private final double damageMultiplier;
    private final double speedBonus;

    RubyTier(double damageMultiplier, double speedBonus) {
        this.damageMultiplier = damageMultiplier;
        this.speedBonus = speedBonus;
    }

    public double getDamageMultiplier() {
        return damageMultiplier;
    }

    public double getSpeedBonus() {
        return speedBonus;
    }
}
