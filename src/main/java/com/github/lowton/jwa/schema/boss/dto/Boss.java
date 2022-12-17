package com.github.lowton.jwa.schema.boss.dto;

import java.util.List;
import java.util.Map;

public record Boss(Map<Integer, String> bossActors, List<String> minions) {
}
