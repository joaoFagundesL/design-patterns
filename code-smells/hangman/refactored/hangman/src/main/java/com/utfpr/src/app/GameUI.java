package com.utfpr.src.app;

import com.utfpr.src.button.Button;
import com.utfpr.src.button.ButtonBuilder;
import java.awt.*;
import javax.swing.*;

public class GameUI {
  private final JFrame frame;
  private final HangmanGameManager gameManager;
  private JLabel labelChances;

  public GameUI(final JFrame frame, final HangmanGameManager gameManager) {
    this.frame = frame;
    this.gameManager = gameManager;
  }

  public void setupUI() {
    final JLabel lblNewLabel = new JLabel("Chances Restantes: ");
    lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
    lblNewLabel.setBounds(22, 24, 246, 16);
    frame.getContentPane().add(lblNewLabel);

    labelChances = new JLabel(String.valueOf(gameManager.getChances()[0]));
    labelChances.setFont(new Font("Dialog", Font.BOLD, 18));
    labelChances.setBounds(252, 25, 70, 15);
    frame.getContentPane().add(labelChances);

    createLetterButtons();
  }

  public void updateChances(final int chances) {
    labelChances.setText(String.valueOf(chances));
  }

  private void createLetterButtons() {
    final char[] letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    final int startX = 22;
    final int startY = 373;
    final int width = 71;
    final int height = 19;
    final int gap = 94;

    final ButtonBuilder builder = new ButtonBuilder();
    for (int i = 0; i < letras.length; i++) {
      final String letter = String.valueOf(letras[i]);
      final int positionX = startX + i % 10 * gap;
      final int positionY = startY + (i / 10) * (height + 10);

      final Button button =
          builder
              .withLetter(letter)
              .withPosition(positionX, positionY)
              .withSize(width, height)
              .build();

      button.setMediator(gameManager);
      frame.getContentPane().add(button);
    }
  }
}
