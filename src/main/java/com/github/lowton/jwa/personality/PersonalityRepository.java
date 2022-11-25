package com.github.lowton.jwa.personality;

import com.github.lowton.jwa.personality.dto.Personality;

import java.util.Optional;

public interface PersonalityRepository {

    Optional<Personality> getPersonality(String id);
}
