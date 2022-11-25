package com.github.lowton.jwa.personality.dto;

import java.util.Arrays;
import java.util.List;

public record Personality(
        String id,
        String name,
        int health,
        int attack,
        int speed,
        int armor,
        int critical,
        String firstMove,
        String secondMove,
        String thirdMove,
        String fourthMove) {

    public List<String> getMoves() {
        return Arrays.asList(firstMove, secondMove, thirdMove, fourthMove);
    }
}
