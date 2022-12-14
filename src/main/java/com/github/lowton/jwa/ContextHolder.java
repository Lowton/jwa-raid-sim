package com.github.lowton.jwa;

import com.github.lowton.jwa.action.act.DummyActRepository;
import com.github.lowton.jwa.action.ActionService;
import com.github.lowton.jwa.actor.ActorService;
import com.github.lowton.jwa.action.effect.DummyEffectRepository;
import com.github.lowton.jwa.actor.personality.DummyPersonalityRepository;
import com.github.lowton.jwa.actor.resistance.DummyResistanceRepository;
import com.github.lowton.jwa.schema.DummyReceiver;
import com.github.lowton.jwa.schema.boss.DummyBossRepository;

public class ContextHolder {

    private ContextHolder() {}

    public static void run() {
        final var pathToJson = "src/main/resources/dummy.json";
        
        new Game(
                new ActorService(
                        new DummyPersonalityRepository(),
                        new DummyResistanceRepository(),
                        new ActionService(
                                new DummyActRepository(),
                                new DummyEffectRepository())),
                new DummyReceiver(pathToJson),
                new DummyBossRepository())
                .start();
    }
}
