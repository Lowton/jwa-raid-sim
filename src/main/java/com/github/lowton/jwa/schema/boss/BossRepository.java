package com.github.lowton.jwa.schema.boss;

import com.github.lowton.jwa.schema.boss.dto.Boss;

public interface BossRepository {
	Boss getBossTeam(String id);
}
