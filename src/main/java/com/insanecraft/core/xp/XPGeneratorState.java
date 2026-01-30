package com.insanecraft.core.xp;

/**
 * Tracks XP generator tier and stored XP for block entities.
 */
public final class XPGeneratorState {
    private int level;
    private int storedXp;

    public XPGeneratorState(int level, int storedXp) {
        this.level = Math.max(1, level);
        this.storedXp = Math.max(0, storedXp);
    }

    public int getLevel() {
        return level;
    }

    public int getStoredXp() {
        return storedXp;
    }

    public void addXp(int amount) {
        if (amount > 0) {
            storedXp += amount;
        }
    }

    public boolean tryUpgrade(int xpBottleValue, int requiredXp, int maxLevel) {
        if (level >= maxLevel) {
            return false;
        }
        storedXp += Math.max(0, xpBottleValue);
        if (storedXp >= requiredXp) {
            storedXp = 0;
            level++;
            return true;
        }
        return false;
    }
}
