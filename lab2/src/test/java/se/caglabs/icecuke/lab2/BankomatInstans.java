package se.caglabs.icecuke.lab2;

import lombok.Data;
import se.caglabs.radbankir.AccountManager;
import se.caglabs.radbankir.Billbox;
import se.caglabs.radbankir.impl.RadbankirFacadur;
import se.caglabs.radbankir.impl.RadbankirMaintenancur;

@Data
public class BankomatInstans {
    private static BankomatInstans instans;

    private RadbankirMaintenancur radbankirMaintenancur;
    private RadbankirFacadur radbankirFacadur;
    private Billbox billbox;
    private AccountManager accountManager;

    private BankomatInstans() {
        accountManager = new AccountManager();
        billbox = new Billbox();
        radbankirMaintenancur = new RadbankirMaintenancur(billbox);
        radbankirFacadur = new RadbankirFacadur(billbox, accountManager);
    }

    public static BankomatInstans getInstans() {
        if(instans == null ) {
            instans = new BankomatInstans();
        }

        return instans;
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }
}
