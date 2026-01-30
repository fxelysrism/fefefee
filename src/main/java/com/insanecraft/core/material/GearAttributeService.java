package com.insanecraft.core.material;

/**
 * Calculates gear attributes for ruby tiers.
 */
public class GearAttributeService {
    public GearAttributes buildRubyAttributes(RubyTier tier, double baseDamage, double baseSpeed) {
        double damage = baseDamage * tier.getDamageMultiplier();
        double speed = baseSpeed + tier.getSpeedBonus();
        return new GearAttributes(damage, speed);
    }
}
