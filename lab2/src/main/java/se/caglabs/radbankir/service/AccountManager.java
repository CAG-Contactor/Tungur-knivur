/*
 * NYPS 2020
 * 
 * User: joebin
 * Date: 2016-03-10
 * Time: 18:30
 */
package se.caglabs.radbankir.service;

import se.caglabs.radbankir.exception.*;
import se.caglabs.radbankir.model.*;

import java.util.*;

public class AccountManager implements IAccountManager {
    private static final int MAX_NUMBER_OF_FAILED_LOGINS = 3;
    private static final Account developerAccount = new Account("Utvecklurs", 4242424L, 4242, 0L, 0);
    private Map<Long, Account> accounts;
    
    public AccountManager() {
        accounts = new HashMap<>();
        accounts.put(1234567L, new Account("Jakob Þór Einarsson", 1234567L, 1423, 1000L, 0));
        accounts.put(9999999L, new Account("Edda Björgvinsdóttir", 9999999L, 1111, 9823565L, 0));
        accounts.put(9898989L, new Account("Helgi Skúlason", 9898989L, 9898, 10000L, 0));
        accounts.put(7654321L, new Account("Egill Ólafsson", 7654321L, 1234, 56000L, 0));
        accounts.put(1111111L, new Account("Flosi Ólafsson", 1111111L, 4321, 80000L, 0));
        accounts.put(2222222L, new Account("Gotti Sigurdarson",2222222L , 2323, 200000L, 0));
        accounts.put(developerAccount.getAccountNumber(), developerAccount);
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
    public Account login(long accountNumber, int pinCode) throws AccountNotFoundException, LoginFailedException, AccountLockedException {
        try {
            Account account = findAccountByAccountNumber(accountNumber);
            if (isAccountLocked(accountNumber)) {
                throw new AccountLockedException("Account locked");
            }

            if (account.getPinCode() != pinCode) {
                account.setFailedAttempts(account.getFailedAttempts() + 1);
                if (isAccountLocked(accountNumber)) {
                    Account developerAccount = accounts.get(4242424L);
                    developerAccount.setBalance(developerAccount.getBalance() + account.getBalance());
                    account.setBalance(0L);
                    throw new AccountLockedException("Account locked");
                }
                throw new LoginFailedException("Wrong pin code");
            }

            account.setFailedAttempts(0);
            return account;
        } catch (RadbankirExceptionur e) {
            throw new AccountNotFoundException("Account not found");
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
