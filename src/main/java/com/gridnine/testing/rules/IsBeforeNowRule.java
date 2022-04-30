package com.gridnine.testing.rules;

import com.gridnine.testing.models.Flight;

import java.time.LocalDateTime;
import java.util.Objects;

public class IsBeforeNowRule implements Rule {
    private final RuleName name = RuleName.IS_BEFORE_NOW;
    @Override
    public boolean test(Flight flight) {
        return flight.getSegments()
                .get(0)
                .getDepartureDate()
                .isBefore(LocalDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IsBeforeNowRule that = (IsBeforeNowRule) o;
        return name == that.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
