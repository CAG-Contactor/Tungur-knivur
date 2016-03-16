package se.caglabs.icecuke.lab2;

import cucumber.api.java.Before;
import cucumber.api.java.sv.Givet;
import cucumber.api.java.sv.När;
import cucumber.api.java.sv.Så;
import org.junit.Assert;
import se.caglabs.radbankir.Billbox;
import se.caglabs.radbankir.RadbankirMaintenancur;

import java.util.HashMap;
import java.util.Map;

public class UnderhallSteps {

    private Map<RadbankirMaintenancur.Valuesur, Integer> antalReturnerade;
    private RadbankirMaintenancur radbankirMaintenancur;
    private Billbox billbox;

    @Before
    public void setup() {
        antalReturnerade = new HashMap<>();
        billbox = BankomatInstans.getInstans().getBillbox();
        radbankirMaintenancur = BankomatInstans.getInstans().getRadbankirMaintenancur();
    }

    @Givet("^att det finns (\\d+) sedlar i facket för (\\d+)-kronorssedlar$")
    public void attDetFinnsSedlarIFacketFörKronorssedlar(int antal, int valor) throws Throwable {
        billbox.empty();
        RadbankirMaintenancur.Valuesur valuesur = RadbankirMaintenancur.Valuesur.from(valor);
        billbox.addBills(valuesur, antal);
    }

    @När("^teknikern fyller med (\\d+) (\\d+)-kronorssedlar$")
    public void teknikernFyllerMedKronorssedlar(int antal, int valor) throws Throwable {
        RadbankirMaintenancur.Valuesur valuesur = RadbankirMaintenancur.Valuesur.from(valor);
        int antalReturnerade = radbankirMaintenancur.loadBills(valuesur, antal);

        this.antalReturnerade.putIfAbsent(valuesur, 0);
        this.antalReturnerade.put(valuesur, antalReturnerade + this.antalReturnerade.get(valuesur));
    }

    @Så("^matar bankomaten ut (\\d+) (\\d+)-kronorssedlar$")
    public void matarBankomatenUtKronorssedlar(int antal, int valor) throws Throwable {
        RadbankirMaintenancur.Valuesur valuesur = RadbankirMaintenancur.Valuesur.from(valor);

        antalReturnerade.putIfAbsent(valuesur, 0);
        Assert.assertEquals(Integer.valueOf(antal), antalReturnerade.get(valuesur));
        antalReturnerade.remove(valuesur);
    }

    @Så("^innehåller bankomaten (\\d+) (\\d+)-kronorssedlar$")
    public void innehållerBankomatenKronorssedlar(int antal, int valor) throws Throwable {
        RadbankirMaintenancur.Valuesur valuesur = RadbankirMaintenancur.Valuesur.from(valor);
        int antalSedlarIBankomat = radbankirMaintenancur.showMeTheMoney().get(valuesur);
        Assert.assertEquals(antal, antalSedlarIBankomat);
    }
}
