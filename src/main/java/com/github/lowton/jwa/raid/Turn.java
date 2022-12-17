package com.github.lowton.jwa.raid;

import static java.util.Collections.emptyList;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import com.github.lowton.jwa.action.dto.Action;
import com.github.lowton.jwa.action.dto.ActionEffect;
import com.github.lowton.jwa.action.effect.dto.Target;
import com.github.lowton.jwa.actor.dto.Actor;
import com.github.lowton.jwa.actor.dto.Team;
import com.github.lowton.jwa.schema.dto.TurnSchema;

public class Turn {
	private final Collection<Actor> actors;
	private final Map<Actor, String> moves;
	
	public Turn(final TurnSchema moves) {
		this.moves = moves.moves();
		this.actors = this.moves.keySet();
	}
	
	public void complete() {
		this.actors.forEach(System.out::println);
		final var actions = this.actors.stream().map(actor -> actor.action(this.moves.get(actor))).toList();
		final var queue = new MovementQueue(actions);
		
		while (queue.isNotEmpty()) {
			final var currentAction = queue.next();
			currentAction.map(Action::effects)
					.orElse(emptyList())
					.forEach(this::apply);
		}
	}
	
	private void apply(final ActionEffect effect) {
		final var currentActor = Optional.of(effect)
				.map(ActionEffect::action)
				.map(Action::actor)
				.orElseThrow(IllegalStateException::new);
		final var availableActors = this.actors.stream().filter(aim -> effect.applicable(currentActor, aim));
		
		this.getAffectedActors(effect.target(), availableActors)
				.forEach(actor -> actor.apply(effect));
	}
	
	private Stream<Actor> getAffectedActors(final Target target, final Stream<Actor> availableActors) {
		return switch (target.extremum()) {
			case MAXIMUM -> availableActors.max(target.comparing()).stream();
			case MINIMUM -> availableActors.min(target.comparing()).stream();
			case ALL -> availableActors;
		};
	}
	
	public boolean allPlayersAreDead() {
		return this.actors.stream().filter(actor -> actor.team() == Team.PLAYER).noneMatch(Actor::alive);
	}
	
	// TODO: need somehow distinguish the boss
	public boolean bossIsDead() {
		return this.actors.stream().filter(actor -> actor.team() == Team.BOSS).noneMatch(Actor::alive);
	}
}
