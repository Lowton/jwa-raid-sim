package com.github.lowton.jwa.test;

import com.github.lowton.jwa.action.act.dto.Act;
import com.github.lowton.jwa.action.dto.Action;
import com.github.lowton.jwa.action.effect.dto.Duration;
import com.github.lowton.jwa.action.effect.dto.Effect;
import com.github.lowton.jwa.action.effect.dto.EffectAim;
import com.github.lowton.jwa.action.effect.dto.Impact;
import com.github.lowton.jwa.actor.personality.dto.Personality;
import com.github.lowton.jwa.actor.resistance.dto.Resistance;
import com.github.lowton.jwa.action.effect.dto.Target;

import java.util.List;

public class TestFactory {

    public static Act act(String name, String effectName) {
        return new Act(name, name, List.of(effectName));
    }
    public static Effect effect(String name) {
        return new Effect(name, name, EffectAim.OPPONENT, Target.maxHealth(), Impact.HEALTH_DECREASE, 1, Duration.instant());
    }

    public static Personality personality(String name, String action) {
        return new Personality(name, name, 100, 10, 1, 0, 5, List.of(action));
    }

    public static Resistance.Builder resistance(String id) {
        return Resistance.builder(id)
                .critReduction(50);
    }

    public static Action action(String name, String effect) {
        return new Action(act(name, effect), List.of(effect(effect)));
    }
}
