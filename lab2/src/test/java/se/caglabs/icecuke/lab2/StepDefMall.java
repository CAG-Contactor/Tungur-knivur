package se.caglabs.icecuke.lab2;

import cucumber.api.java.*;
import se.caglabs.radbankir.*;

/**
 * Mall för step definitioner.
 */
public class StepDefMall {
  private RadbankirFacadur radbankirFacadur;

  @Before
  public void setup() {
    radbankirFacadur = BankomatInstans.getInstans();
  }

  @After
  public void tearDown() {
    BankomatInstans.destroy();
  }
}
