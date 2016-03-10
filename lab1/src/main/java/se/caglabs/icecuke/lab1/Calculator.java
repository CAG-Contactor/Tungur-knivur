package se.caglabs.icecuke.lab1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculator {

    private Double display;
    private List<Double> memory;

    public Calculator() {
        clear();
    }

    public Calculator clear() {
        display = 0.0;
        memory = new ArrayList<>();
        return this;
    }

    public Calculator add(double value) {
        display += value;
        return this;
    }

    public Calculator subtract(double value) {
        display -= value;
        return this;
    }

    public Calculator multiply(double value) {
        display *= value;
        return this;
    }

    public double getDisplay() {
        return display;
    }

    public Calculator average(Double... values) {
        Collections.addAll(memory, values);

        // Recalculate the current average
        display = memory
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum() / values.length;
        return this;
    }
}
