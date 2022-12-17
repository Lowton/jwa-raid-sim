package com.github.lowton.jwa.schema;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lowton.jwa.schema.dto.RaidStrategyPlannerSchema;

public class DummyReceiver implements Receiver {
	
	private final File raidSchemaJson;
	
	public DummyReceiver(final String path) {
		this.raidSchemaJson = new File(path);
	}
	
	@Override
	public RaidStrategyPlannerSchema raidStrategyPlannerSchema() {
		final var objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		try {
			return objectMapper.readValue(this.raidSchemaJson, RaidStrategyPlannerSchema.class);
		}
		catch (final IOException e) {
			throw new RaidSchemaException("Couldn't resolve json to raid schema", e);
		}
	}
}
