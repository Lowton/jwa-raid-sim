package com.github.lowton.jwa.action;

import com.github.lowton.jwa.action.dto.Action;
import com.github.lowton.jwa.entity.Target;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DummyActionRepository implements ActionRepository {

    private final Map<String, Action> actions;

    public DummyActionRepository() {
        this.actions = Map.of(
                "simple-attack", new Action("simple-attack", "Bite", Target.maxHealth(), List.of())
        );
    }

    @Override
    public Optional<Action> getAction(String id) {
        return Optional.ofNullable(this.actions.get(id));
    }
}
