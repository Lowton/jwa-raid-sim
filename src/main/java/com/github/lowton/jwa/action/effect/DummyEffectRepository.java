package com.github.lowton.jwa.action.effect;

import com.github.lowton.jwa.action.effect.dto.Effect;
import com.github.lowton.jwa.action.effect.dto.EffectAim;
import com.github.lowton.jwa.action.effect.dto.Impact;

import java.util.Map;
import java.util.Optional;

public class DummyEffectRepository implements EffectRepository {

    private Map<String, Effect> effectMap;

    public DummyEffectRepository() {
        this.effectMap = Map.of(
                "damage", new Effect("damage", "Damage", EffectAim.OPPONENT, Impact.HEALTH_DECREASE, 1)
        );
    }

    public Optional<Effect> getEffectById(String id) {
        return Optional.ofNullable(this.effectMap.get(id));
    }
}
