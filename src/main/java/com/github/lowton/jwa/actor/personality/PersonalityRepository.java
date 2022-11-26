package com.github.lowton.jwa.actor.personality;

import com.github.lowton.jwa.actor.personality.dto.Personality;

import java.util.Optional;

public interface PersonalityRepository {

    Optional<Personality> getPersonality(String id);
}
