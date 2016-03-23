package se.caglabs.radbankir;

import java.util.List;
import java.util.Map;

/**
 * Created by zefal on 23/03/16.
 */
public class Radbankir implements IRadbankirFacadur, IRadbankirMaintenancur {

    private IRadbankirFacadur radbankirFacadur;
    private IRadbankirMaintenancur radbankirMaintenancur;

    @Override
    public void login(long accountNumber, int pinCode) throws RadbankirExceptionur {
        radbankirFacadur.login(accountNumber, pinCode);
    }

    @Override
    public List<Valuesur> withdraw(int amount) throws RadbankirExceptionur {
        return radbankirFacadur.withdraw(amount);
    }

    @Override
    public long getBalance() throws RadbankirExceptionur {
        return radbankirFacadur.getBalance();
    }

    @Override
    public void cancel() {
        radbankirFacadur.cancel();
    }

    @Override
    public List<Valuesur> deposit(List<Valuesur> bills) throws RadbankirExceptionur {
        return radbankirFacadur.deposit(bills);
    }

    @Override
    public List<Valuesur> loadBills(Valuesur valuesur, int antal) throws RadbankirExceptionur {
        return radbankirMaintenancur.loadBills(valuesur, antal);
    }

    @Override
    public Map<Valuesur, Integer> showMeTheMoney() {
        return radbankirMaintenancur.showMeTheMoney();
    }
}
