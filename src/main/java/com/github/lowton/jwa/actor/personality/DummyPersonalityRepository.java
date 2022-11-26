package com.github.lowton.jwa.actor.personality;

import com.github.lowton.jwa.actor.personality.dto.Personality;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DummyPersonalityRepository implements PersonalityRepository {
    private final Map<String, Personality> personalityMap;

    public DummyPersonalityRepository() {
        this.personalityMap = Map.of(
                "tor", new Personality("tor", "Tor", 3000, 500, 125, 0, 30, List.of("simple-attack")),
                "goat", new Personality("goat", "Goat", 5000, 200, 120, 20, 5, List.of("simple-attack"))
        );
    }

    @Override
    public Optional<Personality> getPersonality(String id) {
        return Optional.ofNullable(this.personalityMap.get(id));
    }
}
