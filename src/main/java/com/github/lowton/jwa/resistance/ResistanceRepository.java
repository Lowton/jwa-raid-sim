package com.github.lowton.jwa.resistance;

import com.github.lowton.jwa.resistance.dto.Resistance;

import java.util.Optional;

public interface ResistanceRepository {
    Optional<Resistance> getResistanceById(String id);
}
