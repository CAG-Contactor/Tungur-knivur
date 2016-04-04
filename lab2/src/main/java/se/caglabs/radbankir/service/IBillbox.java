package se.caglabs.radbankir.service;

import se.caglabs.radbankir.exception.RadbankirExceptionur;
import se.caglabs.radbankir.model.Valuesur;

import java.util.List;
import java.util.Map;

/**
 * An interface describing a billbox that contains money in different trays.
 */
public interface IBillbox {

    /**
     * Clears all money in the billbox
     */
    void empty();

    /**
     * Withdraws an amount of money from the billbox
     *
     * @param amount the amount of money to withdraw
     * @return a list of money withdrawn from the billbox
     * @throws RadbankirExceptionur if the money couldn't be withdrawn
     */
    List<Valuesur> withdraw(int amount) throws RadbankirExceptionur;

    /**
     * Deposits money to the billbox
     *
     * @param bills a list of bills to deposit
     * @return a list of money that couldn't be added
     */
    List<Valuesur> deposit(List<Valuesur> bills);

    /**
     * Deposits money to the billbox
     *
     * @param valuesur the note value beeing added
     * @param count    the number of bills beeing added
     * @return a list of money that couldn't be added
     */
    List<Valuesur> deposit(Valuesur valuesur, int count);

    /**
     * Returns a map with trays for each note value and the number of bills for each tray
     *
     * @return a map with the tray and the number of bills
     */
    Map<Valuesur, Integer> getBillTrayStatus();
}
