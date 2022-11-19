package com.github.lowton.jwa.entity;

public record Effect(String id, String name, EffectAim aim, Impact impact, float power) {

    public static Effect damage(final float power) {
        return new Effect("damage", "Damage", EffectAim.ENEMY, Impact.HEALTH_DECREASE, power);
    }
}
