package com.github.lowton.jwa;

import com.github.lowton.jwa.action.act.DummyActRepository;
import com.github.lowton.jwa.action.ActionService;
import com.github.lowton.jwa.actor.ActorService;
import com.github.lowton.jwa.action.effect.DummyEffectRepository;
import com.github.lowton.jwa.actor.personality.DummyPersonalityRepository;
import com.github.lowton.jwa.actor.resistance.DummyResistanceRepository;

public class ContextHolder {

    private ContextHolder() {}

    public static void run() {
        new Game(
                new ActorService(
                        new DummyPersonalityRepository(),
                        new DummyResistanceRepository(),
                        new ActionService(
                                new DummyActRepository(),
                                new DummyEffectRepository())))
                .start();
    }
}
