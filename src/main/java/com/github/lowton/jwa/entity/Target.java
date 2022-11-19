package com.github.lowton.jwa.entity;

public record Target(Characteristic characteristic, Extremum extremum) {

    public static Target maxHealth() {
        return new Target(Characteristic.HEALTH, Extremum.MAXIMUM);
    }
}
