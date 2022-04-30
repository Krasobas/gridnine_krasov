package com.gridnine.testing.rules;

import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.Segment;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class IsGroundTimeMoreRuleTest {

    @Test
    public void whenTestThenTrue() {
        LocalDateTime start = LocalDateTime.now().plusDays(3);
        Flight groundTimeMoreFive = new Flight(List.of(new Segment(start, start.plusHours(5)),
                new Segment(start.plusHours(15), start.plusHours(20))));
        assertTrue(new IsGroundTimeMoreRule(5).test(groundTimeMoreFive));
    }

    @Test
    public void whenTestThenFalse() {
        LocalDateTime start = LocalDateTime.now().plusDays(3);
        Flight normal = new Flight(List.of(new Segment(start, start.plusHours(5)),
                new Segment(start.plusHours(7), start.plusHours(10))));
        assertFalse(new IsGroundTimeMoreRule(5).test(normal));
    }
}