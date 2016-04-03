package se.caglabs.icecuke.lab2;

import lombok.Data;
import se.caglabs.radbankir.service.AccountManager;
import se.caglabs.radbankir.service.Billbox;
import se.caglabs.radbankir.RadbankirFacadur;
import se.caglabs.radbankir.service.IAccountManager;
import se.caglabs.radbankir.service.IBillbox;

@Data
public class BankomatInstans {
    private static BankomatInstans instans;

    private IBillbox billbox;
    private IAccountManager accountManager;
    private RadbankirFacadur radbankirFacadur;

    private BankomatInstans() {
        accountManager = new AccountManager();
        billbox = new Billbox();
        radbankirFacadur = new RadbankirFacadur(billbox, accountManager);
    }

    public static BankomatInstans getInstans() {
        if(instans == null ) {
            instans = new BankomatInstans();
        }

        return instans;
    }
}
