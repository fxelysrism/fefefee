package com.insanecraft.core.harvester;

/**
 * Tracks tier and stored fuel for the Void Harvester block entity.
 */
public final class VoidHarvesterState {
    private VoidHarvesterTier tier;
    private int storedFuel;

    public VoidHarvesterState(VoidHarvesterTier tier, int storedFuel) {
        if (storedFuel < 0) {
            throw new IllegalArgumentException("storedFuel cannot be negative");
        }
        this.tier = tier;
        this.storedFuel = storedFuel;
    }

    public VoidHarvesterTier getTier() {
        return tier;
    }

    public void setTier(VoidHarvesterTier tier) {
        this.tier = tier;
    }

    public int getStoredFuel() {
        return storedFuel;
    }

    public void addFuel(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be positive");
        }
        storedFuel += amount;
    }

    public boolean consumeFuel(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be positive");
        }
        if (storedFuel < amount) {
            return false;
        }
        storedFuel -= amount;
        return true;
    }
}
