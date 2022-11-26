package com.github.lowton.jwa.actor.dto;

import com.github.lowton.jwa.action.dto.Action;
import com.github.lowton.jwa.action.effect.dto.Effect;
import com.github.lowton.jwa.actor.personality.dto.Personality;
import com.github.lowton.jwa.actor.resistance.dto.ResistanceType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class Actor {
    private final Personality personality;
    private final int level;
    private final Map<ResistanceType, Integer> resistance;

    private final StatBoosts boosts;
    private final int remainHealth;
    private final Team team;
    private final Map<Integer, Action> actions;
    private List<Effect> activeEffects = new ArrayList<>();

    public Actor(Personality personality, int level, Map<ResistanceType, Integer> resistance,
                 StatBoosts boosts, Team team, List<Action> actions) {
        this.personality = personality;
        this.level = level;
        this.resistance = resistance;
        this.boosts = boosts;
        this.remainHealth = this.personality.health();
        this.team = team;
        this.actions = this.toActionMap(actions);
    }

    private Map<Integer, Action> toActionMap(List<Action> actions) {
        return actions.stream()
                .map(action -> action.usedBy(this))
                .collect(toMap(action -> actions.indexOf(action) + 1, action -> action));
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
