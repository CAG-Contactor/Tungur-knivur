package se.caglabs.radbankir.model;

import java.util.Arrays;

/**
 * An enum defining all bill values
 */
public enum Valuesur {
    FIVEHUNDRED(500),
    HUNDRED(100),
    TWOHUNDRED(200),
    THOUSAND(1000);

    private final int noteValue;

    Valuesur(int noteValue) {
        this.noteValue = noteValue;
    }

    public static Valuesur from(int noteValue) {
        return Arrays.asList(values())
                .stream()
                .filter(v -> v.noteValue == noteValue)
                .findFirst()
                .get();
    }

    public int getNoteValue() {
        return noteValue;
    }
}
