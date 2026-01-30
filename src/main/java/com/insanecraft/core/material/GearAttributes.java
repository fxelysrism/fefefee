package com.insanecraft.core.material;

/**
 * Derived combat attributes for custom gear tiers.
 */
public final class GearAttributes {
    private final double baseDamage;
    private final double attackSpeed;

    public GearAttributes(double baseDamage, double attackSpeed) {
        this.baseDamage = baseDamage;
        this.attackSpeed = attackSpeed;
    }

    public double getBaseDamage() {
        return baseDamage;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    @Override
    public String toString() {
        return "damage=" + baseDamage + ", speed=" + attackSpeed;
    }
}
