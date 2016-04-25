package se.caglabs.icecuke.lab2;

import cucumber.api.*;
import cucumber.api.java.Before;
import cucumber.api.java.sv.*;
import org.junit.*;
import se.caglabs.radbankir.*;
import se.caglabs.radbankir.exception.*;
import se.caglabs.radbankir.model.*;

import java.util.*;
import java.util.stream.*;

public class UttagSteps {

    private RadbankirFacadur radbankirFacadur;

    private long withdrawnAmount;
    private RadbankirExceptionur withdrawException;
    private List<Valuesur> withdrawnBills;

    @Before
    public void setup() {
        radbankirFacadur = BankomatInstans.getInstans();
        withdrawException = null;
        withdrawnAmount = 0;
        withdrawnBills = new ArrayList<>();
    }

    @Givet("^att bankomaten är full$")
    public void attBankomatenÄrFull() throws Throwable {
        radbankirFacadur.loadBills(Valuesur.HUNDRED, 100);
        radbankirFacadur.loadBills(Valuesur.TWOHUNDRED, 100);
        radbankirFacadur.loadBills(Valuesur.FIVEHUNDRED, 100);
        radbankirFacadur.loadBills(Valuesur.THOUSAND, 100);
    }

    @Givet("^att bankomaten är tom")
    public void attBankomatenÄrTom() throws Throwable {
        radbankirFacadur.emptyBillTrays();
    }

    @När("^kunden tar ut ([-]*\\d+)kr$")
    public void kundenTarUtKr(int uttagsSumma) throws Throwable {
        try {
            withdrawnBills = radbankirFacadur.withdraw(uttagsSumma);
            withdrawnAmount = withdrawnBills.stream().mapToLong(Valuesur::getNoteValue).sum();
        } catch (RadbankirExceptionur e) {
            withdrawException = e;
        }
    }

    @Så("^har kunden ([-]*\\d+)kr (?:kvar |)på kontot$")
    public void harKundenKrKvarPåKontot(int summa) throws Throwable {
        Assert.assertEquals(summa, radbankirFacadur.getBalance());
    }

    @Så("^har kunden fått ut ([-]*\\d+)kr$")
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


    @Så("^har kunden fått ut$")
    public void har_kunden_fått_ut(DataTable dataTable) throws Throwable {
        Map<Integer, Long> collect = withdrawnBills.stream().collect(Collectors.groupingBy(Valuesur::getNoteValue, Collectors.counting()));
        dataTable.getGherkinRows().forEach(row -> {
            Integer valor = Integer.valueOf(row.getCells().get(1));
            Long antal = Long.valueOf(row.getCells().get(0).trim());
            Assert.assertEquals("Kunden fick fel antal sedlar med valör " + valor, antal, collect.getOrDefault(valor, 0L));
        });
    }
}
