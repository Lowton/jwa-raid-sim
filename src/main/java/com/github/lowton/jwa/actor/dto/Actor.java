package com.github.lowton.jwa.actor.dto;

import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.lowton.jwa.action.dto.Action;
import com.github.lowton.jwa.action.dto.ActionEffect;
import com.github.lowton.jwa.action.effect.dto.Duration;
import com.github.lowton.jwa.actor.personality.dto.Personality;
import com.github.lowton.jwa.actor.resistance.dto.ResistanceType;

public class Actor {
	private final Personality personality;
	private final int level;
	private final Map<ResistanceType, Integer> resistance;
	
	private final StatBoosts boosts;
	private int remainHealth;
	private final Team team;
	private final Map<String, Action> actions;
	private List<ActionEffect> activeEffects = new ArrayList<>();
	
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
	
	private Map<String, Action> toActionMap(List<Action> actions) {
		return actions.stream()
				.map(action -> action.usedBy(this))
				.collect(toMap(Action::id, action -> action));
	}
	
	// метод применить на себя действе
	// метод валидировать применимо ли действие
	
	// Завязать здоровье, скорость, урон на модификаторы эффектов
	public String id() {
		return this.personality.id();
	}
	
	public int attack() {
		return this.personality.attack();
	}
	
	public int health() {
		return this.remainHealth;
	}
	
	public Team team() {
		return this.team;
	}
	
	public int speed() {
		return this.personality.speed();
	}
	
	public boolean alive() {
		return this.remainHealth > 0;
	}
	
	public Action action(final String actionId) {
		return this.actions.get(actionId);
	}
	
	public void apply(final ActionEffect effect) {
		if (Duration.instant().equals(effect.duration())) {
			this.applyInstant(effect);
		}
		this.activeEffects.add(effect);
	}
	
	private void applyInstant(final ActionEffect effect) {
		switch (effect.impact()) {
			case HEALTH_DECREASE -> this.gotHit(effect);
		}
	}
	
	private void gotHit(final ActionEffect effect) {
		final var enemy = effect.action().actor();
		this.remainHealth -= enemy.attack() * effect.power();
	}
	
	@Override
	public String toString() {
		return "Actor [" +
				"name: " + personality.name() +
				", remainHealth: " + remainHealth +
				", team: " + team +
				']';
	}
}
