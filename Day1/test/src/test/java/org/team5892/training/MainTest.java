// you likely haven't seen this before. This is organization that matches the folders. Don't worry about it much.
package org.team5892.training;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class MainTest {
  private static final PrintStream originalOut = System.out;
  private static final PrintStream originalErr = System.err;
  private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private static final Class<?> fractionClass = Main.FRACTION_CLASS;
  @BeforeEach
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @AfterEach
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }
  @Test
  void classTest() {
      assertNotNull(fractionClass, "Fraction class needs to be set in main!");
  }
  @Test
  void mainTest() {
    Main.main(new String[]{});
    String[] out = outContent.toString().split("\n");
    assertEquals("Hello world!", out[0],"Must print 'Hello world!'");
    assertEquals("1/2 / 1/7 = 3.5",out[1],"Must divide and output decimal");
    assertEquals("1/2 - 1/7 = 0.35714285714285715",out[2],"Must subtract and output decimal");
  }



}
