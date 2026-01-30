package com.insanecraft.core.totem;

import java.util.Objects;

/**
 * Represents a charged storm totem ready to unleash a weather event.
 */
public final class StormTotemCharge {
    private final StormTier tier;
    private final int durationSeconds;
    private final double lightningChance;

    public StormTotemCharge(StormTier tier, int durationSeconds, double lightningChance) {
        this.tier = Objects.requireNonNull(tier, "tier");
        this.durationSeconds = durationSeconds;
        this.lightningChance = lightningChance;
    }

    public StormTier getTier() {
        return tier;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public double getLightningChance() {
        return lightningChance;
    }

    @Override
    public String toString() {
        return tier.name() + " storm (" + durationSeconds + "s, lightning " + lightningChance + ")";
    }
}
