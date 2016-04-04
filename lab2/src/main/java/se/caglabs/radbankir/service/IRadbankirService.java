package se.caglabs.radbankir.service;

import se.caglabs.radbankir.exception.AccountLockedException;
import se.caglabs.radbankir.exception.AccountNotFoundException;
import se.caglabs.radbankir.exception.LoginFailedException;
import se.caglabs.radbankir.exception.RadbankirExceptionur;
import se.caglabs.radbankir.model.Valuesur;

import java.util.List;

/**
 * An interface that describes all operations available for an ATM customer
 */
public interface IRadbankirService {

    /**
     * Tries to login to the customers account using an account number and a pin code.
     *
     * @param accountNumber the customers account number
     * @param pinCode       the customers pin code
     * @throws LoginFailedException if the login failed
     * @throws AccountLockedException if the login failed and/or the account is locked
     */
    void login(long accountNumber, int pinCode) throws LoginFailedException, AccountLockedException, AccountNotFoundException;

    /**
     * Withdraw an amount from the customers account.
     *
     * @param amount an amount of money to withdraw
     * @return a list of bills withdrawn from the ATM
     * @throws RadbankirExceptionur if the amount to be withdrawn exceeds the customers balance or if the ATM
     *                              doesn't contain enough money for the transaction.
     */
    List<Valuesur> withdraw(int amount) throws RadbankirExceptionur;

    /**
     * Retrieves the customers account balance.
     *
     * @return the account balance
     * @throws RadbankirExceptionur if the balance couldn't be retrieved
     */
    long getBalance() throws RadbankirExceptionur;

    /**
     * Cancels any login- or transaction operations.
     */
    void cancel();

    /**
     * Deposit money to a customers account
     *
     * @param bills a list of bills to deposit
     * @return bills that couldn't be deposited
     * @throws RadbankirExceptionur if the deposit couldn't be made
     */
    List<Valuesur> deposit(List<Valuesur> bills) throws RadbankirExceptionur;
}
