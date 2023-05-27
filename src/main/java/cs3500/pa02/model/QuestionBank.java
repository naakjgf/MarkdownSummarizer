package cs3500.pa02.model;

import static java.util.Collections.shuffle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class that represents a question bank, a collection of questions.
 */
public class QuestionBank {
  private final ArrayList<Question> hardQuestions;
  private final ArrayList<Question> easyQuestions;
  private final ArrayList<Question> unsortedQuestions;
  private final String srFilePath;
  private final GameEndResult gameEndResult;

  /**
   * A constructor for a question bank.
   *
   * @param srFilePath the String path of the sr file.
   */
  public QuestionBank(String srFilePath, GameEndResult gameEndResult) {
    this.gameEndResult = gameEndResult;
    this.hardQuestions = new ArrayList<>();
    this.easyQuestions = new ArrayList<>();
    this.unsortedQuestions = new ArrayList<>();
    this.srFilePath = srFilePath;
    loadQuestionsFromFile();
    sortQuestions();
    randomizeQuestionsOrder();
  }

  /**
   * Loads the Q&As from the sr file into the question bank, more specifically the unsorted list of
   * questions.
   */
  private void loadQuestionsFromFile() {
    File inputFile = new File(srFilePath);
    try {
      Scanner scanner = new Scanner(inputFile);
      StringBuilder tempContentHolder = new StringBuilder();
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        tempContentHolder.append(line).append("\n");
      }
      Pattern importantPattern = Pattern.compile("\\[\\[(.+?)\\]\\]", Pattern.DOTALL);
      Matcher matcher = importantPattern.matcher(tempContentHolder);

      while (matcher.find()) {
        Question question = new Question(matcher.group(1).replaceAll("\n", " "), gameEndResult);
        unsortedQuestions.add(question);
      }
    } catch (FileNotFoundException e) {
      System.out.println("The sr file was not found, could not be opened, or could not be read.");
      throw new RuntimeException(e);
    }
  }

  /**
   * Sorts the unsorted questions into the easy and hard lists based on their difficulty.
   */
  private void sortQuestions() {
    for (Question question : unsortedQuestions) {
      if (question.getDifficulty() == Difficulty.EASY) {
        easyQuestions.add(question);
      } else {
        hardQuestions.add(question);
      }
    }
  }

  /**
   * Saves the questions writing them back to the sr file in their proper format.
   */
  public void saveQuestionsBackToFile() {
    StringBuilder sb = new StringBuilder();
    for (Question question : hardQuestions) {
      sb.append(question.toString()).append("\n");
    }
    for (Question question : easyQuestions) {
      sb.append(question.toString()).append("\n");
    }
    try {
      Files.writeString(Path.of(srFilePath), sb.toString());
    } catch (IOException e) {
      System.out.println("The sr file could not be written to.");
      throw new RuntimeException(e);
    }
  }

  /**
   * Randomizes the order of the questions in the easy and hard lists.
   */
  private void randomizeQuestionsOrder() {
    shuffle(easyQuestions);
    shuffle(hardQuestions);
  }

  /**
   * Gets the next question in the question bank.
   *
   * @param index the number that represents what question the user is currently on.
   * @return the next question in the question bank.
   */
  public Question getNextQuestion(int index) {
    if (index >= hardQuestions.size()) {
      int indexOfEasyQuestion = index - hardQuestions.size();
      return easyQuestions.get(indexOfEasyQuestion);
    } else {
      return hardQuestions.get(index);
    }
  }

  /**
   * Meant to be called at the end of the StudySession, moves the questions in each list to their
   * proper difficulty list. Also sets GameEndResult's numberOfHardQuestionsInBank,
   * numberOfEasyQuestionsInBank, as well as the supportive message.
   */
  public void endingSort() {
    Iterator<Question> iterator = hardQuestions.iterator();
    while (iterator.hasNext()) {
      Question question = iterator.next();
      if (question.getDifficulty() == Difficulty.EASY) {
        easyQuestions.add(question);
        iterator.remove();
      }
    }
    iterator = easyQuestions.iterator();
    while (iterator.hasNext()) {
      Question question = iterator.next();
      if (question.getDifficulty() == Difficulty.HARD) {
        hardQuestions.add(question);
        iterator.remove();
      }
    }

    gameEndResult.setNumberOfHardQuestionsInBank(hardQuestions.size());
    gameEndResult.setNumberOfEasyQuestionsInBank(easyQuestions.size());
    gameEndResult.setSupportiveMessage();
  }

  /**
   * Gets the number of questions in the question bank.
   *
   * @return the number of questions in the question bank.
   */
  public int getTotalNumberOfQuestions() {
    return hardQuestions.size() + easyQuestions.size();
  }
}