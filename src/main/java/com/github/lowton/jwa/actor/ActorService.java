package com.github.lowton.jwa.actor;

import com.github.lowton.jwa.action.ActionService;
import com.github.lowton.jwa.actor.dto.Actor;
import com.github.lowton.jwa.actor.dto.Team;
import com.github.lowton.jwa.personality.PersonalityRepository;
import com.github.lowton.jwa.resistance.ResistanceRepository;

import java.util.Objects;

import static java.util.stream.Collectors.toMap;

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

    public Actor getActor(String id, int level, Team team) {
        final var personality = this.personalityRepository.getPersonality(id).orElseThrow();
        final var resistance = this.resistanceRepository.getResistanceById(id).orElseThrow();
        final var moves = personality.getMoves();
        final var actionMap = moves.stream()
                .filter(Objects::nonNull)
                .map(this.actionService::getAction)
                .collect(toMap(move -> moves.indexOf(move) + 1, move -> move));

        return new Actor(personality, level, resistance.getResistanceMap(), team, actionMap);
    }
}
