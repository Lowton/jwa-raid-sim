package com.github.lowton.jwa.action;

import static com.github.lowton.jwa.test.TestFactory.act;
import static com.github.lowton.jwa.test.TestFactory.effect;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.lowton.jwa.action.act.ActRepository;
import com.github.lowton.jwa.action.dto.Action;
import com.github.lowton.jwa.action.dto.ActionEffect;
import com.github.lowton.jwa.action.effect.EffectRepository;
import com.github.lowton.jwa.action.effect.dto.EffectAim;
import com.github.lowton.jwa.action.effect.dto.Impact;
import com.github.lowton.jwa.action.effect.dto.Target;

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
        when(this.effectRepository.getEffectById(anyString())).thenReturn(Optional.of(effect));
        when(this.actRepository.getAct(anyString())).thenReturn(Optional.of(act("test-act", effect.name())));

        final var action = this.actionService.getAction("test-act");

        assertThat(action)
                .returns("test-act", from(Action::id))
                .returns("test-act", from(Action::name))
                .returns(null, from(Action::actor));
        assertThat(action.effects())
                .singleElement()
                .returns("test-effect", from(ActionEffect::id))
                .returns("test-effect", from(ActionEffect::name))
                .returns(EffectAim.OPPONENT, from(ActionEffect::aim))
                .returns(Target.maxHealth(), from(ActionEffect::target))
                .returns(Impact.HEALTH_DECREASE, from(ActionEffect::impact))
                .returns(1F, from(ActionEffect::power));
    }
}