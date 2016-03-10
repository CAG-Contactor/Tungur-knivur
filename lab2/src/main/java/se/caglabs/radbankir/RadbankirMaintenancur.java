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
        HUNDRED,
        TWOHUNDRED,
        FIVEHUNDRED,
        THOUSAND
    }

    void loadBills(int hundreds, int twohundreds, int fivehundreds, int thousands);
    Map<Valuesur, Integer> showMeTheMoney();


}
