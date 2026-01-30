package com.insanecraft.core.totem;

/**
 * Represents the strength of a storm summoned by a Storm Totem.
 */
public enum StormTier {
    BREEZE(45, 0.05),
    TEMPEST(90, 0.15),
    CATACLYSM(160, 0.3);

    private final int durationSeconds;
    private final double lightningChance;

    StormTier(int durationSeconds, double lightningChance) {
        this.durationSeconds = durationSeconds;
        this.lightningChance = lightningChance;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public double getLightningChance() {
        return lightningChance;
    }
}
