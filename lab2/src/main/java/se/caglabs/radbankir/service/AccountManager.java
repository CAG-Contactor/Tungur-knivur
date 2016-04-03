/*
 * NYPS 2020
 * 
 * User: joebin
 * Date: 2016-03-10
 * Time: 18:30
 */
package se.caglabs.radbankir.service;

import se.caglabs.radbankir.exception.RadbankirExceptionur;
import se.caglabs.radbankir.model.Account;

import java.util.HashMap;
import java.util.Map;

public class AccountManager implements IAccountManager {
    private static final int MAX_NUMBER_OF_FAILED_LOGINS = 3;
    private Map<Long, Account> accounts;

    public AccountManager() {
        accounts = new HashMap<>();
        accounts.put(1234567L, new Account("Jakob Þór Einarsson", 1234567L, 1423, 1000L, 0));
        accounts.put(9999999L, new Account("Edda Björgvinsdóttir", 9999999L, 1111, 9823565L, 0));
        accounts.put(9898989L, new Account("Helgi Skúlason", 9898989L, 9898, 10000L, 0));
        accounts.put(7654321L, new Account("Egill Ólafsson", 7654321L, 1234, 56000L, 0));
        accounts.put(1111111L, new Account("Flosi Ólafsson", 1111111L, 4321, 80000L, 0));
        accounts.put(2222222L, new Account("Gotti Sigurdarson", 2222222L, 2323, 200000L, 0));
        accounts.put(4242424L, new Account("Utvecklurs", 4242424L, 4242, 0L, 0));
    }

    @Override
    public Account findAccountByAccountNumber(final long accountNumber) throws RadbankirExceptionur {
        return accounts
                .values()
                .stream()
                .filter(a -> a.getAccountNumber() == accountNumber)
                .findFirst()
                .orElseThrow(() -> new RadbankirExceptionur("Kunde inte hitta konto med kontonummer " + accountNumber));
    }

    @Override
    public boolean isAccountLocked(long accountNumber) throws RadbankirExceptionur {
        return findAccountByAccountNumber(accountNumber).getFailedAttempts() >= MAX_NUMBER_OF_FAILED_LOGINS;
    }

    @Override
    public Account login(long accountNumber, int pinCode) throws RadbankirExceptionur {
        Account account = findAccountByAccountNumber(accountNumber);
        if( isAccountLocked(accountNumber)) {
            throw new RadbankirExceptionur("Kontot är låst");
        } else if (account.getPinCode() == pinCode) {
            account.setFailedAttempts(0);
            return account;
        } else {
            account.setFailedAttempts(account.getFailedAttempts() + 1);
            throw new RadbankirExceptionur("Felaktig pinkod användes");
        }
    }

    @Override
    public void cancel(final long accountNumber) {
        try {
            Account account = findAccountByAccountNumber(accountNumber);
            account.setFailedAttempts(0);
        } catch (Exception e) {
            // Ignore
        }
    }
}
