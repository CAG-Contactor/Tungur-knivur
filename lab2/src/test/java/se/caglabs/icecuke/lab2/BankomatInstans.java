package se.caglabs.icecuke.lab2;

import se.caglabs.radbankir.RadbankirFacadur;
import se.caglabs.radbankir.service.AccountManager;
import se.caglabs.radbankir.service.Billbox;
import se.caglabs.radbankir.service.IAccountManager;
import se.caglabs.radbankir.service.IBillbox;

public class BankomatInstans {

    private static RadbankirFacadur radbankirFacadur;

    public static RadbankirFacadur getInstans() {
        if (radbankirFacadur == null) {
            radbankirFacadur = new RadbankirFacadur(new Billbox(), new AccountManager());
        }

        return radbankirFacadur;
    }

    public static void destroy() {
        radbankirFacadur = null;
    }
}
