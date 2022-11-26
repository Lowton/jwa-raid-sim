package com.github.lowton.jwa.action.effect;

import com.github.lowton.jwa.action.effect.dto.Effect;

import java.util.Optional;

public interface EffectRepository {
    Optional<Effect> getEffectById(String id);
}
