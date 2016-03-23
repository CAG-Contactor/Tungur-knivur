package se.caglabs.radbankir;

import java.util.List;
import java.util.Map;

/**
 * Created by zefal on 23/03/16.
 */
public interface IRadbankirMaintenancur {
    List<Valuesur> loadBills(Valuesur valuesur, int antal) throws RadbankirExceptionur;

    Map<Valuesur, Integer> showMeTheMoney();
}
