package com.github.lowton.jwa.action;

import com.github.lowton.jwa.action.dto.Action;

public class ActionService {

    private final ActionRepository actionRepository;

    public ActionService(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    public Action getAction(String id) {
        return this.actionRepository.getAction(id).orElseThrow();
    }
}
