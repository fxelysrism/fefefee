package com.insanecraft.core.harvester;

/**
 * Represents the tier of a Void Harvester.
 */
public enum VoidHarvesterTier {
    BASIC(1, 10, 30),
    ADVANCED(2, 18, 24),
    ELITE(4, 30, 18);

    private final int outputPerCycle;
    private final int fuelCost;
    private final int cycleSeconds;

    VoidHarvesterTier(int outputPerCycle, int fuelCost, int cycleSeconds) {
        this.outputPerCycle = outputPerCycle;
        this.fuelCost = fuelCost;
        this.cycleSeconds = cycleSeconds;
    }

    public int getOutputPerCycle() {
        return outputPerCycle;
    }

    public int getFuelCost() {
        return fuelCost;
    }

    public int getCycleSeconds() {
        return cycleSeconds;
    }
}
