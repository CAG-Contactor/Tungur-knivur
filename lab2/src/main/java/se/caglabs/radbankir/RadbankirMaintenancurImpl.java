package se.caglabs.radbankir;

import java.util.Map;

/**
 * Project:Tungur-knivur
 * User: fredrik
 * Date: 16/03/16
 * Time: 18:27
 */
public class RadbankirMaintenancurImpl implements RadbankirMaintenancur {

    private Billbox billbox;

    public RadbankirMaintenancurImpl(Billbox billbox) {
        this.billbox = billbox;
    }

    @Override
    public int loadBills(Valuesur valuesur, int antal) {
        return billbox.addBills(valuesur, antal);
    }

    @Override
    public Map<Valuesur, Integer> showMeTheMoney() {
        return billbox.getBills();
    }
}
