package com.gridnine.testing.rules;

import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.Segment;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

public class IsGroundTimeMoreRule implements Rule {
    private final RuleName name = RuleName.IS_GROUND_TIME_MORE;
    private int hours;

    public IsGroundTimeMoreRule(int hours) {
        this.hours = hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    @Override
    public boolean test(Flight flight) {
        long groundTime = 0;
        List<Segment> sgm = flight.getSegments();
        for (int i = 0; i < sgm.size() - 1; i++) {
            groundTime += ChronoUnit.HOURS.between(sgm.get(i).getArrivalDate(),
                                                   sgm.get(i + 1).getDepartureDate());
        }
        return groundTime > hours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IsGroundTimeMoreRule that = (IsGroundTimeMoreRule) o;
        return hours == that.hours && name == that.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hours);
    }
}
