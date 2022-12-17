package com.github.lowton.jwa.raid;

import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.github.lowton.jwa.action.dto.Action;

public class MovementQueue {
	
	private final Collection<Action> actions;
	
	public MovementQueue(final Collection<Action> actions) {
		this.actions = new ArrayList<>(actions);
	}
	
	public Optional<Action> next() {
		final var currentAction = this.actions.stream().max(comparing(action -> action.actor().speed()));
		currentAction.ifPresent(action -> this.actions.removeIf(a -> action.actor().equals(a.actor())));
		return currentAction;
	}
	
	public boolean isNotEmpty() {
		return !this.actions.isEmpty();
	}
}
