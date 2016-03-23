package se.caglabs.radbankir;

import java.util.List;

/**
 * Created by zefal on 23/03/16.
 */
public interface IRadbankirFacadur {
    void login(long accountNumber, int pinCode) throws RadbankirExceptionur;

    List<Valuesur> withdraw(int amount) throws RadbankirExceptionur;

    long getBalance() throws RadbankirExceptionur;

    void cancel();

    List<Valuesur> deposit(List<Valuesur> bills) throws RadbankirExceptionur;
}
