package cs3500.pa02.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class QuestionTest {

  @Test
  public void testQuestionCreationAndBehavior() {
    String line = "What is the capital of France?:::Paris:::EASY";
    GameEndResult gameEndResult = new GameEndResult();

    Question question = new Question(line, gameEndResult);
    assertEquals("What is the capital of France?", question.getQuestionText());
    assertEquals("Paris", question.getAnswer());
    assertEquals(Difficulty.EASY, question.getDifficulty());

    question.changeDifficulty(Difficulty.HARD);
    assertEquals(Difficulty.HARD, question.getDifficulty());
    assertEquals(1, gameEndResult.getEasyToHard());

    String invalidLine = "Invalid Line";
    try {
      Question invalidQuestion = new Question(invalidLine, gameEndResult);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), e.getMessage());
    }
  }

  @Test
  public void toStringParsingAndChangeDifficultyTesting() {
    String line = "What is the capital of France?:::Paris:::EASY";
    String line2 = "What is the capital of France?:::Paris:::HARD";
    String line3 = "What is the capital of France?:::Paris";
    String line4 = "";
    GameEndResult gameEndResult = new GameEndResult();

    assertThrows(IllegalArgumentException.class, () -> new Question(line4, gameEndResult));
    Question question3 = new Question(line3, gameEndResult);
    question3.changeDifficulty(Difficulty.EASY);
    assertEquals("[[What is the capital of France?:::Paris:::EASY]]", question3.toString());
    Question question2 = new Question(line2, gameEndResult);
    assertEquals("[[What is the capital of France?:::Paris:::HARD]]", question2.toString());
    Question question = new Question(line, gameEndResult);
    assertEquals("[[What is the capital of France?:::Paris:::EASY]]", question.toString());

    question.changeDifficulty(Difficulty.HARD);
    question.changeDifficulty(Difficulty.HARD);
    assertEquals("[[What is the capital of France?:::Paris:::HARD]]", question.toString());
  }
}