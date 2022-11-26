package com.github.lowton.jwa.actor.resistance;

import com.github.lowton.jwa.actor.resistance.dto.Resistance;

import java.util.Map;
import java.util.Optional;

public class DummyResistanceRepository implements ResistanceRepository {

    private final Map<String, Resistance> resistanceMap;

    public DummyResistanceRepository() {
        this.resistanceMap = Map.of(
                "tor", Resistance.builder("tor").build(),
                "goat", Resistance.builder("goat")
                        .critReduction(50)
                        .distraction(25)
                        .dot(50)
                        .rend( 50)
                        .stun( 90)
                        .vulnerable(50)
                        .build()
        );
    }

    @Override
    public Optional<Resistance> getResistanceById(String id) {
        return Optional.ofNullable(resistanceMap.get(id));
    }
}
