// you likely haven't seen this before. This is organization that matches the folders. Don't worry about it much.
package org.team5892.training;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public final class MainTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @BeforeAll
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @AfterAll
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  Main main = new Main();

  @Test
  public void testtest() {
    Main.main(new String[1]);
    assertEquals("Hello World", outContent.toString());

  }

}
