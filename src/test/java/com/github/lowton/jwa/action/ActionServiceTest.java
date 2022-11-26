package com.github.lowton.jwa.action;

import com.github.lowton.jwa.action.act.ActRepository;
import com.github.lowton.jwa.action.dto.Action;
import com.github.lowton.jwa.action.effect.EffectRepository;
import com.github.lowton.jwa.action.effect.dto.Effect;
import com.github.lowton.jwa.action.effect.dto.EffectAim;
import com.github.lowton.jwa.action.effect.dto.Impact;
import com.github.lowton.jwa.entity.Target;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.github.lowton.jwa.test.TestFactory.act;
import static com.github.lowton.jwa.test.TestFactory.effect;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ActionServiceTest {

    private ActRepository actRepository;
    private EffectRepository effectRepository;

    private ActionService actionService;

    @BeforeEach
    void setUp() {
        this.actRepository = mock(ActRepository.class);
        this.effectRepository = mock(EffectRepository.class);
        this.actionService = new ActionService(this.actRepository, this.effectRepository);
    }

    @Test
    void getAction() {
        final var effect = effect("test-effect");
        when(effectRepository.getEffectById(anyString())).thenReturn(Optional.of(effect));
        when(actRepository.getAct(anyString())).thenReturn(Optional.of(act("test-act", effect.name())));

        final var action = this.actionService.getAction("test-act");

        assertThat(action)
                .returns("test-act", from(Action::getId))
                .returns("test-act", from(Action::getName))
                .returns(Target.maxHealth(), from(Action::getTarget))
                .returns(null, from(Action::getActor));
        assertThat(action.getEffects())
                .singleElement()
                .returns("test-effect", from(Effect::id))
                .returns("test-effect", from(Effect::name))
                .returns(EffectAim.OPPONENT, from(Effect::aim))
                .returns(Impact.HEALTH_DECREASE, from(Effect::impact))
                .returns(1F, from(Effect::power));
    }
}