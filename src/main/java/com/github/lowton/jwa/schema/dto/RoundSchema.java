package com.github.lowton.jwa.schema.dto;

import java.util.Map;

public record RoundSchema(Map<Integer, TurnSchema> turns) {
	public TurnSchema turnSchema(final int turnNumber) {
		return this.turns.get(turnNumber);
	}
	
	public Iterable<Integer> turnSequence() {
		return this.turns.keySet().stream().sorted().toList();
	}
}
