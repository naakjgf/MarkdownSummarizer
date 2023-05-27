package cs3500;

import cs3500.pa01.StudyGuide;
import cs3500.pa02.controller.StudySession;
import cs3500.pa02.view.StudySessionView;
import java.io.File;
import java.util.Scanner;

/**
 * This is the main driver of this project.
 */
public class Driver {

  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    if (args.length == 0) {
      StudySessionView view = new StudySessionView();
      String srFilePath;
      File inputFile;
      do {
        srFilePath = view.getSrQuestionBankPath(scanner);
        inputFile = new File(srFilePath);
        if (inputFile.length() == 0) {
          System.out.println("This given input file is empty or does not exist: " + srFilePath
              + "\nPlease enter a valid input file.\n");
        }
      } while (inputFile.length() == 0);
      System.out.println("[Generating Study Session...]");
      new StudySession(srFilePath, scanner);
    } else {
      System.out.println("Generating Study Guide & Q&A File...");
      String path = args[0];
      String orderFlag = args[1];
      String outputPath = args[2];
      StudyGuide newStudyGuide = new StudyGuide(path, orderFlag, outputPath);
    }
  }
}