package com.utfpr.src.app;

import com.utfpr.src.file.WordProvider;
import com.utfpr.src.mediator.Mediator;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class HangmanGameManager implements Mediator {
  private JFrame frame;
  private String palavraDoJogo;
  private final List<JLabel> campos = new ArrayList<>();
  private int[] chances = {4};
  private JLabel labelChances;
  private GuessProcessor guessProcessor;
  private GameUI gameUI;

  private static final Logger LOGGER = Logger.getLogger(HangmanGameManager.class.getName());

  public static void newScreen(final int gameOption) {
    EventQueue.invokeLater(
        () -> {
          try {
            final HangmanGameManager window = new HangmanGameManager(gameOption);
            window.frame.setVisible(true);
          } catch (Exception exception) {
            LOGGER.log(
                Level.SEVERE, "An error occurred while trying to open the window.", exception);
          }
        });
  }

  public HangmanGameManager(final int gameOption) {
    initialize(gameOption);
  }

  private void initialize(final int gameOption) {
    WordProvider wordProvider = new WordProvider();
    palavraDoJogo = wordProvider.loadWord(gameOption);

    labelChances = new JLabel("Chances: " + chances[0]);

    frame = new JFrame();
    frame.setBounds(100, 100, 800, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    gameUI = new GameUI(frame, this);
    gameUI.setupUI();

    guessProcessor = new GuessProcessor(this);
    setupWordLabels();
  }

  private void setupWordLabels() {
    int espaco = 129;
    for (int i = 0; i < palavraDoJogo.length(); i++) {
      JLabel label = new JLabel("__");
      label.setBounds(espaco, 128, 53, 19);
      frame.getContentPane().add(label);
      campos.add(label);
      espaco += 20;
    }
  }

  @Override
  public void processGuess(int encontrou) {
    if (encontrou == 0) {
      if (chances[0] > 0) {
        chances[0]--;
        gameUI.updateChances(chances[0]);
      } else {
        JOptionPane.showMessageDialog(
            null,
            "Você Perdeu! A palavra é: " + palavraDoJogo,
            "Mensagem",
            JOptionPane.WARNING_MESSAGE);
        mensagem();
      }
    }
  }

  @Override
  public int checkGuess(char letra) {
    return guessProcessor.checkGuess(letra);
  }

  public void mensagem() {
    int dialogButton = JOptionPane.YES_NO_OPTION;
    int dialogResult =
        JOptionPane.showConfirmDialog(null, "Deseja Continuar?", "Mensagem", dialogButton);

    if (dialogResult == 0) {
      frame.dispose();
      Option.main(null);
    } else {
      System.exit(0);
      frame.dispose();
    }
  }

  @Override
  public String getPalavraDoJogo() {
    return palavraDoJogo;
  }

  @Override
  public int[] getChances() {
    return chances.clone();
  }

  @Override
  public JLabel getLabelChances() {
    return labelChances;
  }

  @Override
  public List<JLabel> getCampos() {
    return campos;
  }
}
