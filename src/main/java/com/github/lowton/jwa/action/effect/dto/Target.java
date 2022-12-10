package com.github.lowton.jwa.action.effect.dto;

import java.util.Comparator;

import com.github.lowton.jwa.actor.dto.Actor;
import com.github.lowton.jwa.entity.Characteristic;
import com.github.lowton.jwa.entity.Extremum;

public record Target(Characteristic characteristic, Extremum extremum) {

    public static Target maxHealth() {
        return new Target(Characteristic.HEALTH, Extremum.MAXIMUM);
    }
    
    public Comparator<Actor> comparing() {
        return switch (this.characteristic) {
            case ATTACK -> Comparator.comparing(Actor::attack);
            case HEALTH -> Comparator.comparing(Actor::health);
            case SPEED -> Comparator.comparing(Actor::speed);
        };
    }
}
