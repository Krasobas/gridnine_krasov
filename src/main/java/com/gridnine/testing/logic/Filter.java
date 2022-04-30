package com.gridnine.testing.logic;

import com.gridnine.testing.models.Flight;
import com.gridnine.testing.rules.Rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Filter {
    private List<Rule> rules;

    public Filter(Rule... rules) {
        this.rules = new ArrayList<>(Arrays.asList(rules));
    }

    public List<Rule> getRules() {
        return this.rules;
    }

    public List<Flight> filter(List<Flight> flights) {
        return flights.stream().filter(x -> {
            boolean check = true;
            for (Rule rule : rules) {
                if (rule.test(x)) {
                    check = false;
                    break;
                }
            }
            return check;
        }).collect(Collectors.toList());
    }

    public boolean reset(Rule... rules) {
        this.rules.clear();
        return this.rules.addAll(Arrays.asList(rules));
    }

    public boolean add(Rule... rules) {
        return this.rules.addAll(Arrays.asList(rules));
    }

    public boolean remove(Rule... rules) {
        return this.rules.removeAll(Arrays.asList(rules));
    }
}
