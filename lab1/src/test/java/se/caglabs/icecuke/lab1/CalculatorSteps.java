package se.caglabs.icecuke.lab1;

import cucumber.api.DataTable;
import cucumber.api.java.sv.*;
import org.junit.*;

import java.math.BigDecimal;
import java.util.List;

public class CalculatorSteps {

    private Calculator calculator;

    @Givet("^att min kalkylator är nollställd$")
    public void att_kalkylator_är_startad() throws Throwable {
        calculator = new Calculator();
    }

    @När("^jag summerar (.*) och (.*)$")
    public void jag_summerar_och(BigDecimal arg1, BigDecimal arg2) throws Throwable {
        calculator.add(arg1).add(arg2);
    }

    @Så("^har jag (.*)$")
    public void är_summan(BigDecimal arg1) throws Throwable {
        Assert.assertTrue("Förväntade " + calculator.getDisplay() + " men var " + arg1, calculator.getDisplay().compareTo(arg1) == 0);
    }

    @När("^jag matar in plus (.*)$")
    public void jag_matar_in_plus(BigDecimal arg1) throws Throwable {
        calculator.add(arg1);
    }

    @När("^jag matar in minus (.*)$")
    public void jag_matar_in_minus(BigDecimal arg1) throws Throwable {
        calculator.subtract(arg1);
    }

    @När("^jag multiplicerar med (.*)$")
    public void jag_multiplicerar_med_multiplicera(BigDecimal arg1) throws Throwable {
        calculator.multiply(arg1);
    }

    @När("^jag matar in dessa tal för att räkna ut medeltal$")
    public void jag_matar_in_dessa_tal_för_att_räkna_ut_medeltal(DataTable arg1) throws Throwable {
        List<BigDecimal> tal = arg1.asList(BigDecimal.class);
        calculator.average(tal);
    }
}
