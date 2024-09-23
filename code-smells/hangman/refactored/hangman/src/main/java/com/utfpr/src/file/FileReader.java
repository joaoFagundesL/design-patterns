package com.utfpr.src.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileReader {

  private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());

  public List<String> loadWords(final String filePath) {
    final List<String> words = new ArrayList<>();
    final File file = new File(filePath);

    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNextLine()) {
        words.add(scanner.nextLine());
      }
    } catch (FileNotFoundException e) {
      LOGGER.log(Level.SEVERE, "An error occurred while trying to open the file", e);
    }

    return words;
  }
}
