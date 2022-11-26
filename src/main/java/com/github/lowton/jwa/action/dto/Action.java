package com.github.lowton.jwa.action.dto;

import com.github.lowton.jwa.action.act.dto.Act;
import com.github.lowton.jwa.actor.dto.Actor;
import com.github.lowton.jwa.action.effect.dto.Effect;
import com.github.lowton.jwa.entity.Target;

import java.util.List;

public class Action {
    private final Act act;
    private Actor actor;
    private final List<Effect> effects;

    public Action(Act act, List<Effect> effects) {
        this.act = act;
        this.effects = effects;
    }

    public String getId() {
        return act.id();
    }

    public String getName() {
        return act.name();
    }

    public Target getTarget() {
        return act.target();
    }

    public Actor getActor() {
        return actor;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public Action usedBy(Actor actor) {
        this.actor = actor;
        return this;
    }
}
