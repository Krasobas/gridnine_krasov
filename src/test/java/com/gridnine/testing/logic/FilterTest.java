package com.gridnine.testing.logic;

import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.Segment;
import com.gridnine.testing.rules.IsBeforeDepartureRule;
import com.gridnine.testing.rules.IsBeforeNowRule;
import com.gridnine.testing.rules.IsGroundTimeMoreRule;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class FilterTest {

    @Test
    public void whenAddThenContainsTrue() {
        Filter filter = new Filter(new IsBeforeDepartureRule());
        IsBeforeNowRule beforeNow = new IsBeforeNowRule();
        filter.add(beforeNow);
        assertTrue(filter.getRules().contains(beforeNow));
    }

    @Test
    public void whenResetThenEqualsTrue() {
        Filter filter = new Filter(new IsBeforeDepartureRule());
        IsBeforeNowRule beforeNow = new IsBeforeNowRule();
        filter.reset(beforeNow);
        assertEquals(filter.getRules().get(0), beforeNow);
    }


    @Test
    public void whenRemoveThenEmptyTrue() {
        Filter filter = new Filter(new IsBeforeDepartureRule());
        filter.remove(new IsBeforeDepartureRule());
        assertTrue(filter.getRules().isEmpty());
    }

    @Test
    public void whenAllFiltersThenOnlyNormal() {
        LocalDateTime start = LocalDateTime.now().plusDays(3);
        Flight normal = new Flight(List.of(new Segment(start, start.plusHours(5)),
                new Segment(start.plusHours(7), start.plusHours(10))));
        Flight beforeNow = new Flight(List.of(new Segment(start.minusDays(4), start.plusHours(5))));
        Flight beforeDeparture = new Flight(List.of(new Segment(start, start.minusHours(5))));
        Flight groundTimeMoreFive = new Flight(List.of(new Segment(start, start.plusHours(5)),
                new Segment(start.plusHours(15), start.plusHours(20))));

        List<Flight> actual = List.of(normal, beforeNow, beforeDeparture, groundTimeMoreFive);
        List<Flight> expected = List.of(normal);
        Filter all = new Filter(new IsBeforeNowRule(),
                new IsBeforeDepartureRule(),
                new IsGroundTimeMoreRule(5));
        assertEquals(all.filter(actual), expected);
    }
}