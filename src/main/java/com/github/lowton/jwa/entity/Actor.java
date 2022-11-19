package com.github.lowton.jwa.entity;

import java.util.Map;
import java.util.Queue;

import static java.util.Objects.requireNonNullElse;

public class Actor {

    // возможно имеет смысл вынести статы в отдельный класс
    private final String id;
    private final String name;
    private final int health;
    private int remainHealth;
    private final int attack;
    private final int speed;
    private final Team team;
    private Map<Integer, Action> moves;

    private Queue<Integer> movesQueue;

    public Actor(String id, String name, int health, int attack, int speed, Team team) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.remainHealth = health;
        this.attack = attack;
        this.speed = speed;
        this.team = team;
    }

    private Actor setMoves(Map<Integer, Action> moves) {
        this.moves = moves;
        return this;
    }

    public Actor setMovesQueue(Queue<Integer> movesQueue) {
        this.movesQueue = movesQueue;
        return this;
    }

    public int attack() {
        return attack;
    }

    public int speed() {
        return speed;
    }

    // список доступных действий
    // список действующих эффектов
    // характеристики

    // метод применить на себя действе
    // метод валидировать применимо ли действие

    public boolean alive() {
        return this.remainHealth > 0;
    }

    public Actor apply(final Action action) {
        for(var effect: action.effects()) {
            if (this.applicable(effect, action.actor())) {
                System.out.println(action.actor().name + " uses " + action.name() + " on " + this.name);
                this.apply(effect);
            }
        }
        return this;
    }

    private void apply(Effect effect) {
        switch (effect.impact()) {
            case HEALTH_DECREASE -> {
                this.remainHealth -= effect.power();
                System.out.println(this.name + " got " + effect.power() + " as " + effect.name() + " and now have " + this.remainHealth + " HP");
            }
        }
    }

    private boolean applicable(Effect effect, Actor actor) {
        return switch (effect.aim()){
            case ENEMY -> actor.team != this.team;
            case FRIEND -> actor.team == this.team;
            case SELF -> actor == this;
        };
    }

    public Action act() {
        final var moveNumber = requireNonNullElse(movesQueue.poll(), 1);
        return this.moves.get(moveNumber);
    }

    public static Actor player(String id, String name, int health, int attack, int speed) {
        final var actor = new Actor(id, name, health, attack, speed, Team.PLAYER);
        return actor.setMoves(Map.of(1, Action.hit(actor)));
    }
    public static Actor boss(String id, String name, int health, int attack, int speed) {
        final var actor = new Actor(id, name, health, attack, speed, Team.BOSS);
        return actor.setMoves(Map.of(1, Action.hit(actor)));
    }
}
