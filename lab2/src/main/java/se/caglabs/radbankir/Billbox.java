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
    private Map<RadbankirMaintenancur.Valuesur, Integer> bills = new HashMap<>();
    {
        bills.put(RadbankirMaintenancur.Valuesur.HUNDRED, 50);
        bills.put(RadbankirMaintenancur.Valuesur.TWOHUNDRED, 50);
        bills.put(RadbankirMaintenancur.Valuesur.FIVEHUNDRED, 50);
        bills.put(RadbankirMaintenancur.Valuesur.THOUSAND, 50);
    }
}
