package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudyGuideTest {
  String filePath;
  String filePath2;
  String sortType;
  String sortType2;
  String sortType3;
  String sortType4;
  String outputFilePath;
  String outputFilePath2;
  Path outputPath;
  Path outputPath2;

  @BeforeEach
  public void setUp() {
    filePath = "./SampleInputForTesting/VectorArrayDirectory/arrays.md";
    filePath2 = "./SampleInputForTesting/VectorArrayDirectory/vectors.md";
    sortType = "createDate";
    sortType2 = "fileName";
    sortType3 = "modDate";
    sortType4 = "alphabetical";
    outputFilePath = "./SampleOutputForTesting/outputResults1.md";
    outputFilePath2 = "./SampleOutputForTesting/outputResults2.md";
    outputPath = Path.of(outputFilePath);
    outputPath2 = Path.of(outputFilePath2);
  }

  /**
   * Tests the generateStudyGuide method.
   * Compares the generated file to a file with the intended contents.
   */
  @Test
  public void generateStudyGuide() {
    String outputFilePath = "./SampleOutputForTesting/outputResults1.md";
    String outputFilePath2 = "./SampleOutputForTesting/outputResults2.md";
    StudyGuide guide = new StudyGuide(filePath, sortType, outputFilePath);
    StudyGuide guide2 = new StudyGuide(filePath2, sortType2, outputFilePath2);
    guide.generateStudyGuide(outputPath);
    guide2.generateStudyGuide(outputPath2);
    try {
      byte[] f1 = Files.readAllBytes(outputPath);
      byte[] f2 = Files.readAllBytes(
          Path.of("./SampleOutputForTesting/outputResultsArray.md"));
      assertArrayEquals(f1, f2);
      byte[] f3 = Files.readAllBytes(outputPath2);
      byte[] f4 = Files.readAllBytes(
          Path.of("./SampleOutputForTesting/outputResultsVector.md"));
      assertArrayEquals(f3, f4);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Tests the orderFiles method.
   * Compares the order of the files to the intended order by looking at the first element.
   */
  @Test
  public void orderFilesTest() {
    StudyGuide guide = new StudyGuide("./SampleInputForTesting", sortType, outputFilePath);

    ArrayList<MarkDownFile> creationDateFiles = guide.getFiles();

    assertTrue(creationDateFiles.get(0).getFileCreationDate()
        <= creationDateFiles.get(1).getFileCreationDate());
    assertTrue(creationDateFiles.get(1).getFileCreationDate()
        <= creationDateFiles.get(2).getFileCreationDate());

    StudyGuide guide2 = new StudyGuide("./SampleInputForTesting", sortType2, outputFilePath);

    ArrayList<MarkDownFile> filenameFiles = guide2.getFiles();

    assertTrue(filenameFiles.get(0).getFileName()
        .compareTo(filenameFiles.get(1).getFileName()) <= 0);
    assertTrue(filenameFiles.get(1).getFileName()
        .compareTo(filenameFiles.get(2).getFileName()) <= 0);

    StudyGuide guide3 = new StudyGuide("./SampleInputForTesting", sortType3, outputFilePath);

    ArrayList<MarkDownFile> modifiedFiles = guide3.getFiles();

    assertTrue(modifiedFiles.get(0).getFileLastModifiedDate()
        <= modifiedFiles.get(1).getFileLastModifiedDate());
    assertTrue(modifiedFiles.get(1).getFileLastModifiedDate()
        <= modifiedFiles.get(2).getFileLastModifiedDate());

    assertThrows(IllegalArgumentException.class, () ->
        new StudyGuide("./SampleInputForTesting", sortType4, outputFilePath));
  }
}