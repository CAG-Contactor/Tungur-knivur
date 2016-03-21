package se.caglabs.icecuke.lab2;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.sv.Givet;
import cucumber.api.java.sv.När;
import cucumber.api.java.sv.Så;
import org.junit.Assert;
import se.caglabs.radbankir.*;

import java.util.Arrays;
import java.util.List;

/**
 * Project:Tungur-knivur
 * User: fredrik
 * Date: 16/03/16
 * Time: 20:02
 */
public class UttagSteps {

    private Billbox billbox;
    private AccountManager accountManager;
    private RadbankirFacadur radbankirFacadur;

    private long withdrawnAmount;
    private RadbankirExceptionur withdrawException;


    @Before
    public void setup() {
        billbox = BankomatInstans.getInstans().getBillbox();
        radbankirFacadur = BankomatInstans.getInstans().getRadbankirFacadur();
        accountManager = BankomatInstans.getInstans().getAccountManager();
        withdrawException = null;
        withdrawnAmount = 0;
    }

    @Givet("^att bankomaten är full$")
    public void attBankomatenÄrFull() throws Throwable {
        billbox.deposit(Valuesur.HUNDRED, 100);
        billbox.deposit(Valuesur.TWOHUNDRED, 100);
        billbox.deposit(Valuesur.FIVEHUNDRED, 100);
        billbox.deposit(Valuesur.THOUSAND, 100);
    }

    @Givet("^att bankomaten är tom")
    public void attBankomatenÄrTom() throws Throwable {
        billbox.empty();
    }

    @Givet("^att kunden med kontonummer (\\d+) har (\\d+)kr på kontot$")
    public void att_kunden_med_kontonummer_har_kr_på_kontot(int kontonummer, int summa) throws Throwable {
        accountManager.findAccountByAccountNumber(kontonummer).setBalance(summa);
    }

    @När("^kunden tar ut (\\d+)kr$")
    public void kundenTarUtKr(int uttagsSumma) throws Throwable {
        try {
            List<Valuesur> bills = radbankirFacadur.withdraw(uttagsSumma);
            withdrawnAmount = bills.stream().mapToLong(Valuesur::getNoteValue).sum();
        } catch (RadbankirExceptionur e) {
            withdrawException = e;
        }
    }

    @Så("^har kunden (\\d+)kr (?:kvar |)på kontot$")
    public void harKundenKrKvarPåKontot(int summa) throws Throwable {
        Assert.assertEquals(summa, radbankirFacadur.getBalance());
    }

    @Så("^har kunden fått ut (\\d+)kr$")
    public void harKundenFåttUtKr(int summa) throws Throwable {
        Assert.assertEquals(Long.valueOf(summa), Long.valueOf(withdrawnAmount));
    }

    @När("^(?:kunden matar in |)en (\\d+)-kronorssedel$")
    public void kunder_matar_in_en_sedel(int valor) throws RadbankirExceptionur {
        Valuesur valuesur = Valuesur.from(valor);
        radbankirFacadur.deposit(Arrays.asList(valuesur));
    }

    @Så("^nekas kunden uttag$")
    public void nekas_kunden_uttag() throws Throwable {
        Assert.assertNotNull("Förväntade oss ett fel vid uttag av pengar", withdrawException);
        Assert.assertEquals(Long.valueOf(0), Long.valueOf(withdrawnAmount));
    }

}
