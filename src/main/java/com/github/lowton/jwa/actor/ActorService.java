package com.github.lowton.jwa.actor;

import com.github.lowton.jwa.action.ActionService;
import com.github.lowton.jwa.actor.dto.Actor;
import com.github.lowton.jwa.actor.dto.StatBoosts;
import com.github.lowton.jwa.actor.dto.Team;
import com.github.lowton.jwa.actor.personality.PersonalityRepository;
import com.github.lowton.jwa.actor.resistance.ResistanceRepository;

import java.util.Objects;

public class ActorService {

    private final PersonalityRepository personalityRepository;
    private final ResistanceRepository resistanceRepository;
    private final ActionService actionService;

    public ActorService(PersonalityRepository personalityRepository,
                        ResistanceRepository resistanceRepository,
                        ActionService actionService) {
        this.personalityRepository = personalityRepository;
        this.resistanceRepository = resistanceRepository;
        this.actionService = actionService;
    }

    public Actor getActor(String id, int level, StatBoosts boosts, Team team) {
        final var personality = this.personalityRepository.getPersonality(id).orElseThrow();
        final var resistance = this.resistanceRepository.getResistanceById(id).orElseThrow();
        final var actions = personality.actions().stream()
                .filter(Objects::nonNull)
                .map(this.actionService::getAction)
                .toList();

        return new Actor(personality, level, resistance.getResistanceMap(), boosts, team, actions);
    }
}
