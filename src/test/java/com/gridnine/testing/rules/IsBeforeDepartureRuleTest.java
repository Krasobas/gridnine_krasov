package com.gridnine.testing.rules;

import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.Segment;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class IsBeforeDepartureRuleTest {

    @Test
    public void whenTestThenTrue() {
        LocalDateTime start = LocalDateTime.now().plusDays(3);
        Flight beforeDeparture = new Flight(List.of(new Segment(start, start.minusHours(5))));
        assertTrue(new IsBeforeDepartureRule().test(beforeDeparture));
    }

    @Test
    public void whenTestThenFalse() {
        LocalDateTime start = LocalDateTime.now().plusDays(3);
        Flight normal = new Flight(List.of(new Segment(start, start.plusHours(5)),
                new Segment(start.plusHours(7), start.plusHours(10))));
        assertFalse(new IsBeforeDepartureRule().test(normal));
    }
}