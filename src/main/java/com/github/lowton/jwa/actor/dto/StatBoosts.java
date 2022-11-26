package com.github.lowton.jwa.actor.dto;

public record StatBoosts(int health, int attack, int speed) {
    public static StatBoosts empty() {
        return new StatBoosts(0, 0, 0);
    }
}
