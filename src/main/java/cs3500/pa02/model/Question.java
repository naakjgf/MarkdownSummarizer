package cs3500.pa02.model;

import java.util.Objects;
import java.util.Scanner;

/**
 * A class that represents a singular question.
 */
public class Question {
  private final String questionText;
  private final String answer;
  private Difficulty difficulty;
  private final Scanner scanner;
  private final GameEndResult gameEndResult;

  /**
   * A constructor for a Question.
   *
   * @param lineFromSrFile the line from the sr file.
   */
  public Question(String lineFromSrFile, GameEndResult gameEndResult) {
    this.gameEndResult = gameEndResult;
    scanner = new Scanner(lineFromSrFile).useDelimiter(":::");
    this.questionText = parseQuestionText();
    this.answer = parseAnswer();
    this.difficulty = parseDifficulty();
    scanner.close();
  }

  /**
   * Gets the question text of this specific question.
   *
   * @return A String of the question text.
   */
  public String getQuestionText() {
    return questionText;
  }

  /**
   * Gets the answer to this specific question.
   *
   * @return A String of the answer to the question.
   */
  public String getAnswer() {
    return answer;
  }

  /**
   * Gets the difficulty of this specific question.
   *
   * @return the difficulty of the question.
   */
  public Difficulty getDifficulty() {
    return difficulty;
  }

  /**
   * Changes the difficulty of this specific question to the given difficulty if it is not already
   * that difficulty.
   *
   * @param newDifficulty the new difficulty of the question.
   */
  public void changeDifficulty(Difficulty newDifficulty) {
    Objects.requireNonNull(newDifficulty);
    if (difficulty == newDifficulty) {
      return;
    } else if (newDifficulty == Difficulty.EASY) {
      gameEndResult.incrementHardToEasy();
    } else {
      gameEndResult.incrementEasyToHard();
    }
    this.difficulty = newDifficulty;
  }

  /**
   * Parses the difficulty from the line from the sr file.
   *
   * @return the difficulty level of the question.
   */
  private Difficulty parseDifficulty() {
    if (scanner.hasNext()) {
      if (scanner.next().equalsIgnoreCase("easy")) {
        return Difficulty.EASY;
      } else {
        return Difficulty.HARD;
      }
    } else {
      System.out.println("No difficulty found in line from sr file line, may be a new question"
          + " with no difficulty, setting difficulty to the default of Hard");
      return Difficulty.HARD;
    }
  }

  /**
   * Parses the question text from the line from the sr file.
   *
   * @return the question text.
   */
  private String parseQuestionText() {
    if (scanner.hasNext()) {
      return scanner.next();
    } else {
      throw new IllegalArgumentException("No question text found in line from sr file line");
    }
  }

  /**
   * Parses the answer from the line from the sr file.
   *
   * @return the answer to the question.
   */
  private String parseAnswer() {
    if (scanner.hasNext()) {
      return scanner.next();
    } else {
      throw new IllegalArgumentException("No answer found in line from sr file line");
    }
  }

  /**
   * Returns a string representation of this question, formats it as follows:
   * "questionTest:::answer:::difficulty"
   *
   * @return a string representation of this question
   */
  @Override
  public String toString() {
    return "[[" + questionText + ":::" + answer + ":::" + difficulty + "]]";
  }
}
