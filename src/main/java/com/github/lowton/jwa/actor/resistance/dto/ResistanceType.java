package com.github.lowton.jwa.actor.resistance.dto;

import java.util.Arrays;

public enum ResistanceType {
    critReduction("critReduction"),
    distraction("distraction"),
    dot("dot"),
    rend("rend"),
    speedDecrease("speedDecrease"),
    stun("stun"),
    swapPrevention("swapPrevention"),
    taunt("taunt"),
    vulnerable("vulnerable");

    private final String value;

    ResistanceType(String value) {
        this.value = value;
    }

    public static ResistanceType of(String value) {
        return Arrays.stream(ResistanceType.values())
                .filter(type -> value.equals(type.value))
                .findAny()
                .orElseThrow();
    }
}
