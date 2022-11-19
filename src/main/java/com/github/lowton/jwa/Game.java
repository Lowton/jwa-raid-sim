package com.github.lowton.jwa;

import com.github.lowton.jwa.entity.Actor;

import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toMap;

public class Game {
    public static void run() {
        System.out.println("Starting battle");
        new Game().start();
        System.out.println("Finish");
    }

    private void start() {
        final var actors = this.prepareActors();
        for(var i: new int[2]) {
            final var acts = actors.stream().collect(toMap(actor -> actor, Actor::act));
            actors.stream()
                    .filter(Actor::alive) // только живые
                    .sorted(comparingInt(Actor::speed).reversed())
                    .forEach(actor -> actors.stream().filter(Actor::alive)
                            .forEach(enemy -> enemy.apply(acts.get(actor))));
        }
    }

    private Set<Actor> prepareActors() {
        return Set.of(
                Actor.player("tor", "Tor", 3000, 500, 125)
                        .setMovesQueue(this.queueOf(1, 1)),
                Actor.boss("goat", "Goat", 5000, 200, 120)
                        .setMovesQueue(this.queueOf(1, 1, 1, 1))
        );
    }

    private Queue<Integer> queueOf(final Integer ...moves) {
        return Arrays.stream(moves).collect(toCollection(LinkedList::new));
    }
}
