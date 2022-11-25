package com.github.lowton.jwa.resistance.dto;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public record Resistance(
        String id,
        Integer critReduction,
        Integer distraction,
        Integer dot,
        Integer rend,
        Integer speedDecrease,
        Integer stun,
        Integer swapPrevention,
        Integer taunt,
        Integer vulnerable) {

    // TODO: think of replacing with more clear getting and maybe storing
    public Map<ResistanceType, Integer> getResistanceMap() {
        final var resistanceMap = new HashMap<ResistanceType, Integer>();
        for (var field : this.getClass().getDeclaredFields()) {
            if (!"id".equals(field.getName())) {
                try {
                    final var resistancePower = (Integer) field.get(this);
                    if (resistancePower > 0) {
                        resistanceMap.put(this.mapToResistanceType(field), resistancePower);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return resistanceMap;
    }

    private ResistanceType mapToResistanceType(Field field) {
        return ResistanceType.of(field.getName());
    }
}
