package com.github.lowton.jwa;

import com.github.lowton.jwa.actor.ActorService;
import com.github.lowton.jwa.actor.dto.Actor;
import com.github.lowton.jwa.actor.dto.StatBoosts;
import com.github.lowton.jwa.actor.dto.Team;
import com.github.lowton.jwa.entity.Round;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Game {
	
	private ActorService actorService;
	
	public Game(ActorService actorService) {
		this.actorService = actorService;
	}
	
	public void start() {
		System.out.println("Starting battle");
		final var actors = this.prepareActors();
		final var movementSchema = prepareMovementSchema();
		
		new Round(actors, movementSchema).start();
		
		System.out.println("Finish");
	}
	
	private Set<Actor> prepareActors() {
		return Set.of(
				this.actorService.getActor("tor", 30, StatBoosts.empty(), Team.PLAYER),
				this.actorService.getActor("goat", 30, StatBoosts.empty(), Team.BOSS)
		);
	}
	
	private Map<Integer, Map<String, String>> prepareMovementSchema() {
		final var map = new HashMap<Integer, Map<String, String>>();
		for (int i = 1; i <= 20; ++i) {
			map.put(i, Map.of(
					"tor", "simple-attack",
					"goat", "simple-attack"
			));
		}
		
		return map;
	}
}
