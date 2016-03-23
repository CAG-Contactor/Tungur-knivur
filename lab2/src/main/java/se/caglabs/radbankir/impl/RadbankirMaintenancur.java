package se.caglabs.radbankir.impl;

import se.caglabs.radbankir.Billbox;
import se.caglabs.radbankir.IRadbankirMaintenancur;
import se.caglabs.radbankir.RadbankirExceptionur;
import se.caglabs.radbankir.Valuesur;

import java.util.List;
import java.util.Map;

/**
 * Project:Tungur-knivur
 * User: fredrik
 * Date: 16/03/16
 * Time: 18:27
 */
public class RadbankirMaintenancur implements IRadbankirMaintenancur {


    private Billbox billbox;

    public RadbankirMaintenancur(Billbox billbox) {
        this.billbox = billbox;
    }

    @Override
    public List<Valuesur> loadBills(Valuesur valuesur, int antal) throws RadbankirExceptionur {
        return billbox.deposit(valuesur, antal);
    }

    @Override
    public Map<Valuesur, Integer> showMeTheMoney() {
        return billbox.getBills();
    }
}
