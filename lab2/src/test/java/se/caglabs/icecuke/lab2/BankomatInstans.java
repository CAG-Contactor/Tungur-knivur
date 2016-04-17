package se.caglabs.icecuke.lab2;

import se.caglabs.radbankir.*;
import se.caglabs.radbankir.service.*;

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
