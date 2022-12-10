package com.github.lowton.jwa.entity;

import java.util.Collection;
import java.util.Map;

import com.github.lowton.jwa.actor.dto.Actor;

public class Round {
	private final Collection<Actor> actors;
	private final Map<Integer, Map<String, String>> movementSchema;
	
	// TODO: implement round effects
	public Round(final Collection<Actor> actors, final Map<Integer, Map<String, String>> movementSchema) {
		this.actors = actors;
		this.movementSchema = movementSchema;
	}
	
	public void start() {
		for(int i = 1; i <= 20; ++i) {
			final var turn = new Turn(this.actors, this.movementSchema.get(i));
			if(turn.allPlayersAreDead() || turn.bossIsDead()) {
				break;
			}
			turn.complete();
		}
	}
}
