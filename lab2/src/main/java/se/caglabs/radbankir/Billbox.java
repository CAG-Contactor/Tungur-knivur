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
    private static final int MAX_BILLS_PER_VALUESUR = 100;

    private Map<Valuesur, Integer> bills = new HashMap<>();

    public Billbox() {
        empty();
    }

    public void empty() {
        bills.put(Valuesur.HUNDRED, 0);
        bills.put(Valuesur.TWOHUNDRED, 0);
        bills.put(Valuesur.FIVEHUNDRED, 0);
        bills.put(Valuesur.THOUSAND, 0);
    }

    public List<Valuesur> withdraw(final int amount) throws RadbankirExceptionur {
        List<Valuesur> withdrawnBills = new ArrayList<>();
        int withdrawnAmount = withdraw(withdrawnBills, amount);
        if( withdrawnAmount != 0 ) {
            deposit(withdrawnBills);
            throw new RadbankirExceptionur("Det fanns inte sedlar i maskinen för att kunna ta ut summan " + amount);
        }

        return withdrawnBills;
    }

    private int withdraw(List<Valuesur> withdrawnBills, int amountLeftToWithdraw) {
        for(Valuesur valuesur : Valuesur.values()) {
            if( amountLeftToWithdraw >= valuesur.getNoteValue() && bills.get(valuesur) > 0) {
                withdrawnBills.add(valuesur);
                bills.put(valuesur, bills.get(valuesur) - 1);
                return withdraw(withdrawnBills, amountLeftToWithdraw - valuesur.getNoteValue());
            }
        }

        return amountLeftToWithdraw;
    }

    public List<Valuesur> deposit(List<Valuesur> bills) throws RadbankirExceptionur {
        List<Valuesur> billsToReturn = new ArrayList<>();

        for( Valuesur valuesur : bills) {
            if (this.bills.get(valuesur) +  1 > MAX_BILLS_PER_VALUESUR) {
                billsToReturn.add(valuesur);
            } else {
                this.bills.put(valuesur, this.bills.get(valuesur) + 1);
            }
        }

        return billsToReturn;
    }

    public List<Valuesur> deposit(Valuesur valuesur, int count) throws RadbankirExceptionur {
        List<Valuesur> valuesurs = new ArrayList<>();
        for( int i = 0; i < count; i++) {
            valuesurs.add(valuesur);
        }
        return deposit(valuesurs);
    }
}
