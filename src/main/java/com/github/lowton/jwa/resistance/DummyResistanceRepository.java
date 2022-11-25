package com.github.lowton.jwa.resistance;

import com.github.lowton.jwa.resistance.dto.Resistance;

import java.util.Map;
import java.util.Optional;

public class DummyResistanceRepository implements ResistanceRepository {

    private final Map<String, Resistance> resistanceMap;

    public DummyResistanceRepository() {
        this.resistanceMap = Map.of(
                "tor", new Resistance("tor", 0, 0,0,0,0,0,0,0,0),
                "goat", new Resistance("goat", 50,25,50, 50, 0, 90, 0, 0, 50)
        );
    }

    @Override
    public Optional<Resistance> getResistanceById(String id) {
        return Optional.ofNullable(resistanceMap.get(id));
    }
}
