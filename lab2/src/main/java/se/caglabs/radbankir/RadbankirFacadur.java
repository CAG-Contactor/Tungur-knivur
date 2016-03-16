/*
 * NYPS 2020
 *
 * User: joebin
 * Date: 2016-03-10
 * Time: 18:12
 */
package se.caglabs.radbankir;

import java.util.*;

/**
 * Alla metoder loggar (kastar state) ut automatiskt när de är klara.
 */
public class RadbankirFacadur {
    private final Billbox billbox;
    private final Accounts accounts;

    private Accounts.Account account;

    public RadbankirFacadur(Billbox billbox) {
        this.billbox = billbox;
        this.accounts = new Accounts();
    }

    // Sätt upp initialt state
    public void login(long accountNumber, int pinCode) throws RadbankirExceptionur {
        Map<Long, Accounts.Account> accounts = this.accounts.getAccounts();
        if(accounts.get(accountNumber).getPinCode() != pinCode){
            account = null;
            throw new RadbankirExceptionur("Fel pincod");
        }
        account = accounts.get(accountNumber);
    }

    public Map<RadbankirMaintenancur.Valuesur, Integer> withDraw(int amount) throws RadbankirExceptionur {
        if(amount > 5000){
            throw new RadbankirExceptionur("Utag över 5000 kr");
        }
        if(account.getBalance() < amount ){
            throw new RadbankirExceptionur("För lite pengar på kontot");
        }
        Map<RadbankirMaintenancur.Valuesur, Integer> withDrawNotes = billbox.withDraw(amount);
        return withDrawNotes;
    }

    public void deposit(List<Integer> bills) throws RadbankirExceptionur {

    }

    public int getBalance() throws RadbankirExceptionur {
        return 0;
    }

    public void cancel() {

    }
}
