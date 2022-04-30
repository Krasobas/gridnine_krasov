package com.gridnine.testing;

import com.gridnine.testing.logic.Filter;
import com.gridnine.testing.logic.FlightBuilder;
import com.gridnine.testing.models.Flight;
import com.gridnine.testing.rules.IsBeforeDepartureRule;
import com.gridnine.testing.rules.IsBeforeNowRule;
import com.gridnine.testing.rules.IsGroundTimeMoreRule;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        Filter filter = new Filter(new IsBeforeNowRule());
        System.out.println("All flights before filtering: ");
        flights.forEach(System.out::println);
        System.out.println("Flights after IsBeforeNow filter:");
        filter.filter(flights).forEach(System.out::println);
        System.out.println("Flights after IsBeforeDeparture filter:");
        filter.reset(new IsBeforeDepartureRule());
        filter.filter(flights).forEach(System.out::println);
        System.out.println("Flights after IsGroundTimeMore (2 hours) filter:");
        filter.reset(new IsGroundTimeMoreRule(2));
        filter.filter(flights).forEach(System.out::println);
        System.out.println("Flights after all filters:");
        filter.add(new IsBeforeDepartureRule(), new IsBeforeNowRule());
        filter.filter(flights).forEach(System.out::println);
    }
}
