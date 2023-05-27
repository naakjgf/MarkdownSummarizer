package cs3500.pa02.controller;

import cs3500.pa02.model.Difficulty;
import cs3500.pa02.model.GameEndResult;
import cs3500.pa02.model.Question;
import cs3500.pa02.model.QuestionBank;
import cs3500.pa02.view.GameEndResultView;
import cs3500.pa02.view.StudySessionView;
import java.util.Scanner;

/**
 * A class that represents a study session.
 */
public class StudySession {
  private int numberOfQuestions;
  private final QuestionBank questionBank;
  private final StudySessionView view;
  private final GameEndResultView gameEndResultView;
  private final GameEndResult gameEndResult;
  private final Scanner scanner;

  /**
   * A constructor for a study session.
   *
   * @param srFilePath the String path of the sr file.
   * @param scanner the scanner to take in user input.
   */
  public StudySession(String srFilePath, Scanner scanner) {
    this.scanner = scanner;
    this.view = new StudySessionView();
    this.numberOfQuestions = view.getNumberOfQuestions(this.scanner);
    this.gameEndResult = new GameEndResult();
    this.gameEndResultView = new GameEndResultView(gameEndResult);
    this.questionBank = new QuestionBank(srFilePath, gameEndResult);
    startStudySession();
  }

  /**
   * Starts the study session and preforms all controller actions.
   */
  public void startStudySession() {
    if (questionBank.getTotalNumberOfQuestions() < numberOfQuestions) {
      this.numberOfQuestions = questionBank.getTotalNumberOfQuestions();
    }
    System.out.println(
        "\nWelcome to the Study Session! You will be asked " + numberOfQuestions + " questions.");
    for (int i = 0; i < numberOfQuestions; i++) {
      Question currentQuestion = questionBank.getNextQuestion(i);
      view.displayQuestion(currentQuestion);
      chooseOptionAndRespond(currentQuestion);
      gameEndResult.incrementNumberOfQuestionsAnswered();
    }
    questionBank.endingSort();
    questionBank.saveQuestionsBackToFile();
    gameEndResultView.display();
  }

  /**
   * Chooses an option and responds to the user's input.
   *
   * @param currentQuestion the current question being asked.
   */
  private void chooseOptionAndRespond(Question currentQuestion) {
    int choice = view.displayAndTakeInOptions(this.scanner);
    switch (choice) {
      case 1 -> currentQuestion.changeDifficulty(Difficulty.EASY);
      case 2 -> currentQuestion.changeDifficulty(Difficulty.HARD);
      case 3 -> view.displayAnswer(currentQuestion, this.scanner);
      case 4 -> {
        gameEndResultView.display();
        System.exit(0);
      }
      default -> {
        System.out.println("Please enter a valid integer representing one of the options.");
        chooseOptionAndRespond(currentQuestion);
      }
    }
  }
}
