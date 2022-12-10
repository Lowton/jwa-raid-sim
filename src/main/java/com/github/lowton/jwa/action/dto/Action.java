package com.github.lowton.jwa.action.dto;

import com.github.lowton.jwa.action.act.dto.Act;
import com.github.lowton.jwa.actor.dto.Actor;
import com.github.lowton.jwa.action.effect.dto.Effect;

import java.util.List;

public class Action {
	private final Act act;
	private Actor actor;
	private final List<ActionEffect> effects;
	
	public Action(Act act, List<Effect> effects) {
		this.act = act;
		this.effects = effects.stream()
				.map(this::from)
				.toList();
	}
	
	public String id() {
		return act.id();
	}
	
	public String name() {
		return act.name();
	}
	
	public Actor actor() {
		return actor;
	}
	
	public List<ActionEffect> effects() {
		return effects;
	}
	
	public Action usedBy(Actor actor) {
		this.actor = actor;
		return this;
	}
	
	private ActionEffect from(Effect effect) {
		return new ActionEffect(effect, this);
	}
}
