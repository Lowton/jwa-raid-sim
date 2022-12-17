package com.github.lowton.jwa.raid;

import com.github.lowton.jwa.schema.dto.RoundSchema;

public class Round {
	private final RoundSchema roundSchema;
	
	// TODO: implement round effects
	public Round(final RoundSchema roundSchema) {
		this.roundSchema = roundSchema;
	}
	
	public void start() {
		for(final int turnNumber: this.roundSchema.turnSequence()) {
			System.out.println("========= turn " + turnNumber + " =========");
			final var turn = new Turn(this.roundSchema.turnSchema(turnNumber));
			turn.complete();
			if(turn.allPlayersAreDead() || turn.bossIsDead()) {
				System.out.println("round is ended");
				break;
			}
		}
	}
}
