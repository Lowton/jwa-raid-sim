package com.github.lowton.jwa.action.act;

import com.github.lowton.jwa.action.act.dto.Act;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DummyActRepository implements ActRepository {

    private final Map<String, Act> acts;

    public DummyActRepository() {
        this.acts = Map.of(
                "simple-attack", new Act("simple-attack", "Bite", List.of("damage"))
        );
    }

    @Override
    public Optional<Act> getAct(String id) {
        return Optional.ofNullable(this.acts.get(id));
    }
}
