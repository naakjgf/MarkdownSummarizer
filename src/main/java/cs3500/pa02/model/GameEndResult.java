package cs3500.pa02.model;

/**
 * A class that represents the results of a study session.
 */
public class GameEndResult {
  private int hardToEasy;
  private int easyToHard;
  private int numberOfHardQuestionsInBank;
  private int numberOfEasyQuestionsInBank;
  private int numberOfQuestionsAnswered;
  private String supportiveMessage;

  /**
   * Increments the count of hard to easy questions.
   */
  public void incrementHardToEasy() {
    this.hardToEasy++;
  }

  /**
   * Increments the count of easy to hard questions.
   */
  public void incrementEasyToHard() {
    this.easyToHard++;
  }

  /**
   * Sets the number of hard questions based on the bank.
   *
   * @param numberOfHardQuestionsInBank the number of hard questions in the QuestionBank.
   */
  public void setNumberOfHardQuestionsInBank(int numberOfHardQuestionsInBank) {
    this.numberOfHardQuestionsInBank = numberOfHardQuestionsInBank;
  }

  /**
   * Sets the number of easy questions based on the bank.
   *
   * @param numberOfEasyQuestionsInBank the number of easy questions in the QuestionBank.
   */
  public void setNumberOfEasyQuestionsInBank(int numberOfEasyQuestionsInBank) {
    this.numberOfEasyQuestionsInBank = numberOfEasyQuestionsInBank;
  }

  /**
   * Increments the number of questions answered.
   */
  public void incrementNumberOfQuestionsAnswered() {
    this.numberOfQuestionsAnswered++;
  }

  /**
   * Sets the supportive message based on the number of hard to easy questions and total number of
   * questions answered.
   */
  public void setSupportiveMessage() {
    if (hardToEasy == numberOfQuestionsAnswered || numberOfHardQuestionsInBank == 0) {
      this.supportiveMessage = "You got all the hard questions right! You're a genius!";
    } else if (hardToEasy == 0) {
      this.supportiveMessage = "There is room for improvement, I believe in you though!";
    } else if (hardToEasy > numberOfQuestionsAnswered / 2) {
      this.supportiveMessage = "You're making great progress!";
    } else if (hardToEasy > numberOfQuestionsAnswered / 3) {
      this.supportiveMessage = "You're doing well, keep it up!";
    } else {
      this.supportiveMessage = "Great Job! You're a pro!";
    }
  }

  /**
   * Gets the number of hard to easy questions.
   *
   * @return the number of hard to easy questions.
   */
  public int getHardToEasy() {
    return hardToEasy;
  }

  /**
   * Gets the number of easy to hard questions.
   *
   * @return the number of easy to hard questions.
   */
  public int getEasyToHard() {
    return easyToHard;
  }

  /**
   * Gets the number of hard questions in the bank.
   *
   * @return the number of hard questions in the bank.
   */
  public int getNumberOfHardQuestionsInBank() {
    return numberOfHardQuestionsInBank;
  }

  /**
   * Gets the number of easy questions in the bank.
   *
   * @return the number of easy questions in the bank.
   */
  public int getNumberOfEasyQuestionsInBank() {
    return numberOfEasyQuestionsInBank;
  }

  /**
   * Gets the number of questions answered.
   *
   * @return the number of questions answered.
   */
  public int getNumberOfQuestionsAnswered() {
    return numberOfQuestionsAnswered;
  }

  /**
   * Gets the supportive message.
   *
   * @return the supportive message.
   */
  public String getSupportiveMessage() {
    return supportiveMessage;
  }
}
