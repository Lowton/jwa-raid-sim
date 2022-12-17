package com.github.lowton.jwa;

import java.util.Map;

import com.github.lowton.jwa.actor.ActorService;
import com.github.lowton.jwa.actor.dto.StatBoosts;
import com.github.lowton.jwa.actor.dto.Team;
import com.github.lowton.jwa.raid.Round;
import com.github.lowton.jwa.schema.Receiver;
import com.github.lowton.jwa.schema.dto.RaidSchema;
import com.github.lowton.jwa.schema.dto.RoundSchema;
import com.github.lowton.jwa.schema.dto.TurnSchema;

public class Game {
	
	private final ActorService actorService;
	private final Receiver receiver;
	
	public Game(final ActorService actorService, final Receiver receiver) {
		this.actorService = actorService;
		this.receiver = receiver;
	}
	
	public void start() {
		System.out.println("Starting battle");
		final RaidSchema raidSchema = this.getRaidSchema();
		
		for (final int roundNumber : raidSchema.roundSequence()) {
			System.out.println("Start round " + roundNumber);
			new Round(raidSchema.roundSchema(roundNumber)).start();
			System.out.println("Round " + roundNumber + " is ended");
		}
		
		System.out.println("Finish");
	}
	
	private RaidSchema getRaidSchema() {
		final var raidStrategyPlannerSchema = this.receiver.raidStrategyPlannerSchema();
		
		final var tor1 = this.actorService.getActor("tor", 30, StatBoosts.empty(), Team.PLAYER);
		final var tor2 = this.actorService.getActor("tor", 30, StatBoosts.empty(), Team.PLAYER);
		final var goat = this.actorService.getActor("goat", 30, StatBoosts.empty(), Team.BOSS);
		return new RaidSchema(Map.of(
				1, new RoundSchema(Map.of(
						1, new TurnSchema(Map.of(
								tor1, "simple-attack",
								tor2, "simple-attack",
								goat, "simple-attack")),
						2, new TurnSchema(Map.of(
								tor1, "simple-attack",
								tor2, "simple-attack",
								goat, "simple-attack")),
						3, new TurnSchema(Map.of(
								tor1, "simple-attack",
								tor2, "simple-attack",
								goat, "simple-attack")),
						4, new TurnSchema(Map.of(
								tor1, "simple-attack",
								tor2, "simple-attack",
								goat, "simple-attack")),
						5, new TurnSchema(Map.of(
								tor1, "simple-attack",
								tor2, "simple-attack",
								goat, "simple-attack")),
						6, new TurnSchema(Map.of(
								tor1, "simple-attack",
								tor2, "simple-attack",
								goat, "simple-attack")),
						7, new TurnSchema(Map.of(
								tor1, "simple-attack",
								tor2, "simple-attack",
								goat, "simple-attack"))
				))
		)
		);
	}
}
