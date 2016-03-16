package se.caglabs.icecuke.lab2;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.sv.Givet;
import cucumber.api.java.sv.När;
import cucumber.api.java.sv.Så;
import org.junit.Assert;
import se.caglabs.radbankir.Billbox;
import se.caglabs.radbankir.RadbankirFacadur;
import se.caglabs.radbankir.RadbankirMaintenancur;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project:Tungur-knivur
 * User: fredrik
 * Date: 16/03/16
 * Time: 20:02
 */
public class UttagSteps {

    private RadbankirMaintenancur radbankirMaintenancur;
    private Billbox billbox;
    private RadbankirFacadur radbankirFacadur;

    @Before
    public void setup() {
        billbox = BankomatInstans.getInstans().getBillbox();
        radbankirMaintenancur = BankomatInstans.getInstans().getRadbankirMaintenancur();
        radbankirFacadur = BankomatInstans.getInstans().getRadbankirFacadur();
    }


    @Givet("^att bankomaten är full$")
    public void attBankomatenÄrFull() throws Throwable {
        billbox.addBills(RadbankirMaintenancur.Valuesur.HUNDRED, 100);
        billbox.addBills(RadbankirMaintenancur.Valuesur.TWOHUNDRED, 100);
        billbox.addBills(RadbankirMaintenancur.Valuesur.FIVEHUNDRED, 100);
        billbox.addBills(RadbankirMaintenancur.Valuesur.THOUSAND, 100);
    }

    @När("^kunden loggar in med kontonummer (\\d+) och pinkod (\\d+)$")
    public void kundenLoggarInMedKontonummerOchPinkod(int kontonummer, int pinkod) throws Throwable {
        radbankirFacadur.login(kontonummer,pinkod);
    }

    @När("^kunden tar ut (\\d+)kr$")
    public void kundenTarUtKr(int uttagsSumma) throws Throwable {
        Map<RadbankirMaintenancur.Valuesur, Integer> valuesurIntegerMap = radbankirFacadur.withDraw(uttagsSumma);
        valuesurIntegerMap.values().stream().mapToInt(v -> v).sum();
    }

    @Så("^har kunden (\\d+)kr kvar på kontot$")
    public void harKundenKrKvarPåKontot(int summa) throws Throwable {
        Assert.assertEquals(summa, radbankirFacadur.getBalance());
    }

    @Så("^har kunden fått ut (\\d+)kr$")
    public void harKundenFåttUtKr(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
