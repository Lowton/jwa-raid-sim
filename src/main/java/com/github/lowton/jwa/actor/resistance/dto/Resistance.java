package com.github.lowton.jwa.actor.resistance.dto;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNullElse;

public record Resistance(
        String id,
        int critReduction,
        int distraction,
        int dot,
        int rend,
        int speedDecrease,
        int stun,
        int swapPrevention,
        int taunt,
        int vulnerable) {

    public Resistance(Builder builder) {
        this(
                builder.id,
                requireNonNullElse(builder.critReduction, 0),
                requireNonNullElse(builder.distraction, 0),
                requireNonNullElse(builder.dot, 0),
                requireNonNullElse(builder.rend, 0),
                requireNonNullElse(builder.speedDecrease, 0),
                requireNonNullElse(builder.stun, 0),
                requireNonNullElse(builder.swapPrevention, 0),
                requireNonNullElse(builder.taunt, 0),
                requireNonNullElse(builder.vulnerable, 0)
        );
    }

    // TODO: think of replacing with more clear getting and maybe storing
    public Map<ResistanceType, Integer> getResistanceMap() {
        final var resistanceMap = new HashMap<ResistanceType, Integer>();
        for (var field : this.getClass().getDeclaredFields()) {
            if (!"id".equals(field.getName())) {
                try {
                    final var resistancePower = (Integer) field.get(this);
                    if (resistancePower > 0) {
                        resistanceMap.put(ResistanceType.of(field.getName()), resistancePower);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return resistanceMap;
    }

    public static Builder builder(String id) {
        return new Builder(id);
    }

    public static class Builder {
        final String id;
        Integer critReduction;
        Integer distraction;
        Integer dot;
        Integer rend;
        Integer speedDecrease;
        Integer stun;
        Integer swapPrevention;
        Integer taunt;
        Integer vulnerable;

        public Builder(String id) {
            this.id = id;
        }

        public Builder critReduction(Integer critReduction) {
            this.critReduction = critReduction;
            return this;
        }

        public Builder distraction(Integer distraction) {
            this.distraction = distraction;
            return this;
        }

        public Builder dot(Integer dot) {
            this.dot = dot;
            return this;
        }

        public Builder rend(Integer rend) {
            this.rend = rend;
            return this;
        }

        public Builder speedDecrease(Integer speedDecrease) {
            this.speedDecrease = speedDecrease;
            return this;
        }

        public Builder stun(Integer stun) {
            this.stun = stun;
            return this;
        }

        public Builder swapPrevention(Integer swapPrevention) {
            this.swapPrevention = swapPrevention;
            return this;
        }

        public Builder taunt(Integer taunt) {
            this.taunt = taunt;
            return this;
        }

        public Builder vulnerable(Integer vulnerable) {
            this.vulnerable = vulnerable;
            return this;
        }

        public Resistance build() {
            return new Resistance(this);
        }
    }
}
