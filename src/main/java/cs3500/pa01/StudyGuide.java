package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class StudyGuide {

  private ArrayList<MarkDownFile>  files;

    public StudyGuide(String path, String orderFlag, String outputPath) {
      MarkdownFileVisitor markdownFileVisitor = new MarkdownFileVisitor();
      try {
        Files.walkFileTree(Path.of(path), markdownFileVisitor);
      } catch (IOException e) {
        e.printStackTrace();
      }
      files = markdownFileVisitor.getFiles();
      orderFiles(orderFlag);
      generateStudyGuide(Path.of(outputPath));
      generateQuestionsAndAnswersFile();
    }

  /**
   * Generates a study guide in the file which is decided by the outputPath
   * using the important points as well as the headers to generate a comprehensive study guide.
   *
   * @param outputPath the path to the output file
   */
  public void generateStudyGuide(Path outputPath) {
    StringBuilder sb = new StringBuilder();
    for (MarkDownFile file : files) {
      Map<String, ArrayList<String>> headersAndImportantPoints = file.getHeadersAndImportantPoints();

      for (String header : file.getHeaders()) {
        ArrayList<String> importantPoints = headersAndImportantPoints.get(header);
        sb.append(header).append("\n");
        for (String point : importantPoints) {
          sb.append(point).append("\n");
        }
        sb.append("\n");
      }
    }
    try {
      Files.writeString(outputPath, sb.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

    /**
     * Orders the files in the study guide based on the order flag.
     *
     * @param orderFlag - the flag that determines how to order the files, can be one of
     * "createDate", "fileName", or "modDate".
     */
  public void orderFiles(String orderFlag) {
    switch (orderFlag) {
      case "createDate" -> files.sort(Comparator.comparing(MarkDownFile::getFileCreationDate));
      case "fileName" -> files.sort(Comparator.comparing(MarkDownFile::getFileName));
      case "modDate" -> files.sort(Comparator.comparing(MarkDownFile::getFileLastModifiedDate));
      default -> throw new IllegalArgumentException("Invalid order flag");
    }
  }

  /**
   * Generates a file with the questions and answers from the study guide.
   * The file is named "QuestionsAndAnswers.sr".
   */
  public void generateQuestionsAndAnswersFile() {
    StringBuilder sb = new StringBuilder();
    for (MarkDownFile file : files) {
      for (String questionAndAnswer : file.getQuestionsAndAnswers()) {
        sb.append(questionAndAnswer).append("\n");
      }
    }
    try {
        Files.writeString(Path.of(
            "OutputStudyGuideFiles/QuestionsAndAnswers.sr"), sb.toString());
        } catch (IOException e) {
        e.printStackTrace();
    }
  }

  /**
   * Gets the files in the study guide.
   *
   * @return the files in the study guide.
   **/
  public ArrayList<MarkDownFile> getFiles() {
    return files;
  }
}
