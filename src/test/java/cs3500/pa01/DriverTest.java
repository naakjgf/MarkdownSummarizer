package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class DriverTest {

  @Test
  public void testMain() {
    String[] args = new String[3];
    args[0] = "./SampleInputForTesting/VectorArrayDirectory";
    args[1] = "fileName";
    args[2] = "./SampleOutputForTesting/outputResult3.md";
    Driver.main(args);
    try {
      byte[] f1 = Files.readAllBytes(Path.of("./SampleOutputForTesting/outputResult3.md"));
      byte[] f2 = Files.readAllBytes(
          Path.of("./SampleOutputForTesting/PATrueOutputByFileName.md"));
      assertArrayEquals(f1, f2);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void visitFileFailedTest() {
    MarkdownFileVisitor newVisitor = new MarkdownFileVisitor();
    assertThrows(RuntimeException.class, () -> {
      newVisitor.visitFileFailed(Path.of("./EmptyDirectoryForTesting"),
          new IOException("Couldn't run"));
    });
  }
}