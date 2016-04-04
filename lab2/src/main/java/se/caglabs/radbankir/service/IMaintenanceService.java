package se.caglabs.radbankir.service;

import se.caglabs.radbankir.exception.RadbankirExceptionur;
import se.caglabs.radbankir.model.Valuesur;

import java.util.List;
import java.util.Map;

/**
 * An interface describing all maintenance operations for the ATM
 */
public interface IMaintenanceService {

    /**
     * Loads the ATM with bills
     *
     * @param valuesur the bill value being added
     * @param count    the number of bills being added
     * @return all bills that wasn't accepted by the machine
     * @throws RadbankirExceptionur if the operation failed.
     */
    List<Valuesur> loadBills(Valuesur valuesur, int count) throws RadbankirExceptionur;

    /**
     * Shows the status of all trays in the machine.
     *
     * @return a map with all bill trays with the note value as the key and the number of bills as the value
     */
    Map<Valuesur, Integer> getBillTrayStatus();

    /**
     * Empties all trays with money
     */
    void emptyBillTrays();
}
