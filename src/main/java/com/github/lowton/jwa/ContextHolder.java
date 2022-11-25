package com.github.lowton.jwa;

import com.github.lowton.jwa.action.DummyActionRepository;
import com.github.lowton.jwa.action.ActionService;
import com.github.lowton.jwa.actor.ActorService;
import com.github.lowton.jwa.personality.DummyPersonalityRepository;
import com.github.lowton.jwa.resistance.DummyResistanceRepository;

public class ContextHolder {

    private ContextHolder() {}

    public static void run() {
        new Game(
                new ActorService(
                        new DummyPersonalityRepository(),
                        new DummyResistanceRepository(),
                        new ActionService(
                                new DummyActionRepository()
                        )))
                .start();
    }
}
