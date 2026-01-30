package com.insanecraft.core.altar;

/**
 * Tracks available starlight for celestial altar rituals.
 */
public final class StarlightPool {
    private int starlight;

    public StarlightPool(int starlight) {
        if (starlight < 0) {
            throw new IllegalArgumentException("starlight cannot be negative");
        }
        this.starlight = starlight;
    }

    public int getStarlight() {
        return starlight;
    }

    public void add(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be positive");
        }
        starlight += amount;
    }

    public boolean consume(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be positive");
        }
        if (starlight < amount) {
            return false;
        }
        starlight -= amount;
        return true;
    }
}
