package se.caglabs.radbankir;

import java.util.List;
import java.util.Map;

/**
 * Project:Tungur-knivur
 * User: fredrik
 * Date: 16/03/16
 * Time: 18:27
 */
public class RadbankirMaintenancur {


    private Billbox billbox;

    public RadbankirMaintenancur(Billbox billbox) {
        this.billbox = billbox;
    }

    public List<Valuesur> loadBills(Valuesur valuesur, int antal) throws RadbankirExceptionur {
        return billbox.deposit(valuesur, antal);
    }

    public Map<Valuesur, Integer> showMeTheMoney() {
        return billbox.getBills();
    }
}
