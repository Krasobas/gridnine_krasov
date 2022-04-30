package com.gridnine.testing.rules;

import com.gridnine.testing.models.Flight;

import java.util.List;

public interface Rule {
    enum RuleName {
        IS_BEFORE_DEPARTURE,
        IS_BEFORE_NOW,
        IS_GROUND_TIME_MORE
    }

    boolean test(Flight flight);
}
