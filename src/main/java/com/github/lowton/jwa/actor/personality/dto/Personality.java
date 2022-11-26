package com.github.lowton.jwa.actor.personality.dto;

import java.util.List;

public record Personality(
        String id,
        String name,
        int health,
        int attack,
        int speed,
        int armor,
        int critical,
        List<String> actions) {
}
