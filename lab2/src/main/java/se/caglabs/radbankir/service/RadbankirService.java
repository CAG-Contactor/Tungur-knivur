/*
 * NYPS 2020
 *
 * User: joebin
 * Date: 2016-03-10
 * Time: 18:12
 */
package se.caglabs.radbankir.service;

import se.caglabs.radbankir.exception.AccountLockedException;
import se.caglabs.radbankir.exception.AccountNotFoundException;
import se.caglabs.radbankir.exception.LoginFailedException;
import se.caglabs.radbankir.exception.RadbankirExceptionur;
import se.caglabs.radbankir.model.Account;
import se.caglabs.radbankir.model.Valuesur;

import java.util.*;

public class RadbankirService implements IRadbankirService {
    private static final long MAX_ACCOUNT_BALANCE = 1000000L;
    private static final int MAX_WITHDRAW_AMOUNT = 5000;

    private final IBillbox billbox;
    private final IAccountManager accountManager;
    private Long accountNumber = null;
    private boolean authenticated = false;

    public RadbankirService(IBillbox billbox, IAccountManager accountManager) {
        this.billbox = billbox;
        this.accountManager = accountManager;
    }

    @Override
    public void login(long accountNumber, int pinCode) throws LoginFailedException, AccountLockedException, AccountNotFoundException {
        this.accountNumber = accountNumber;
        accountManager.login(accountNumber, pinCode);
        authenticated = true;
    }

    @Override
    public List<Valuesur> withdraw(int amount) throws RadbankirExceptionur {
        if(!authenticated) {
            throw new RadbankirExceptionur("Not authenticated");
        }

        if(amount > MAX_WITHDRAW_AMOUNT){
            throw new RadbankirExceptionur("Can not withdraw more than " + MAX_WITHDRAW_AMOUNT + "kr");
        }

        Account account = accountManager.findAccountByAccountNumber(accountNumber);
        if(account.getBalance() < amount ){
            throw new RadbankirExceptionur("Not enough money on the account");
        }

        List<Valuesur> withdrawnBills = billbox.withdraw(amount);
        account.setBalance(account.getBalance() - amount);
        return withdrawnBills;
    }

    @Override
    public long getBalance() throws RadbankirExceptionur {
        if(!authenticated) {
            throw new RadbankirExceptionur("Not authenticated");
        }

        Account account = accountManager.findAccountByAccountNumber(accountNumber);
        return account.getBalance();
    }

    @Override
    public void cancel() {
        this.accountManager.cancel(accountNumber);
        this.accountNumber = null;
    }

    @Override
    public List<Valuesur> deposit(List<Valuesur> bills) throws RadbankirExceptionur {
        if(!authenticated) {
            throw new RadbankirExceptionur("Not authenticated");
        }

        long totalDepositAmount = bills.stream().mapToLong(Valuesur::getNoteValue).sum();
        if( totalDepositAmount > MAX_ACCOUNT_BALANCE) {
            throw new RadbankirExceptionur("Couldn't deposit " + totalDepositAmount + "kr because this would violate the max account balance of " + MAX_ACCOUNT_BALANCE + "kr");
        }

        List<Valuesur> returnedBills = billbox.deposit(bills);
        long actualDepositAmount = totalDepositAmount - returnedBills.stream().mapToLong(Valuesur::getNoteValue).sum();

        Account account = accountManager.findAccountByAccountNumber(accountNumber);
        account.setBalance(account.getBalance() + actualDepositAmount);

        return returnedBills;
    }
}
