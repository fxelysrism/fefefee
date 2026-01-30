package com.insanecraft.core.xp;

import java.util.Map;

/**
 * Placeholder data model for XP Generator tiers.
 */
public class XPGenerator {
    private static final Map<Integer, String> OUTPUT_BY_LEVEL = Map.of(
        1, "low",
        3, "medium",
        5, "insane"
    );

    private static final int MAX_LEVEL = 5;
    private static final int BASE_REQUIRED_XP = 10;

    public void register() {
        // Hook block entity and interaction registration for your mod loader.
    }

    public String getOutputForLevel(int level) {
        return OUTPUT_BY_LEVEL.getOrDefault(level, "low");
    }

    public int getMaxLevel() {
        return MAX_LEVEL;
    }

    public int getRequiredXpForNextLevel(int level) {
        return BASE_REQUIRED_XP * Math.max(1, level);
    }
}
