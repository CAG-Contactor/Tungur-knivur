/*
 * NYPS 2020
 *
 * User: joebin
 * Date: 2016-03-10
 * Time: 18:31
 */
package se.caglabs.radbankir;

import lombok.*;

import java.util.*;

@Data
public class Billbox {
    private static final int MAX_AMOUNT = 100;

    private Map<RadbankirMaintenancur.Valuesur, Integer> bills = new HashMap<>();

    public Billbox() {
        empty();
    }

    public int addBills(RadbankirMaintenancur.Valuesur noteValue, int amount) {
        int total = bills.get(noteValue) + amount;
        int result = 0;

        if( total > MAX_AMOUNT) {
            result = (total - MAX_AMOUNT);
            total = MAX_AMOUNT;
        }

        bills.put(noteValue, total);
        return result;
    }

    public int getBills(RadbankirMaintenancur.Valuesur noteValue) {
        return bills.get(noteValue);
    }

    public void empty() {
        bills.put(RadbankirMaintenancur.Valuesur.HUNDRED, 0);
        bills.put(RadbankirMaintenancur.Valuesur.TWOHUNDRED, 0);
        bills.put(RadbankirMaintenancur.Valuesur.FIVEHUNDRED, 0);
        bills.put(RadbankirMaintenancur.Valuesur.THOUSAND, 0);
    }

    public Map<RadbankirMaintenancur.Valuesur,Integer> withDraw(int amount) throws RadbankirExceptionur {
        int countdown = amount;
        Map<RadbankirMaintenancur.Valuesur, Integer> result = new HashMap<>();

        result.put(RadbankirMaintenancur.Valuesur.THOUSAND, countdown / 1000);
        countdown -= (countdown / 1000) * 1000;

        result.put(RadbankirMaintenancur.Valuesur.FIVEHUNDRED, amount / 500);
        countdown -= (countdown / 500) * 500;

        result.put(RadbankirMaintenancur.Valuesur.FIVEHUNDRED, amount / 200);
        countdown -= (countdown / 200) * 200;

        result.put(RadbankirMaintenancur.Valuesur.FIVEHUNDRED, amount / 100);
        countdown -= (countdown / 100) * 100;

        if(countdown > 0) {
            throw new RadbankirExceptionur("Summan var inte delbar med antalet tillgängliga sedlar i bankomaten");
        }

        return result;
    }
}
