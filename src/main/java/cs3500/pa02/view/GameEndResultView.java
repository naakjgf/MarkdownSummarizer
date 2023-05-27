package cs3500.pa02.view;

import cs3500.pa02.model.GameEndResult;

/**
 * A class representing the view for the results of the study session.
 */
public class GameEndResultView {
  private final GameEndResult result;

  public GameEndResultView(GameEndResult result) {
    this.result = result;
  }

  /**
   * Displays the results of the study session.
   */
  public void display() {
    System.out.println("Hard to Easy: " + result.getHardToEasy());
    System.out.println("Easy to Hard: " + result.getEasyToHard());
    System.out.println(
        "Number of Hard Questions in Bank: " + result.getNumberOfHardQuestionsInBank());
    System.out.println(
        "Number of Easy Questions in Bank: " + result.getNumberOfEasyQuestionsInBank());
    System.out.println("Number of Questions Answered: " + result.getNumberOfQuestionsAnswered());
    System.out.println(result.getSupportiveMessage());
    try {
      Thread.sleep(1500);
      System.out.println("\n[Ending Study Session...]\n");
      System.out.println(
          "Your .sr file has been updated with your results for your" + " next study session!");
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
