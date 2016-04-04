package se.caglabs.radbankir.service;

import se.caglabs.radbankir.exception.AccountLockedException;
import se.caglabs.radbankir.exception.AccountNotFoundException;
import se.caglabs.radbankir.exception.LoginFailedException;
import se.caglabs.radbankir.exception.RadbankirExceptionur;
import se.caglabs.radbankir.model.Account;

/**
 * An interface describing an account manager for hand
 */
public interface IAccountManager {
    /**
     * Retrieves an account by its account number
     *
     * @param accountNumber the account number of the account to be retrieved
     * @return the account if found
     * @throws RadbankirExceptionur if the account couldn't be found an exception will be thrown
     */
    Account findAccountByAccountNumber(long accountNumber) throws RadbankirExceptionur;

    /**
     * Returns true if the account is locked because of too many login attempts
     *
     * @param accountNumber the account number of the account
     * @return true if the account is locked
     * @throws RadbankirExceptionur if the account couldn't be found
     */
    boolean isAccountLocked(long accountNumber) throws RadbankirExceptionur;

    /**
     * Attempt to login to a customers account
     *
     * @param accountNumber the account number for the customer
     * @param pinCode       the pin code for the account
     * @return the account if found
     * @throws AccountNotFoundException if the account couldn't be found
     * @throws LoginFailedException if the login failed
     * @throws AccountLockedException if the account is locked
     */
    Account login(long accountNumber, int pinCode) throws AccountNotFoundException, LoginFailedException, AccountLockedException;

    /**
     * Cancels any account operation
     *
     * @param accountNumber the account number to cancel operations
     */
    void cancel(long accountNumber);
}
