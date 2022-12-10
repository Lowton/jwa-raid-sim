package com.github.lowton.jwa.action;

import java.util.Optional;

import com.github.lowton.jwa.action.act.ActRepository;
import com.github.lowton.jwa.action.dto.Action;
import com.github.lowton.jwa.action.effect.EffectRepository;

public class ActionService {

    private final ActRepository actRepository;
    private final EffectRepository effectRepository;

    public ActionService(ActRepository actRepository, EffectRepository effectRepository) {
        this.actRepository = actRepository;
        this.effectRepository = effectRepository;
    }

    public Action getAction(String id) {
        final var act = this.actRepository.getAct(id).orElseThrow();
        final var effects = act.effects().stream()
                .map(this.effectRepository::getEffectById)
                .flatMap(Optional::stream)
                .toList();
        return new Action(act, effects);
    }
}
