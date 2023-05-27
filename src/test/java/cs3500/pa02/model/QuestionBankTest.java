package cs3500.pa02.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionBankTest {
  private static final String SR_FILE_PATH = "./InputStudyGuideFiles/TestSrFile.sr";
  private static final String TEMP_SR_FILE_PATH =
      "./InputStudyGuideFiles/TestSrFileTemp.sr";
  private QuestionBank questionBank;

  @BeforeEach
  void setUp() {
    try {
      Files.copy(Path.of(SR_FILE_PATH), Path.of(TEMP_SR_FILE_PATH),
          StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    questionBank = new QuestionBank(TEMP_SR_FILE_PATH, new GameEndResult());
  }

  @Test
  void testLoadQuestionsFromFile() {
    assertTrue(questionBank.getTotalNumberOfQuestions() > 0);
  }

  @Test
  void testSortQuestions() {
    // Assuming that there are both HARD and EASY questions in the original file
    assertNotEquals(questionBank.getTotalNumberOfQuestions(),
        questionBank.getNextQuestion(0).getDifficulty());
  }

  @Test
  void testSaveQuestionsBackToFile() {
    int initialTotal = questionBank.getTotalNumberOfQuestions();
    questionBank.endingSort();
    questionBank.saveQuestionsBackToFile();

    QuestionBank testBank = new QuestionBank(TEMP_SR_FILE_PATH, new GameEndResult());
    assertEquals(initialTotal, testBank.getTotalNumberOfQuestions());
  }
  @Test
  public void testSaveQuestionsBackToFile2() {
    Question firstQuestion = questionBank.getNextQuestion(0);
    firstQuestion.changeDifficulty(Difficulty.EASY);

    questionBank.saveQuestionsBackToFile();

    QuestionBank newQuestionBank = new QuestionBank(TEMP_SR_FILE_PATH, new GameEndResult());

    Question persistedQuestion = newQuestionBank.getNextQuestion(
        newQuestionBank.getTotalNumberOfQuestions() - 1);
    assertEquals(firstQuestion.getDifficulty(), persistedQuestion.getDifficulty());
  }

  @Test
  void testRandomizeQuestionsOrder() {
    QuestionBank newBank1 = new QuestionBank(TEMP_SR_FILE_PATH, new GameEndResult());
    QuestionBank newBank2 = new QuestionBank(TEMP_SR_FILE_PATH, new GameEndResult());

    assertNotEquals(newBank1.getNextQuestion(0), newBank2.getNextQuestion(0));
  }

  @Test
  void testGetNextQuestion() {
    assertNotNull(questionBank.getNextQuestion(0));
  }
  @Test
  public void testGetNextQuestion2() {
    int hardQuestionsSize = questionBank.getTotalNumberOfQuestions() - 1;
    Question expectedQuestion = questionBank.getNextQuestion(hardQuestionsSize);
    assertEquals(expectedQuestion.getDifficulty(), Difficulty.EASY);
  }

  @Test
  public void testGetNextQuestion3() {
    Question firstQuestion = questionBank.getNextQuestion(0);
    firstQuestion.changeDifficulty(Difficulty.EASY);
    Question secondQuestion = questionBank.getNextQuestion(1);
    secondQuestion.changeDifficulty(Difficulty.EASY);

    questionBank.saveQuestionsBackToFile();

    QuestionBank newQuestionBank = new QuestionBank(TEMP_SR_FILE_PATH, new GameEndResult());
    int hardQuestionsSize = newQuestionBank.getTotalNumberOfQuestions() - 1;
    Question expectedQuestion = newQuestionBank.getNextQuestion(hardQuestionsSize);
    assertEquals(expectedQuestion.getDifficulty(), Difficulty.EASY);
  }

  @Test
  void testEndingSort() {
    Question hardQuestion = questionBank.getNextQuestion(0);
    hardQuestion.changeDifficulty(Difficulty.EASY);

    questionBank.endingSort();
    assertNotEquals(hardQuestion, questionBank.getNextQuestion(0));
  }

  @Test
  void testGetTotalNumberOfQuestions() {
    assertTrue(questionBank.getTotalNumberOfQuestions() > 0);
  }
}