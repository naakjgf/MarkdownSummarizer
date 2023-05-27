package cs3500.pa02.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GameEndResultTest {

  @Test
  public void testGameEndResultBehavior() {
    GameEndResult result = new GameEndResult();

    assertEquals(0, result.getHardToEasy());
    assertEquals(0, result.getEasyToHard());
    assertEquals(0, result.getNumberOfHardQuestionsInBank());
    assertEquals(0, result.getNumberOfEasyQuestionsInBank());
    assertEquals(0, result.getNumberOfQuestionsAnswered());

    result.incrementHardToEasy();
    assertEquals(1, result.getHardToEasy());
    result.incrementEasyToHard();
    assertEquals(1, result.getEasyToHard());

    result.setNumberOfHardQuestionsInBank(10);
    assertEquals(10, result.getNumberOfHardQuestionsInBank());

    result.setNumberOfEasyQuestionsInBank(20);
    assertEquals(20, result.getNumberOfEasyQuestionsInBank());

    result.incrementNumberOfQuestionsAnswered();
    assertEquals(1, result.getNumberOfQuestionsAnswered());

    result.setSupportiveMessage();
    assertEquals("You got all the hard questions right! You're a genius!",
        result.getSupportiveMessage());
  }

  @Test
  public void supportiveMessageAllHardQuestionsRight() {
    GameEndResult result = new GameEndResult();
    for (int i = 0; i < 10; i++) {
      result.incrementHardToEasy();
      result.incrementNumberOfQuestionsAnswered();
    }
    result.setNumberOfHardQuestionsInBank(0);
    result.setSupportiveMessage();
    assertEquals("You got all the hard questions right! You're a genius!", result.getSupportiveMessage());
  }

  @Test
  public void supportiveMessageNoImprovement() {
    GameEndResult result = new GameEndResult();
    for (int i = 0; i < 10; i++) {
      result.incrementNumberOfQuestionsAnswered();
    }
    result.setNumberOfHardQuestionsInBank(10);
    result.setSupportiveMessage();
    assertEquals("There is room for improvement, I believe in you though!", result.getSupportiveMessage());
  }

  @Test
  public void supportiveMessageGreatProgress() {
    GameEndResult result = new GameEndResult();
    for (int i = 0; i < 6; i++) {
      result.incrementHardToEasy();
    }
    for (int i = 0; i < 10; i++) {
      result.incrementNumberOfQuestionsAnswered();
    }
    result.setNumberOfHardQuestionsInBank(10);
    result.setSupportiveMessage();
    assertEquals("You're making great progress!", result.getSupportiveMessage());
  }

  @Test
  public void setSupportiveMessage_DoingWell() {
    GameEndResult result = new GameEndResult();
    for (int i = 0; i < 4; i++) {
      result.incrementHardToEasy();
    }
    for (int i = 0; i < 10; i++) {
      result.incrementNumberOfQuestionsAnswered();
    }
    result.setNumberOfHardQuestionsInBank(10);
    result.setSupportiveMessage();
    assertEquals("You're doing well, keep it up!", result.getSupportiveMessage());
  }

  @Test
  public void setSupportiveMessage_GreatJob() {
    GameEndResult result = new GameEndResult();
    for (int i = 0; i < 2; i++) {
      result.incrementHardToEasy();
    }
    for (int i = 0; i < 10; i++) {
      result.incrementNumberOfQuestionsAnswered();
    }
    result.setNumberOfHardQuestionsInBank(10);
    result.setSupportiveMessage();
    assertEquals("Great Job! You're a pro!", result.getSupportiveMessage());
  }
}