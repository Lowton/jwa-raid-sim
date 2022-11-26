package com.github.lowton.jwa.action.act;

import com.github.lowton.jwa.action.act.dto.Act;

import java.util.Optional;

public interface ActRepository {
    Optional<Act> getAct(String id);
}
