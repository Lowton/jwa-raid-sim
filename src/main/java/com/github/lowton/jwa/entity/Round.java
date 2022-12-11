package com.github.lowton.jwa.entity;

import java.util.Collection;
import java.util.Map;

import com.github.lowton.jwa.actor.dto.Actor;

public class Round {
	private final Collection<Actor> actors;
	// TODO: reimplement movement map (two actors with the same ID cannot use different actions)
	private final Map<Integer, Map<String, String>> movementSchema;
	
	// TODO: implement round effects
	public Round(final Collection<Actor> actors, final Map<Integer, Map<String, String>> movementSchema) {
		this.actors = actors;
		this.movementSchema = movementSchema;
	}
	
	public void start() {
		for(int i = 1; i <= 20; ++i) {
			System.out.println("========= turn " + i + " =========");
			this.actors.forEach(System.out::println);
			final var turn = new Turn(this.actors, this.movementSchema.get(i));
			turn.complete();
			if(turn.allPlayersAreDead() || turn.bossIsDead()) {
				System.out.println("round is ended");
				break;
			}
		}
	}
}
