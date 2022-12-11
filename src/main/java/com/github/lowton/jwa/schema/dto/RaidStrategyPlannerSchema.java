package com.github.lowton.jwa.schema.dto;

import java.util.Map;

public record RaidStrategyPlannerSchema(String currentRaid,
										Map<Integer, Integer> turnsPerRound,
										Map<Integer, Creature> creatures,
										Map<Integer, Map<Integer, Map<Integer, String>>> moves) {
	
	public static Creature creature(String uuid) {
		return new Creature(uuid);
	}
	
	public record Creature(String uuid) {
	}
}
