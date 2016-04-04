package se.caglabs.icecuke.lab2;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.sv.När;
import cucumber.api.java.sv.Så;
import org.junit.Assert;
import se.caglabs.radbankir.RadbankirFacadur;
import se.caglabs.radbankir.exception.AccountLockedException;
import se.caglabs.radbankir.exception.AccountNotFoundException;
import se.caglabs.radbankir.exception.LoginFailedException;

public class InloggningSteps {

    private RadbankirFacadur radbankirFacadur;
    private LoginFailedException loginFailedException;
    private AccountLockedException accountLockedException;
    private AccountNotFoundException accountNotFoundException;

    @Before
    public void setup() {
        loginFailedException = null;
        accountLockedException = null;
        accountNotFoundException = null;
        radbankirFacadur = BankomatInstans.getInstans();
    }

    @After
    public void tearDown() {
        BankomatInstans.destroy();
    }

    @När("^kunden loggar in med kontonummer (\\d+) och pinkod (\\d+)$")
    public void kundenLoggarInMedKontonummerOchPinkod(int kontonummer, int pinkod) throws Throwable {
        try {
            radbankirFacadur.login(kontonummer, pinkod);
        } catch (LoginFailedException e) {
            loginFailedException = e;
        } catch (AccountLockedException e) {
            accountLockedException = e;
        } catch (AccountNotFoundException e) {
            accountNotFoundException = e;
        }    }

    @När("^kunden med kontonummer (\\d+) loggar in med fel pinkod$")
    public void kunden_med_kontonummer_loggar_in_med_fel_pinkod(int kontonummer) {
        try {
            radbankirFacadur.login(kontonummer, 99999);
        } catch (LoginFailedException e) {
            loginFailedException = e;
        } catch (AccountLockedException e) {
            accountLockedException = e;
        } catch (AccountNotFoundException e) {
            accountNotFoundException = e;
        }
    }

    @När("^kunden avbryter$")
    public void kunden_avbryter() throws Throwable {
        radbankirFacadur.cancel();
    }

    @Så("^får kunden ett felmeddelande att försöka igen$")
    public void får_kunden_ett_felmeddelande_att_försöka_igen() throws Throwable {
        Assert.assertNotNull(loginFailedException);
    }

    @Så("^får kunden ett felmeddelande om att kontot är låst$")
    public void får_kunden_ett_felmeddelande_om_att_kontot_är_låst() throws Throwable {
        Assert.assertNotNull(accountLockedException);
    }
}
