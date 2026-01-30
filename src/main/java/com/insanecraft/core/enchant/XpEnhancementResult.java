package com.insanecraft.core.enchant;

/**
 * Result data for applying an XP enhancement.
 */
public record XpEnhancementResult(EnchantedItem item, int remainingXpLevels) {
}
