package com.github.lowton.jwa.raid;

import static com.github.lowton.jwa.test.TestFactory.actor;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.github.lowton.jwa.actor.dto.Team;
import com.github.lowton.jwa.schema.dto.TurnSchema;

class TurnTest {
	
	@Test
	void simpleTurnCheck() {
		final var goodGuy = actor("good guy", 50, 25, Team.PLAYER);
		final var boss = actor("boss", 100, 15, Team.BOSS);
		final var turnSchema = new TurnSchema(Map.of(
				goodGuy, "test-action",
				boss, "test-action"));
		final var turn = new Turn(turnSchema);
		
		turn.complete();
		
		assertThat(goodGuy.health()).isEqualTo(50 - 15);
		assertThat(boss.health()).isEqualTo(100 - 25);
	}
}