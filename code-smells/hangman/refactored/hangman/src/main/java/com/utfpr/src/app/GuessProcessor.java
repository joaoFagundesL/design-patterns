package com.utfpr.src.app;

import java.text.Normalizer;
import java.util.List;
import javax.swing.*;

public class GuessProcessor {
  private final HangmanGameManager gameManager;

  public GuessProcessor(final HangmanGameManager gameManager) {
    this.gameManager = gameManager;
  }

  public int checkGuess(final char letra) {
    final String palavraDoJogo = normalizeWord(gameManager.getPalavraDoJogo());
    int encontrou = 0;

    for (int i = 0; i < palavraDoJogo.length(); i++) {
      if (isLetterMatched(letra, palavraDoJogo.charAt(i))) {
        updateCampo(i);
        encontrou++;
      }
    }

    if (isGameWon()) {
      displayWinMessage();
      gameManager.mensagem();
    } else {
      gameManager.processGuess(encontrou);
    }

    return encontrou;
  }

  private String normalizeWord(String palavraDoJogo) {
    palavraDoJogo = Normalizer.normalize(palavraDoJogo, Normalizer.Form.NFD);
    return palavraDoJogo.replaceAll("[^\\p{ASCII}]", "");
  }

  private boolean isLetterMatched(final char letra, final char character) {
    return Character.toLowerCase(letra) == character || Character.toUpperCase(letra) == character;
  }

  private void updateCampo(final int index) {
    final List<JLabel> campos = gameManager.getCampos();
    campos.get(index).setText(String.valueOf(gameManager.getPalavraDoJogo().charAt(index)));
  }

  private boolean isGameWon() {
    for (final JLabel campo : gameManager.getCampos()) {
      if (campo.getText().equals("__")) {
        return false;
      }
    }
    return true;
  }

  private void displayWinMessage() {
    JOptionPane.showMessageDialog(null, "Você Ganhou!", "Mensagem", JOptionPane.WARNING_MESSAGE);
  }
}
