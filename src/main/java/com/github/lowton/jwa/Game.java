package com.github.lowton.jwa;

import com.github.lowton.jwa.actor.ActorService;
import com.github.lowton.jwa.actor.dto.Actor;
import com.github.lowton.jwa.actor.dto.StatBoosts;
import com.github.lowton.jwa.actor.dto.Team;

import java.util.Set;

public class Game {

    private ActorService actorService;

    public Game(ActorService actorService) {
        this.actorService = actorService;
    }

    public void start() {
        System.out.println("Starting battle");
        final var actors = this.prepareActors();
        // добавить сущность раунда и хода

        System.out.println("Finish");
    }

    private Set<Actor> prepareActors() {
        return Set.of(
                this.actorService.getActor("tor", 30, StatBoosts.empty(), Team.PLAYER),
                this.actorService.getActor("goat", 30, StatBoosts.empty(), Team.BOSS)
        );
    }
}
