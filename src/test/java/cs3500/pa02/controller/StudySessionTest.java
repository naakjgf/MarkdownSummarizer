package cs3500.pa02.controller;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class StudySessionTest {

  @Test
  public void testStartStudySession() {
    String simulatedUserInput = "4\n1\n1\n1\n1\n";
    ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedUserInput.getBytes());
    System.setIn(testIn);
    Scanner scanner = new Scanner(System.in);
    StudySession session = new StudySession("./InputStudyGuideFiles/TestSrFile.sr",
        scanner);
  }

  @Test
  public void anotherInstanceOfStudySession() {
    String simulatedUserInput = "32\n1\n3\n2\n1\n1\n1\n1\n2\n2\n1\n2\n1\n2\n2\n1\n" +
        "1\n2\n1\n3\n1\n1\n1\n2\n2\n1\n2\n1\n1\n2\n1\n2\n1\n2";
    ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedUserInput.getBytes());
    System.setIn(testIn);
    Scanner scanner = new Scanner(System.in);
    StudySession session = new StudySession("./InputStudyGuideFiles/TestSrFile.sr",
        scanner);
  }
}