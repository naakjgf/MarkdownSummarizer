package cs3500.pa02.view;

import cs3500.pa02.model.Difficulty;
import cs3500.pa02.model.GameEndResult;
import cs3500.pa02.model.Question;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudySessionViewTest {

  @AfterEach
  public void resetSystemIO() {
    System.setIn(System.in);
    System.setOut(System.out);
  }

  @Test
  public void testDisplayQuestion() {
    GameEndResult result = new GameEndResult();
    Question question = new Question(
        "What is the capital of France?:::Paris:::EASY", result);
    StudySessionView view = new StudySessionView();

    ByteArrayOutputStream output = new ByteArrayOutputStream();
    System.setOut(new PrintStream(output));

    view.displayQuestion(question);

    String expectedOutput = "\nWhat is the capital of France?\n\n";

    System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

    assertEquals(expectedOutput, output.toString());
  }

  @Test
  public void testGetNumberOfQuestions() {
    StudySessionView view = new StudySessionView();
    Scanner scanner = new Scanner("huh\n5\n");
    int num = view.getNumberOfQuestions(scanner);
    assertEquals(5, num);
  }

  @Test
  public void testDisplayAndTakeInOptions() {
    StudySessionView view = new StudySessionView();
    Scanner scanner = new Scanner("10\n1\n");
    int choice = view.displayAndTakeInOptions(scanner);
    assertEquals(10, choice);
  }

  @Test
  public void testGetSrQuestionBankPath() {
    StudySessionView view = new StudySessionView();
    Scanner scanner = new Scanner("wrong/path\npath/to/file.sr\n");
    String path = view.getSrQuestionBankPath(scanner);
    assertEquals("path/to/file.sr", path);
  }

  @Test
  public void testDisplayAnswer() {
    GameEndResult result = new GameEndResult();
    Question question = new Question(
        "What is the capital of France?:::Paris:::EASY", result);
    StudySessionView view = new StudySessionView();

    Scanner scanner = new Scanner("0\n1\n");
    view.displayAnswer(question, scanner);
    assertEquals(Difficulty.EASY, question.getDifficulty());
  }

  @Test
  public void testDisplayAnswer2() {
    GameEndResult result = new GameEndResult();
    Question question = new Question(
        "What is the capital of France?:::Paris:::EASY", result);
    StudySessionView view = new StudySessionView();

    Scanner scanner = new Scanner("10\n2\n");
    view.displayAnswer(question, scanner);
    assertEquals(Difficulty.HARD, question.getDifficulty());
  }
}
