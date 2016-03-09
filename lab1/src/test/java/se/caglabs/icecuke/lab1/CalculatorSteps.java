package se.caglabs.icecuke.lab1;

import cucumber.api.java.sv.*;
import org.junit.Assert;

public class CalculatorSteps {

    private Calculator calculator;

    @Givet("^att kalkylator är startad$")
    public void att_kalkylator_är_startad() throws Throwable {
        calculator = new Calculator();
    }

    @När("^jag summerar (\\d+) och (\\d+)$")
    public void jag_summerar_och(int arg1, int arg2) throws Throwable {
        calculator.add(arg1).add(arg2);
    }

    @Så("^är summan (\\d+)$")
    public void är_summan(int arg1) throws Throwable {
        Assert.assertEquals(Double.valueOf(arg1), Double.valueOf(calculator.getDisplay()));
    }
}
