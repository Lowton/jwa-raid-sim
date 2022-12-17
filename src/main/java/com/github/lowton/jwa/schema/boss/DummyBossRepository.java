package com.github.lowton.jwa.schema.boss;

import static java.util.Collections.emptyList;

import java.util.Map;

import com.github.lowton.jwa.schema.boss.dto.Boss;

public class DummyBossRepository implements BossRepository {
	
	private final Map<String, Boss> bosses;
	
	public DummyBossRepository() {
		this.bosses = Map.of(
				"goat", new Boss(
						Map.of(
								1, "goat_1",
								2, "goat_2"
						),
						emptyList())
		);
	}
	
	
	@Override
	public Boss getBossTeam(final String id) {
		return this.bosses.get(id);
	}
}
