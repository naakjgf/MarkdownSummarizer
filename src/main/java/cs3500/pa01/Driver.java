package cs3500.pa01;

import cs3500.pa02.controller.StudySession;
import cs3500.pa02.view.StudySessionView;

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
    if (args.length == 0) {
      StudySessionView view = new StudySessionView();
      int thisInt = view.getNumberOfQuestions();
      String thisString = view.getSrQuestionBankPath();
      new StudySession(thisInt, thisString);
    } else {
      System.out.println("Generating Study Guide & Q&A File...");
      String path = args[0];
      String orderFlag = args[1];
      String outputPath = args[2];
      StudyGuide newStudyGuide = new StudyGuide(path, orderFlag, outputPath);
    }
    // update so that if there are 0 args it does study session and does these next things:
    // make calls to StudySessionView and getNumberOfQuestions and getSrQuestionBankPath
    // then make a new StudySession with those as parameters
    // if there is multiple args the else statement should run which is creating a new StudyGuide
    // which will also be updates to create/populate an .sr file as well.
  }
}