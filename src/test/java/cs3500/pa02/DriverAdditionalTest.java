package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.Driver;
import java.io.ByteArrayInputStream;
import java.io.File;
import org.junit.jupiter.api.Test;

public class DriverAdditionalTest {
  @Test
  public void testMainWithInvalidFile() {
    String simulatedUserInput = "./invalidPath\n./InputStudyGuideFiles/TestSrFile.sr\n2\n1\n1";
    ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedUserInput.getBytes());
    System.setIn(testIn);

    Driver.main(new String[]{});

    System.setIn(System.in);
  }
  @Test
  public void testMain() {
    String[] args = new String[3];
    args[0] = "./InputStudyGuideFiles";
    args[1] = "fileName";
    args[2] = "./OutputStudyGuideFiles/ImportantPointsOutput.md";
    Driver.main(args);
    File inputFile = new File(
        "./InputStudyGuideFiles/InnerDirectory1/InnerDirectory2/BoringEmpty.md");
    assertEquals(0, inputFile.length());
  }
}
