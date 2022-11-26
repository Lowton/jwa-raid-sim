package com.github.lowton.jwa.actor;

import com.github.lowton.jwa.action.ActionService;
import com.github.lowton.jwa.actor.dto.Actor;
import com.github.lowton.jwa.actor.dto.StatBoosts;
import com.github.lowton.jwa.actor.dto.Team;
import com.github.lowton.jwa.actor.personality.PersonalityRepository;
import com.github.lowton.jwa.actor.resistance.ResistanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.github.lowton.jwa.test.TestFactory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ActorServiceTest {

    private PersonalityRepository personalityRepository;
    private ResistanceRepository resistanceRepository;
    private ActionService actionService;

    private ActorService actorService;

    @BeforeEach
    void setUp() {
        this.personalityRepository = mock(PersonalityRepository.class);
        this.resistanceRepository = mock(ResistanceRepository.class);
        this.actionService = mock(ActionService.class);

        this.actorService = new ActorService(this.personalityRepository, this.resistanceRepository, this.actionService);
    }

    @Test
    void getActor() {
        when(this.personalityRepository.getPersonality(anyString()))
                .thenReturn(Optional.of(personality("test-actor", "test-action")));
        when(this.resistanceRepository.getResistanceById(anyString()))
                .thenReturn(Optional.of(resistance("test-actor").build()));
        when(this.actionService.getAction(anyString()))
                .thenReturn(action("test-action", "test-effect"));

        final var boosts = new StatBoosts(1,2,3);

        final var actor = this.actorService.getActor("test-actor", 1, boosts, Team.PLAYER);

        // TODO: finish assertions after class is complete
        assertThat(actor).isNotNull()
                .returns(100, from(Actor::health))
                .returns(10, from(Actor::attack))
                .returns(1, from(Actor::speed));
    }
}