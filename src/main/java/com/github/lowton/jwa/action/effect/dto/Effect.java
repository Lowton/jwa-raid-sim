package com.github.lowton.jwa.action.effect.dto;

public record Effect(String id, String name, EffectAim aim, Target target, Impact impact, float power, Duration duration) {
}
