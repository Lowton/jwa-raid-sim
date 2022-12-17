package com.github.lowton.jwa.schema.dto;

import java.util.Map;

import com.github.lowton.jwa.actor.dto.Actor;

public record TurnSchema(Map<Actor, String> moves) {
}
