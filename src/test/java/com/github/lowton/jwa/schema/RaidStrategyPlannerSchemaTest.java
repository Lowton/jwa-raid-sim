package com.github.lowton.jwa.schema;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lowton.jwa.schema.dto.RaidStrategyPlannerSchema;

class RaidStrategyPlannerSchemaTest {
	
	private ObjectMapper objectMapper;
	
	@BeforeEach
	void setUp() {
		this.objectMapper = new ObjectMapper();
	}
	
	@Test
	void writeToJson() throws Exception {
		final var raidSchema = new RaidStrategyPlannerSchema("goat_raid",
				Map.of(1, 4),
				Map.of(1, RaidStrategyPlannerSchema.creature("tor")),
				Map.of(1, Map.of(1, Map.of(
						1, "simple-attack",
						2, "simple-attack",
						3, "simple-attack",
						4, "simple-attack"))));
		final var raidJson = this.objectMapper.writeValueAsString(raidSchema);
		System.out.println(raidJson);
	}
	
	@Test
	void readJson() throws Exception {
		// Raid JSON data is used from file exported from JWA raid strategy planner on www.paleo.gg
		final var json = new File("src/test/resources/json/example.json");
		this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		final RaidStrategyPlannerSchema schema = this.objectMapper.readValue(json, RaidStrategyPlannerSchema.class);
		
		assertThat(schema.currentRaid()).isEqualTo("b_meiolania");
		assertThat(schema.turnsPerRound()).containsEntry(1, 4);
		assertThat(schema.creatures())
				.containsEntry(1, RaidStrategyPlannerSchema.creature("albertosaurus"))
				.containsEntry(2, RaidStrategyPlannerSchema.creature("ankylosaurus_gen_2"));
		assertThat(schema.moves().get(1).get(1))
				.containsEntry(1, "fierce_impact")
				.containsEntry(2, "killer_instinct")
				.containsEntry(3, "fierce_impact")
				.containsEntry(4, "killer_instinct");
		assertThat(schema.moves().get(1).get(2))
				.containsEntry(1, "fierce_impact")
				.containsEntry(2, "killer_instinct")
				.containsEntry(3, "fierce_impact")
				.containsEntry(4, "killer_instinct");
		assertThat(schema.moves().get(2).get(1))
				.containsEntry(1, "superior_vulnerability")
				.containsEntry(2, "group_taunting_shields")
				.containsEntry(3, "superior_vulnerability")
				.containsEntry(4, "group_taunting_shields");
		assertThat(schema.moves().get(2).get(2))
				.containsEntry(1, "superior_vulnerability")
				.containsEntry(2, "group_taunting_shields")
				.containsEntry(3, "superior_vulnerability")
				.containsEntry(4, "group_taunting_shields");
	}
}