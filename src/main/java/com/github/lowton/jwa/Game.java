package com.github.lowton.jwa;

import static java.util.stream.Collectors.toMap;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

import com.github.lowton.jwa.actor.ActorService;
import com.github.lowton.jwa.actor.dto.Actor;
import com.github.lowton.jwa.actor.dto.StatBoosts;
import com.github.lowton.jwa.actor.dto.Team;
import com.github.lowton.jwa.raid.Round;
import com.github.lowton.jwa.schema.Receiver;
import com.github.lowton.jwa.schema.boss.BossRepository;
import com.github.lowton.jwa.schema.dto.RaidSchema;
import com.github.lowton.jwa.schema.dto.RaidStrategyPlannerSchema;
import com.github.lowton.jwa.schema.dto.RoundSchema;
import com.github.lowton.jwa.schema.dto.TurnSchema;

public class Game {
	
	private final ActorService actorService;
	private final Receiver receiver;
	private final BossRepository bossRepository;
	
	public Game(final ActorService actorService, final Receiver receiver, final BossRepository bossRepository) {
		this.actorService = actorService;
		this.receiver = receiver;
		this.bossRepository = bossRepository;
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
		final var boss = this.bossRepository.getBossTeam(raidStrategyPlannerSchema.currentRaid());
		final Map<Integer, RoundSchema> raidMap = this.mapToRaidMap(raidStrategyPlannerSchema);

		return new RaidSchema(raidMap);
	}
	
	private Map<Integer, RoundSchema> mapToRaidMap(final RaidStrategyPlannerSchema schema) {
		final var creatures = schema.creatures().entrySet().stream()
				.collect(toMap(Map.Entry::getKey, entry -> this.getActor(entry.getValue().uuid(), Team.PLAYER)));
		
		final Map<Integer, RoundSchema> raidMap =
				schema.turnsPerRound().entrySet().stream()
						.collect(toMap(Map.Entry::getKey, entry -> this.getRoundSchema(entry.getValue())));
		
		final var moves = schema.moves();
		for (final int creatureNumber : moves.keySet()) {
			final var creatureMoves = moves.get(creatureNumber);
			for (final int roundNumber : creatureMoves.keySet()) {
				final var roundMoves = creatureMoves.get(roundNumber);
				for (final int turnNumber : roundMoves.keySet()) {
					final var moveId = roundMoves.get(turnNumber);
					final var creature = creatures.get(creatureNumber);
					raidMap.get(roundNumber).turnSchema(turnNumber).moves().put(creature, moveId);
				}
			}
		}
		
		return raidMap;
	}
	
	private Actor getActor(final String id, final Team team) {
		return this.actorService.getActor(id, 30, StatBoosts.empty(), team);
	}
	
	private RoundSchema getRoundSchema(final Integer entry) {
		final Map<Integer, TurnSchema> turns = IntStream.rangeClosed(1, entry)
				.boxed()
				.collect(toMap(num -> num, num -> new TurnSchema(new LinkedHashMap<>())));
		return new RoundSchema(turns);
	}
}
