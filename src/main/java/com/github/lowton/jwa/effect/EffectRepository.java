package com.github.lowton.jwa.effect;

import com.github.lowton.jwa.effect.dto.Effect;

import java.util.Optional;

public interface EffectRepository {
    Optional<Effect> getEffectById(String id);
}
