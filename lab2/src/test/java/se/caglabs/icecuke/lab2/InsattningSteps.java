package se.caglabs.icecuke.lab2;

import cucumber.api.java.*;
import cucumber.api.java.sv.*;
import se.caglabs.radbankir.*;
import se.caglabs.radbankir.model.*;

import java.util.*;


public class InsattningSteps {
    private RadbankirFacadur radbankirFacadur;

    @Before
    public void setup() {
        radbankirFacadur = BankomatInstans.getInstans();
    }

    @After
    public void tearDown() {
        BankomatInstans.destroy();
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
