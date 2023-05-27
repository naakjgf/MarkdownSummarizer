package cs3500.pa02.view;

import cs3500.pa02.model.Difficulty;
import cs3500.pa02.model.Question;
import java.util.Scanner;

/**
 * A class that represents a study session view.
 */
public class StudySessionView {

  /**
   * Displays the question portion of the specific Question it is called on.
   *
   * @param question an instance of the Question class which has a question to be displayed.
   */
  public void displayQuestion(Question question) {
    System.out.println("\n" + question.getQuestionText() + "\n");
  }

  /**
   * Displays the options on how to deal with a question for the user to choose from and takes in
   * the user's choice.
   *
   * @return an integer representing the user's choice.
   */
  public int displayAndTakeInOptions(Scanner newScanner) {
    System.out.println("""
        Choose an option:\s
        --------------------------
        |   [1 | Mark As Easy]   |\s
        |   [2 | Mark As Hard]   |\s
        |    [3 | See Answer]    |\s
        |      [4 | Exit]        |\s
        --------------------------""");
    if (newScanner.hasNextInt()) {
      return newScanner.nextInt();
    } else {
      System.out.println("Please enter a valid integer representing one of the options.");
      newScanner.nextLine();
      return displayAndTakeInOptions(newScanner);
    }
  }

  /**
   * Displays the answer portion of the specific Question it is called on, and takes in the user's
   * choice of whether to mark the question as easy or hard after having seen the answer.
   *
   * @param question an instance of the Question class which has an answer to be displayed.
   */
  public void displayAnswer(Question question, Scanner thisScanner) {
    int choice = 0;
    do {
      System.out.println("\n" + question.getAnswer() + "\n");
      System.out.println(
          "Would you like to mark this question as \n 1." + Difficulty.EASY + "\nor \n 2."
              + Difficulty.HARD + "?");
      if (thisScanner.hasNextInt()) {
        choice = thisScanner.nextInt();
        if (choice == 1) {
          question.changeDifficulty(Difficulty.EASY);
          System.out.println("Marking question as easy.");
        } else if (choice == 2) {
          question.changeDifficulty(Difficulty.HARD);
          System.out.println("Marking question as hard.");
        } else {
          System.out.println("Please enter a valid integer representing one of the options.");
        }
      } else {
        System.out.println("Please enter a valid integer representing one of the options.");
        thisScanner.nextLine();
        displayAnswer(question, thisScanner);
      }
    } while (choice != 1 && choice != 2);
  }


  /**
   * gets the path to the .sr file that the user wants to study.
   *
   * @return a String representing the path to the .sr file.
   */
  public String getSrQuestionBankPath(Scanner newScanner) {
    String srPath = "";
    boolean isValid = false;
    while (!isValid) {
      System.out.println("Please enter the path to your .sr study file.");
      if (newScanner.hasNext()) {
        srPath = newScanner.next();
        if (srPath.endsWith(".sr")) {
          isValid = true;
        } else {
          System.out.println("Please enter a valid path ending with .sr");
          newScanner.nextLine();
        }
      }
    }
    return srPath;
  }

  /**
   * gets the number of questions the user wants to study.
   *
   * @return an integer representing the number of questions the user wants to study.
   */
  public int getNumberOfQuestions(Scanner newScanner) {
    System.out.println("How many questions would you like to practice today?");
    if (newScanner.hasNextInt()) {
      return newScanner.nextInt();
    } else {
      System.out.println("Please enter a valid integer.");
      newScanner.nextLine();
      return getNumberOfQuestions(newScanner);
    }
  }
}

