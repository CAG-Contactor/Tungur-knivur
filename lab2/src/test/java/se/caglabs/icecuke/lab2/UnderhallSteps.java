package se.caglabs.icecuke.lab2;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.sv.Givet;
import cucumber.api.java.sv.När;
import cucumber.api.java.sv.Så;
import org.junit.Assert;
import se.caglabs.radbankir.RadbankirFacadur;
import se.caglabs.radbankir.model.Valuesur;
import se.caglabs.radbankir.service.IBillbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnderhallSteps {

    private Map<Valuesur, Integer> antalReturnerade;
    private RadbankirFacadur radbankirFacadur;

    @Before
    public void setup() {
        antalReturnerade = new HashMap<>();
        radbankirFacadur = BankomatInstans.getInstans();
    }

    @After
    public void tearDown() {
        BankomatInstans.destroy();
    }

    @När("^(?:att |)tekniker(?:n|) fyll(?:er|t) på med ([-]*\\d+) (\\d+)-kronorssedlar$")
    public void teknikernFyllerMedKronorssedlar(int antal, int valor) throws Throwable {
        Valuesur valuesur = Valuesur.from(valor);
        List<Valuesur> returneradeSedlar = radbankirFacadur.loadBills(valuesur, antal);

        this.antalReturnerade.putIfAbsent(valuesur, 0);
        this.antalReturnerade.put(valuesur, returneradeSedlar.size() + this.antalReturnerade.get(valuesur));
    }

    @Så("^matar bankomaten ut (\\d+) (\\d+)-kronorssedlar$")
    public void matarBankomatenUtKronorssedlar(int antal, int valor) throws Throwable {
        Valuesur valuesur = Valuesur.from(valor);

        antalReturnerade.putIfAbsent(valuesur, 0);
        Assert.assertEquals(Integer.valueOf(antal), antalReturnerade.get(valuesur));
        antalReturnerade.remove(valuesur);
    }

    @Så("^innehåller bankomaten (\\d+) (\\d+)-kronorssedlar$")
    public void innehållerBankomatenKronorssedlar(int antal, int valor) throws Throwable {
        Valuesur valuesur = Valuesur.from(valor);
        int antalSedlarIBankomat = radbankirFacadur.getBillTrayStatus().get(valuesur);
        Assert.assertEquals(antal, antalSedlarIBankomat);
    }

    @Så("^finns det (\\d+) (\\d+)-kronorssedlar i bankomaten$")
    public void finns_det_kronorssedlar_i_bankomaten(int antal, int valor) throws Throwable {
  //      Assert.assertEquals(Integer.valueOf(billbox.getBillTrayStatus().get(Valuesur.from(valor))), Integer.valueOf(antal));
    }
}
