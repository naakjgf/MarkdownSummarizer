package cs3500.pa01;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * A file visitor that visits all markdown files in a directory.
 */

public class MarkdownFileVisitor implements FileVisitor<Path> {
  private ArrayList<MarkDownFile> files;

  public MarkdownFileVisitor() {
    files = new ArrayList<>();
  }

  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
    return CONTINUE;
  }

  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
    if (file.toString().endsWith(".md")) {
      files.add(new MarkDownFile(file, attrs));
    }
    return CONTINUE;
  }

  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) {
    throw new RuntimeException("Failed to visit" + file + "due to " + exc.getMessage());
  }

  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
    return CONTINUE;
  }

  public ArrayList<MarkDownFile> getFiles() {
    return files;
  }
}
