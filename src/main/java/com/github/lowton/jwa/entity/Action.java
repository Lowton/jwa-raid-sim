package com.github.lowton.jwa.entity;


import java.util.List;

public record Action(String id, String name, Actor actor, Target target, List<Effect> effects) {

    public static Action hit(final Actor actor) {
        return new Action("hit", "hit", actor, Target.maxHealth(), List.of(Effect.damage(actor.attack())));
    }
}
