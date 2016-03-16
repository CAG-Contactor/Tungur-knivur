package se.caglabs.icecuke.lab2;

import lombok.Data;
import se.caglabs.radbankir.Billbox;
import se.caglabs.radbankir.RadbankirFacadur;
import se.caglabs.radbankir.RadbankirMaintenancur;
import se.caglabs.radbankir.RadbankirMaintenancurImpl;

@Data
public class BankomatInstans {
    private static BankomatInstans instans;

    private RadbankirMaintenancur radbankirMaintenancur;
    private RadbankirFacadur radbankirFacadur;
    private Billbox billbox;

    private BankomatInstans() {
        billbox = new Billbox();
        radbankirMaintenancur = new RadbankirMaintenancurImpl(billbox);
        radbankirFacadur = new RadbankirFacadur(billbox);
    }

    public static BankomatInstans getInstans() {
        if(instans == null ) {
            instans = new BankomatInstans();
        }

        return instans;
    }


}
