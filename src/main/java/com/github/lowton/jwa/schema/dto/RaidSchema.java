package com.github.lowton.jwa.schema.dto;


import java.util.Map;

public record RaidSchema(Map<Integer, RoundSchema> rounds) {
	public RoundSchema roundSchema(final int roundNumber) {
		return this.rounds.get(roundNumber);
	}
	
	public Iterable<Integer> roundSequence() {
		return this.rounds.keySet().stream().sorted().toList();
	}
}
