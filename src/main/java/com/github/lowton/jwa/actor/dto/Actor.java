package com.github.lowton.jwa.actor.dto;

import com.github.lowton.jwa.action.dto.Action;
import com.github.lowton.jwa.effect.dto.Effect;
import com.github.lowton.jwa.personality.dto.Personality;
import com.github.lowton.jwa.resistance.dto.ResistanceType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Actor {
    private final Personality personality;
    private final int level;
    private final Map<ResistanceType, Integer> resistance;
    private final int remainHealth;
    private final Team team;
    private final Map<Integer, Action> moves;
    private List<Effect> activeEffects = new ArrayList<>();

    public Actor(Personality personality, int level, Map<ResistanceType, Integer> resistance,
                 Team team, Map<Integer, Action> moves) {
        this.personality = personality;
        this.level = level;
        this.resistance = resistance;
        this.remainHealth = this.personality.health();
        this.team = team;
        this.moves = moves;
    }

    // метод применить на себя действе
    // метод валидировать применимо ли действие

    // Завязать здоровье, скорость, урон на модификаторы эффектов
    public int attack() {
        return this.personality.attack();
    }

    public int speed() {
        return this.personality.speed();
    }

    public int health() {
        return this.remainHealth;
    }

    public boolean alive() {
        return this.remainHealth > 0;
    }
}
