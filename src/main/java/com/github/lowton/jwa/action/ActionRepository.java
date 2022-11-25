package com.github.lowton.jwa.action;

import com.github.lowton.jwa.action.dto.Action;

import java.util.Optional;

public interface ActionRepository {
    Optional<Action> getAction(String id);
}
