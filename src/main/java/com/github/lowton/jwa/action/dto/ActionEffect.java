package com.github.lowton.jwa.action.dto;

import com.github.lowton.jwa.action.effect.dto.Duration;
import com.github.lowton.jwa.action.effect.dto.Effect;
import com.github.lowton.jwa.action.effect.dto.EffectAim;
import com.github.lowton.jwa.action.effect.dto.Impact;
import com.github.lowton.jwa.action.effect.dto.Target;
import com.github.lowton.jwa.actor.dto.Actor;

public class ActionEffect {
	
	private final Effect effect;
	private final Action action;
	
	public ActionEffect(final Effect effect, final Action action) {
		this.effect = effect;
		this.action = action;
	}
	
	public String id() {
		return this.effect.id();
	}
	
	public String name() {
		return this.effect.name();
	}
	
	public Action action() {
		return this.action;
	}
	
	public EffectAim aim() {
		return this.effect.aim();
	}
	
	public Target target() {
		return this.effect.target();
	}
	
	public Duration duration() {
		return this.effect.duration();
	}
	
	public Impact impact() {
		return this.effect.impact();
	}
	
	public float power() {
		return this.effect.power();
	}
	
	public boolean applicable(final Actor actor, final Actor aim) {
		return switch (this.effect.aim()) {
			case OPPONENT -> actor.team() != aim.team();
			case TEAMMATE -> actor.team() == aim.team();
			case SELF -> actor.equals(aim);
		};
	}
}
