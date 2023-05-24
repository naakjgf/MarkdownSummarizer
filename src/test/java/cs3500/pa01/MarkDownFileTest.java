package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MarkDownFileTest {
  BasicFileAttributes testAttr;
  BasicFileAttributes testAttr2;
  BasicFileAttributes testAttr3;
  Path testpath1;
  Path testpath2;
  Path testpath3;
  Path outPath1;
  Path outPath2;
  Path outPath3;

  @BeforeEach
  void setUp() {
    testpath1 = Path.of("./SampleInputForTesting/VectorArrayDirectory/arrays.md");
    testpath2 = Path.of("./SampleInputForTesting/VectorArrayDirectory/vectors.md");
    testpath3 = Path.of("./SampleInputForTesting/NestedTestDirectory3/RandomNestedExample3.md");
    outPath1 = Path.of("./SampleOutputForTesting/outputResults1.md");
    outPath2 = Path.of("./SampleOutputForTesting/outputResults2.md");
    outPath3 = Path.of("./SampleOutputForTesting/outputResults3.md");
    try {
      testAttr = Files.readAttributes(testpath1, BasicFileAttributes.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      testAttr2 = Files.readAttributes(testpath2, BasicFileAttributes.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      testAttr3 = Files.readAttributes(testpath3, BasicFileAttributes.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Tests the getFileCreationDate method.
   */
  @Test
  public void getFileCreationDate() {
    MarkDownFile file1 = new MarkDownFile(testpath1, testAttr);
    MarkDownFile file2 = new MarkDownFile(testpath2, testAttr2);
    MarkDownFile file3 = new MarkDownFile(testpath3, testAttr3);
    assertEquals(file1.fileCreationDate, file1.getFileCreationDate());
    assertEquals(file2.fileCreationDate, file2.getFileCreationDate());
    assertEquals(file3.fileCreationDate, file3.getFileCreationDate());
  }

  /**
   * Tests the getFileLastModifiedDate method.
   */
  @Test
  public void getFileLastModifiedDate() {
    MarkDownFile file1 = new MarkDownFile(testpath1, testAttr);
    MarkDownFile file2 = new MarkDownFile(testpath2, testAttr2);
    MarkDownFile file3 = new MarkDownFile(testpath3, testAttr3);
    assertEquals(file1.fileLastModifiedDate, file1.getFileLastModifiedDate());
    assertEquals(file2.fileLastModifiedDate, file2.getFileLastModifiedDate());
    assertEquals(file3.fileLastModifiedDate, file3.getFileLastModifiedDate());
  }

  /**
   * Tests the getHeadersAndImportantPoints method by comparing the output to what the
   * Map should be.
   */
  @Test
  public void getHeadersAndImportantPoints() {
    Map<String, ArrayList<String>> testMap1 = Map.of("# Java Arrays", new ArrayList<>(
            List.of("- An **array** is a collection of variables of the same type")),
        "## Creating an Array (Instantiation)", new ArrayList<>(Arrays.asList(
            "- General form:  arrayName = new type[numberOfElements];",
            "- numberOfElements must be a positive Integer.",
            "- Gotcha: Array size is not  modifiable once instantiated.")),
        "## Declaring an Array", new ArrayList<>(Arrays.asList(
            "- General Form: type[] arrayName;",
            "- only creates a reference", "- no array has  actually been created yet")));
    MarkDownFile file1 = new MarkDownFile(testpath1, testAttr);
    assertEquals(testMap1, file1.getHeadersAndImportantPoints());
  }

  /**
   * Tests the getHeaders method.
   */
  @Test
  public void getHeaders() {
    ArrayList<String> file1Strings = new ArrayList<>(Arrays.asList("# Java Arrays",
        "## Declaring an Array", "## Creating an Array (Instantiation)"));
    ArrayList<String> file2Strings = new ArrayList<>(Arrays.asList("# Vectors",
        "## Declaring a vector", "## Adding an element to a vector"));
    ArrayList<String> file3Strings = new ArrayList<>(Arrays.asList("# SQL Basics",
        "## SELECT Statement", "## INSERT Statement", "## UPDATE Statement", "## DELETE Statement",
        "## Joins"));
    MarkDownFile file1 = new MarkDownFile(testpath1, testAttr);
    MarkDownFile file2 = new MarkDownFile(testpath2, testAttr2);
    MarkDownFile file3 = new MarkDownFile(testpath3, testAttr3);
    assertEquals(file1Strings, file1.getHeaders());
    assertEquals(file2Strings, file2.getHeaders());
    assertEquals(file3Strings, file3.getHeaders());
  }

  /**
   * Tests the getFileName method.
   */
  @Test
  public void getFileName() {
    MarkDownFile file1 = new MarkDownFile(testpath1, testAttr);
    MarkDownFile file2 = new MarkDownFile(testpath2, testAttr2);
    MarkDownFile file3 = new MarkDownFile(testpath3, testAttr3);
    assertEquals("arrays.md", file1.getFileName());
    assertEquals("vectors.md", file2.getFileName());
    assertEquals("RandomNestedExample3.md", file3.getFileName());
  }
}