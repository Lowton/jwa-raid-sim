package com.github.lowton.jwa.test;

import com.github.lowton.jwa.action.act.dto.Act;
import com.github.lowton.jwa.action.dto.Action;
import com.github.lowton.jwa.action.effect.dto.Duration;
import com.github.lowton.jwa.action.effect.dto.Effect;
import com.github.lowton.jwa.action.effect.dto.EffectAim;
import com.github.lowton.jwa.action.effect.dto.Impact;
import com.github.lowton.jwa.actor.dto.Actor;
import com.github.lowton.jwa.actor.dto.StatBoosts;
import com.github.lowton.jwa.actor.dto.Team;
import com.github.lowton.jwa.actor.personality.dto.Personality;
import com.github.lowton.jwa.actor.resistance.dto.Resistance;
import com.github.lowton.jwa.action.effect.dto.Target;

import java.util.List;
import java.util.Map;

public class TestFactory {

    public static Act act(final String name, final String effectName) {
        return new Act(name, name, List.of(effectName));
    }
    public static Effect effect(final String name) {
        return new Effect(name, name, EffectAim.OPPONENT, Target.maxHealth(), Impact.HEALTH_DECREASE, 1, Duration.instant());
    }

    public static Personality personality(final String name, final String action) {
        return personality(name, action, 100, 10);
    }
    
    public static Personality personality(final String name, final String action, final int health, final int attack) {
        return new Personality(name, name, health, attack, 1, 0, 5, List.of(action));
    }

    public static Resistance.Builder resistance(final String id) {
        return Resistance.builder(id)
                .critReduction(50);
    }

    public static Action action(final String name, final String effect) {
        return new Action(act(name, effect), List.of(effect(effect)));
    }
    
    public static Actor actor(final String name, final int health, final int attack, final Team team) {
            final var actionName = "test-action";
        return new Actor(
                personality(name, actionName, health, attack),
                1, Map.of(), StatBoosts.empty(),
                team, List.of(action(actionName, "Kick")));
    }
}
