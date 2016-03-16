package se.caglabs.icecuke.lab1;

import java.util.Arrays;

public class Calculator {

    private Double display;

    public Calculator() {
        clear();
    }

    public Calculator clear() {
        display = 0.0;
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
        // Recalculate the current average
        display = Arrays.asList(values)
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum() / values.length;
        return this;
    }
}
