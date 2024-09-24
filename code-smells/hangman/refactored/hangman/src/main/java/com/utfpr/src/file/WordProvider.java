package com.utfpr.src.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class WordProvider {
  public String loadWord(final int gameOption) {
    final String filePath = getFilePath(gameOption);
    try {
      final List<String> palavras = Files.readAllLines(Paths.get(filePath));
      final Random rand = new Random();
      return palavras.get(rand.nextInt(palavras.size()));
    } catch (final IOException e) {
      throw new RuntimeException("Error loading words from file.", e);
    }
  }

  private String getFilePath(final int gameOption) {
    switch (gameOption) {
      case 1:
        return "/home/arch/personal/college/internship/design-patterns/refactoring/hangman/refactored/HangmanGame/hangman/src/animais";
      case 2:
        return "/home/arch/personal/college/internship/design-patterns/refactoring/hangman/refactored/HangmanGame/hangman/src/comidas";
      case 3:
        return "/home/arch/personal/college/internship/design-patterns/refactoring/hangman/refactored/HangmanGame/hangman/src/profissoes";
      default:
        throw new IllegalArgumentException("Invalid option: " + gameOption);
    }
  }
}
