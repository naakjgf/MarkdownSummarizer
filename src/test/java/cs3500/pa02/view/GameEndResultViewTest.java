package cs3500.pa02.view;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa02.model.GameEndResult;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameEndResultViewTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @BeforeEach
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
  }
  @AfterEach
  public void restoreStreams() {
    System.setOut(originalOut);
  }

  @Test
  public void testDisplay() {
    GameEndResult result = new GameEndResult();
    GameEndResultView view = new GameEndResultView(result);
    view.display();
    String expectedOutput = """
        Hard to Easy: 0
        Easy to Hard: 0
        Number of Hard Questions in Bank: 0
        Number of Easy Questions in Bank: 0
        Number of Questions Answered: 0
        null

        [Ending Study Session...]

        Your .sr file has been updated with your results for your next study session!
        """;
    assertEquals(expectedOutput, outContent.toString());
  }

  @Test
  public void testSleepingThrowsAndInterruptedException() {
    GameEndResult result = new GameEndResult();
    GameEndResultView view = new GameEndResultView(result);
    Thread thread = new Thread(() -> {
      try {
        view.display();
      } catch (RuntimeException e) {
        throw e;
      }
    });
    thread.start();
    thread.interrupt();
    try {
      thread.join();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}