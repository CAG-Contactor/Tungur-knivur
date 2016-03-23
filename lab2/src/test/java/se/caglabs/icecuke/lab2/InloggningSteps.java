package se.caglabs.icecuke.lab2;

import cucumber.api.java.Before;
import cucumber.api.java.sv.Givet;
import cucumber.api.java.sv.När;
import cucumber.api.java.sv.Så;
import org.junit.Assert;
import se.caglabs.radbankir.Account;
import se.caglabs.radbankir.AccountManager;
import se.caglabs.radbankir.RadbankirExceptionur;
import se.caglabs.radbankir.impl.RadbankirFacadur;

public class InloggningSteps {

    private int kontonummer;
    private RadbankirFacadur radbankirFacadur;
    private AccountManager accountManager;

    @Before
    public void setup() {
        radbankirFacadur = BankomatInstans.getInstans().getRadbankirFacadur();
        accountManager = BankomatInstans.getInstans().getAccountManager();
    }

    @När("^(?:kunden loggar in|att kunden är inloggad) med kontonummer (\\d+) och pinkod (\\d+)$")
    public void kundenLoggarInMedKontonummerOchPinkod(int kontonummer, int pinkod) throws Throwable {
        this.kontonummer = kontonummer;
        radbankirFacadur.login(kontonummer, pinkod);
    }

    @När("^kunden med kontonummer (\\d+) loggar in med fel pinkod$")
    public void kunden_med_kontonummer_loggar_in_med_fel_pinkod(int kontonummer) throws Throwable {
        this.kontonummer = kontonummer;
        try {
            radbankirFacadur.login(kontonummer, 99999);
        } catch (RadbankirExceptionur e) {
            // Ignorera denna
        }
    }

    @Så("^har inte kontot låsts$")
    public void har_inte_kontot_låsts() throws Throwable {
        Assert.assertFalse(accountManager.isAccountLocked(kontonummer));
    }

    @Så("^har kontot låsts$")
    public void har_kontot_låsts() throws Throwable {
        Assert.assertTrue(accountManager.isAccountLocked(kontonummer));
    }

    @Givet("^att kunden med kontonummer (\\d+) är inloggad$")
    public void att_kunden_med_kontonummer_är_inloggad(int kontonummer) throws Throwable {
        Account account = accountManager.findAccountByAccountNumber(kontonummer);
        account.setFailedAttempts(0);
        radbankirFacadur.login(kontonummer, account.getPinCode());
    }
}
