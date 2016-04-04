package se.caglabs.radbankir.service;

import se.caglabs.radbankir.exception.RadbankirExceptionur;
import se.caglabs.radbankir.model.Valuesur;

import java.util.List;
import java.util.Map;

/**
 * Project:Tungur-knivur
 * User: fredrik
 * Date: 16/03/16
 * Time: 18:27
 */
public class MaintenanceService implements IMaintenanceService {


    private IBillbox billbox;

    public MaintenanceService(IBillbox billbox) {
        this.billbox = billbox;
    }

    @Override
    public List<Valuesur> loadBills(Valuesur valuesur, int count) throws RadbankirExceptionur {
        return billbox.deposit(valuesur, count);
    }

    @Override
    public Map<Valuesur, Integer> getBillTrayStatus() {
        return billbox.getBillTrayStatus();
    }

    @Override
    public void emptyBillTrays() {
        billbox.empty();
    }
}
