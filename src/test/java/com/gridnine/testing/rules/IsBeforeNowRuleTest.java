package com.gridnine.testing.rules;

import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.Segment;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class IsBeforeNowRuleTest {

    @Test
    public void whenTestThenTrue() {
        LocalDateTime start = LocalDateTime.now().plusDays(3);
        Flight beforeNow = new Flight(List.of(new Segment(start.minusDays(4), start.plusHours(5))));
        assertTrue(new IsBeforeNowRule().test(beforeNow));
    }

    @Test
    public void whenTestThenFalse() {
        LocalDateTime start = LocalDateTime.now().plusDays(3);
        Flight normal = new Flight(List.of(new Segment(start, start.plusHours(5)),
                new Segment(start.plusHours(7), start.plusHours(10))));
        assertFalse(new IsBeforeNowRule().test(normal));
    }
}