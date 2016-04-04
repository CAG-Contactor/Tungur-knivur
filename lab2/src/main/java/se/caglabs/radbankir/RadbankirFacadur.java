package se.caglabs.radbankir;

import se.caglabs.radbankir.exception.RadbankirExceptionur;
import se.caglabs.radbankir.model.Valuesur;
import se.caglabs.radbankir.service.*;

import java.util.List;
import java.util.Map;

/**
 * A facade for customer- and maintenance ATM services
 */
public class RadbankirFacadur implements IRadbankirService, IMaintenanceService {

    private IRadbankirService radbankirService;
    private IMaintenanceService maintenanceService;

    /**
     * Constructor for creating a facade
     *
     * @param billbox the billbox containing the money
     * @param accountManager the accountmanager responsible for handling accounts
     */
    public RadbankirFacadur(IBillbox billbox, IAccountManager accountManager) {
        this.radbankirService = new RadbankirService(billbox, accountManager);
        this.maintenanceService = new MaintenanceService(billbox);
    }

    /**
     * @param accountNumber the customers account number
     * @param pinCode       the customers pin code
     * @throws RadbankirExceptionur
     */
    @Override
    public void login(long accountNumber, int pinCode) throws RadbankirExceptionur {
        radbankirService.login(accountNumber, pinCode);
    }

    /**
     * Withdraw an amount from the customers account.
     *
     * @param amount an amount of money to withdraw
     * @return a list of bills withdrawn from the ATM
     * @throws RadbankirExceptionur if the amount to be withdrawn exceeds the customers balance or if the ATM
     *                              doesn't contain enough money for the transaction.
     */
    @Override
    public List<Valuesur> withdraw(int amount) throws RadbankirExceptionur {
        return radbankirService.withdraw(amount);
    }

    /**
     * Retrieves the customers account balance.
     *
     * @return the account balance
     * @throws RadbankirExceptionur if the balance couldn't be retrieved
     */
    @Override
    public long getBalance() throws RadbankirExceptionur {
        return radbankirService.getBalance();
    }

    /**
     * Cancels any login- or transaction operations.
     */
    @Override
    public void cancel() {
        radbankirService.cancel();
    }

    /**
     * Deposit money to a customers account
     *
     * @param bills a list of bills to deposit
     * @return bills that couldn't be deposited
     * @throws RadbankirExceptionur if the deposit couldn't be made
     */
    @Override
    public List<Valuesur> deposit(List<Valuesur> bills) throws RadbankirExceptionur {
        return radbankirService.deposit(bills);
    }

    /**
     * Allows maintenance to load the machine with bills
     *
     * @param valuesur the bill value being added
     * @param count    the number of bills being added
     * @return all bills that couldn't be added
     * @throws RadbankirExceptionur if the operation failed.
     */
    @Override
    public List<Valuesur> loadBills(Valuesur valuesur, int count) throws RadbankirExceptionur {
        return maintenanceService.loadBills(valuesur, count);
    }

    /**
     * Allows maintenance to show the status of all trays in the machine.
     *
     * @return a map with all bill trays with the note value as the key and the
     * number of bills as the value
     */
    @Override
    public Map<Valuesur, Integer> getBillTrayStatus() {
        return maintenanceService.getBillTrayStatus();
    }
}
