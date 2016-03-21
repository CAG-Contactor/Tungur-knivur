package se.caglabs.icecuke.lab2;

import cucumber.api.java.Before;
import cucumber.api.java.sv.När;
import se.caglabs.radbankir.RadbankirFacadur;
import se.caglabs.radbankir.Valuesur;

import java.util.ArrayList;
import java.util.List;


public class InsattningSteps {
    private RadbankirFacadur radbankirFacadur;

    @Before
    public void setup() {
        radbankirFacadur = BankomatInstans.getInstans().getRadbankirFacadur();
    }

    @När("^kunden sätter in (\\d+) (\\d+)-kronorssedel$")
    public void kunden_sätter_in_kronorssedel(int antal, int valor) throws Throwable {
        Valuesur valuesur = Valuesur.from(valor);
        List<Valuesur> valuesurs = new ArrayList<>();

        for (int i = 0; i < antal; i++) {
            valuesurs.add(valuesur);
        }
        radbankirFacadur.deposit(valuesurs);
    }
}
