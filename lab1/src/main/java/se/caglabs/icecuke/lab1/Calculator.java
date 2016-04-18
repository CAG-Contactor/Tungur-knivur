package se.caglabs.icecuke.lab1;

import java.util.*;
import java.math.*;

public class Calculator {

    private BigDecimal display;

    public Calculator() {
        clear();
    }

    public Calculator clear() {
        display = BigDecimal.valueOf(0.0);
        return this;
    }

    public Calculator add(BigDecimal value) {
        display = display.add(value);
        return this;
    }

    public Calculator subtract(BigDecimal value) {
        display = display.subtract(value);
        return this;
    }

    public Calculator multiply(BigDecimal value) {
        display = display.multiply(value);
        return this;
    }

    public BigDecimal getDisplay() {
        return display;
    }

    public Calculator average(List<BigDecimal> values) {
        // Recalculate the current average
        display = values.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(values.size()), 5, BigDecimal.ROUND_HALF_UP);
        return this;
    }
}
