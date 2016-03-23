/*
 * NYPS 2020
 *
 * User: joebin
 * Date: 2016-03-10
 * Time: 18:12
 */
package se.caglabs.radbankir.impl;

import se.caglabs.radbankir.*;

import java.util.*;

/**
 * Alla metoder loggar (kastar state) ut automatiskt när de är klara.
 */
public class RadbankirFacadur implements IRadbankirFacadur {
    private static final long MAX_ACCOUNT_BALANCE = 1000000L;
    private static final int MAX_WITHDRAW_AMOUNT = 5000;

    private final Billbox billbox;
    private final AccountManager accountManager;
    private Account account;

    public RadbankirFacadur(Billbox billbox, AccountManager accountManager) {
        this.billbox = billbox;
        this.accountManager = accountManager;
    }

    @Override
    public void login(long accountNumber, int pinCode) throws RadbankirExceptionur {
        account = accountManager.login(accountNumber, pinCode);
    }

    @Override
    public List<Valuesur> withdraw(int amount) throws RadbankirExceptionur {
        if(account == null) {
            throw new RadbankirExceptionur("Ej inloggad");
        }

        if(amount > MAX_WITHDRAW_AMOUNT){
            throw new RadbankirExceptionur("Utag över 5000 kr");
        }

        if(account.getBalance() < amount ){
            throw new RadbankirExceptionur("För lite pengar på kontot");
        }

        List<Valuesur> withdrawnBills = billbox.withdraw(amount);
        account.setBalance(account.getBalance() - amount);
        return withdrawnBills;
    }

    @Override
    public long getBalance() throws RadbankirExceptionur {
        if(account == null) {
            throw new RadbankirExceptionur("Ej inloggad");
        }

        return account.getBalance();
    }

    @Override
    public void cancel() {
        account = null;
    }

    @Override
    public List<Valuesur> deposit(List<Valuesur> bills) throws RadbankirExceptionur {
        if(account == null) {
            throw new RadbankirExceptionur("Ej inloggad");
        }

        long totalDepositAmount = bills.stream().mapToLong(Valuesur::getNoteValue).sum();
        if( totalDepositAmount > MAX_ACCOUNT_BALANCE) {
            throw new RadbankirExceptionur("Insättning på " + totalDepositAmount + "kr kan ej göras då kontot kommer överstiga " + MAX_ACCOUNT_BALANCE + "kr");
        }

        List<Valuesur> returnedBills = billbox.deposit(bills);
        long actualDepositAmount = totalDepositAmount - returnedBills.stream().mapToLong(Valuesur::getNoteValue).sum();
        account.setBalance(account.getBalance() + actualDepositAmount);

        return returnedBills;
    }
}
