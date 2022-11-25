package com.github.lowton.jwa.effect;

import com.github.lowton.jwa.effect.dto.Effect;

import java.util.Map;
import java.util.Optional;

public class DummyEffectRepository implements EffectRepository {

    private Map<String, Effect> effectMap;

    public DummyEffectRepository() {
        this.effectMap = Map.of();
    }

    public Optional<Effect> getEffectById(String id) {
        return Optional.ofNullable(this.effectMap.get(id));
    }
}
