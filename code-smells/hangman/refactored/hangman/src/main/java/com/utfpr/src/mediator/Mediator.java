package com.utfpr.src.mediator;

import java.util.List;
import javax.swing.JLabel;

public interface Mediator {
  void processGuess(int encontrou);

  int checkGuess(char letra);

  String getPalavraDoJogo();

  int[] getChances();

  JLabel getLabelChances();

  List<JLabel> getCampos();
}
