package com.github.lowton.jwa.schema;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lowton.jwa.schema.dto.RaidSchema;
import com.github.lowton.jwa.schema.dto.RaidStrategyPlannerSchema;

public class Receiver {
	
	private final File raidSchemaJson;
	
	public Receiver(final File raidSchemaJson) {
		this.raidSchemaJson = raidSchemaJson;
	}
	
	public RaidSchema raidSchema() throws IOException {
		final var objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		final var schema = objectMapper.readValue(this.raidSchemaJson, RaidStrategyPlannerSchema.class);
		
		return this.mapToRaidSchema(schema);
	}
	
	private RaidSchema mapToRaidSchema(final RaidStrategyPlannerSchema schema) {
		return null;
	}
}
