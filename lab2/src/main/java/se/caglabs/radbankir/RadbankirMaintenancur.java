/*
 * NYPS 2020
 *
 * User: joebin
 * Date: 2016-03-10
 * Time: 18:21
 */
package se.caglabs.radbankir;

import java.util.*;

/**
 *
 */
public interface RadbankirMaintenancur {

    public enum Valuesur {
        HUNDRED(100),
        TWOHUNDRED(200),
        FIVEHUNDRED(500),
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
    }

    int loadBills(Valuesur valuesur, int antal);

    Map<Valuesur, Integer> showMeTheMoney();



}
