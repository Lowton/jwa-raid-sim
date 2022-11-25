package com.github.lowton.jwa.action.dto;


import com.github.lowton.jwa.effect.dto.Effect;
import com.github.lowton.jwa.entity.Target;

import java.util.List;

public record Action(String id, String name, Target target, List<Effect> effects) {
}
