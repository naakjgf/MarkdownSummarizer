package cs3500.pa01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class that represents a markdown file.
 */
public class MarkDownFile {
  private String fileName;
  long fileCreationDate;
  long fileLastModifiedDate;
  private Map<String, ArrayList<String>> headersAndImportantPoints;
  private ArrayList<String> headers;
  private ArrayList<String> questionsAndAnswers;

  /**
   * A constructor for a markdown file.
   *
   * @param file the path of a file.
   * @param attrs the attributes of a file.
   */
  public MarkDownFile(Path file, BasicFileAttributes attrs) {
    this.fileName = file.getFileName().toString();
    this.fileCreationDate = retrieveFileCreationEpoch(attrs);
    this.fileLastModifiedDate = retrieveFileLastModifiedEpoch(attrs);
    this.headersAndImportantPoints = new HashMap<>();
    this.headers = new ArrayList<>();
    this.questionsAndAnswers = new ArrayList<>();
    collectHeadersAndImportantPoints(file);
  }

  /**
   * gets the list of questions and answers.
   *
   * @return an ArrayList with questions and answers.
   */
  public ArrayList<String> getQuestionsAndAnswers() {
    return questionsAndAnswers;
  }

  /**
   * gets the file creation date.
   *
   * @return the file creation date.
   */
  public long getFileCreationDate() {
    return fileCreationDate;
  }

  /**
   * gets the last modified date of the file.
   *
   * @return the last modified date of the file.
   */
  public long getFileLastModifiedDate() {
    return fileLastModifiedDate;
  }

  /**
   * gets a map of headers and important points.
   *
   * @return a map of headers and important points.
   */
  public Map<String, ArrayList<String>> getHeadersAndImportantPoints() {
    return headersAndImportantPoints;
  }

  /**
   * gets the headers.
   *
   * @return headers.
   */
  public ArrayList<String> getHeaders() {
    return headers;
  }

  /**
   * extracts the important points from the string passed in.
   *
   * @param content the string to extract important points from.
   * @return the list of important points.
   */
  private ArrayList<String> extractImportantPoints(String content) {
    Pattern importantPattern = Pattern.compile("\\[\\[(.+?)\\]\\]", Pattern.DOTALL);
    Matcher matcher = importantPattern.matcher(content.replaceAll("- ", " "));
    ArrayList<String> theseImportantPoints = new ArrayList<>();

    while (matcher.find()) {
      theseImportantPoints.add("- " + matcher.group(1).replaceAll("\n", " "));
    }

    return extractQuestionAndAnswers(theseImportantPoints);
  }

  /**
   * Extracts the questions and answers from the list of important points.
   *
   * @param importantPoints the list of important points.
   * @return the modified important points list.
   */
  private ArrayList<String> extractQuestionAndAnswers(ArrayList<String> importantPoints) {
    ArrayList<String> pointsToRemove = new ArrayList<>();
    for (String importantPoint : importantPoints) {
      if (importantPoint.contains(":::")) {
        String cleanedPoint = importantPoint.replaceAll("- ", "");
        cleanedPoint = cleanedPoint.replaceAll("\\s+", " ");
        pointsToRemove.add(importantPoint);
        questionsAndAnswers.add(cleanedPoint);
      }
    }
    for (String pointToRemove : pointsToRemove) {
      importantPoints.remove(pointToRemove);
    }
    return importantPoints;
  }

  /**
   * Collects the headers and important points from the file.
   *
   * @param file the file to collect the headers and important points from.
   */
  private void collectHeadersAndImportantPoints(Path file) {
    try {
      File inputFile = new File(file.toString());
      Scanner scanner = new Scanner(inputFile);
      StringBuilder tempContentHolder = new StringBuilder();
      if (inputFile.length() == 0) {
        System.out.println("This input file is empty: " + file.getFileName());
        return;
      }

      String headerLine = scanner.nextLine().trim();
      while (scanner.hasNextLine()) {
        String innerLine = scanner.nextLine();

        if (innerLine.startsWith("#")) {
          headersAndImportantPoints.put(headerLine, extractImportantPoints(
              tempContentHolder.toString()));
          headers.add(headerLine);
          headerLine = innerLine;
          tempContentHolder = new StringBuilder();
        } else {
          tempContentHolder.append(innerLine);
        }
      }

      headersAndImportantPoints.put(headerLine, extractImportantPoints(
          tempContentHolder.toString()));
      headers.add(headerLine);
      scanner.close();

    } catch (IOException e) {
      System.out.println("An error occurred while reading the input file.");
      e.printStackTrace();
    }
  }

  /**
   * Retrieves the file creation epoch.
   *
   * @param attrs the attributes of the file.
   * @return the file creation epoch.
   */
  private long retrieveFileCreationEpoch(BasicFileAttributes attrs) {
    return attrs.creationTime().toMillis();
  }

  /**
   * Retrieves the file last modified epoch.
   *
   * @param attrs the attributes of the file.
   * @return the file last modified epoch.
   */
  private long retrieveFileLastModifiedEpoch(BasicFileAttributes attrs) {
    return attrs.lastModifiedTime().toMillis();
  }


  /**
   * Gets the questions and answers.
   *
   * @return the questions and answers.
   */
  public String getFileName() {
    return fileName;
  }
}
