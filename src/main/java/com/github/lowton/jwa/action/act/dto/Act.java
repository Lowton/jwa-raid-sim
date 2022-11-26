package com.github.lowton.jwa.action.act.dto;


import com.github.lowton.jwa.entity.Target;

import java.util.List;

public record Act(String id, String name, Target target, List<String> effects) {
}
