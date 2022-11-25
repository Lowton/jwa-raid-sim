package com.github.lowton.jwa.personality;

import com.github.lowton.jwa.personality.dto.Personality;

import java.util.Map;
import java.util.Optional;

public class DummyPersonalityRepository implements PersonalityRepository {
    private final Map<String, Personality> personalityMap;

    public DummyPersonalityRepository() {
        this.personalityMap = Map.of(
                "tor", new Personality("tor", "Tor", 3000, 500, 125, 0, 30, "simple-attack", null, null, null),
                "goat", new Personality("goat", "Goat", 5000, 200, 120, 20, 5, "simple-attack", null, null, null)
        );
    }

    @Override
    public Optional<Personality> getPersonality(String id) {
        return Optional.ofNullable(this.personalityMap.get(id));
    }
}
