package com.github.lowton.jwa.actor.resistance;

import com.github.lowton.jwa.actor.resistance.dto.Resistance;

import java.util.Optional;

public interface ResistanceRepository {
    Optional<Resistance> getResistanceById(String id);
}
