package com.gridnine.testing.rules;

import com.gridnine.testing.models.Flight;

import java.util.Objects;

public class IsBeforeDepartureRule implements Rule {
    private final RuleName name = RuleName.IS_BEFORE_DEPARTURE;

    @Override
    public boolean test(Flight flight) {
        return flight.getSegments()
                .stream()
                .anyMatch(sgm -> sgm.getArrivalDate().isBefore(sgm.getDepartureDate()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IsBeforeDepartureRule that = (IsBeforeDepartureRule) o;
        return name == that.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
