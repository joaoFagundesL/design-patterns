package com.utfpr.src.app;

import com.utfpr.src.button.Button;
import com.utfpr.src.button.ButtonBuilder;
import com.utfpr.src.file.FileReader;
import com.utfpr.src.mediator.Mediator;
import java.awt.EventQueue;
import java.awt.Font;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class HangmanGameManager implements Mediator {
  private JFrame frame;
  private String palavraDoJogo;
  private final List<JLabel> campos = new ArrayList<>();
  private final int[] chances = {4};
  private JLabel labelChances;
  private int encontrou;
  ButtonBuilder builder = new ButtonBuilder();

  private final FileReader fileReader = new FileReader();
  private static final Logger LOGGER = Logger.getLogger(Option.class.getName());

  public static void newScreen(final int gameOption) {
    EventQueue.invokeLater(
        new Runnable() {
          @Override
          public void run() {
            try {
              final HangmanGameManager window = new HangmanGameManager(gameOption);
              window.frame.setVisible(true);
            } catch (Exception exception) {
              LOGGER.log(
                  Level.SEVERE, "An error occurred while trying to open the window.", exception);
            }
          }
        });
  }

  public HangmanGameManager(final int gameOption) {
    initialize(gameOption);
  }

  public void mensagem() {
    final int dialogButton = JOptionPane.YES_NO_OPTION;
    final int dialogResult =
        JOptionPane.showConfirmDialog(null, "Deseja Continuar?", "Mensagem", dialogButton);

    if (dialogResult == 0) {
      frame.dispose();
      Option.main(null);
    } else {
      System.exit(0);
      frame.dispose();
      frame.setVisible(false);
    } // Usuario saiu do programa
  }

  private void initialize(final int gameOption) {
    frame = new JFrame();
    frame.setBounds(100, 100, 450, 300);
    frame.setSize(800, 600);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    final JLabel lblNewLabel = new JLabel("Chances Restantes: ");
    lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
    lblNewLabel.setBounds(22, 24, 246, 16);
    frame.getContentPane().add(lblNewLabel);

    final String chancesString = String.valueOf(chances[0]);
    labelChances = new JLabel(chancesString);
    labelChances.setFont(new Font("Dialog", Font.BOLD, 18));
    labelChances.setBounds(252, 25, 70, 15);
    frame.getContentPane().add(labelChances);

    final String filePath = getFilePath(gameOption);
    final List<String> palavras = fileReader.loadWords(filePath);

    final Random rand = new Random();
    palavraDoJogo = palavras.get(rand.nextInt(palavras.size()));

    int espaco = 129;

    for (int i = 0; i < palavraDoJogo.length(); i++) {
      final JLabel label = new JLabel("__");
      label.setBounds(espaco, 128, 53, 19);
      frame.getContentPane().add(label);
      campos.add(label);
      espaco += 20;
    }

    final char[] letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    final int startX = 22;
    final int startY = 373;
    final int width = 71;
    final int height = 19;
    final int gap = 94;

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

      button.setMediator(this);

      frame.getContentPane().add(button);
    }
  }

  private String getFilePath(final int gameOption) {
    String filePath = "";
    switch (gameOption) {
      case 1:
        filePath =
            "/home/arch/personal/college/internship/design-patterns/refactoring/hangman/refactored/HangmanGame/hangman/src/animais";
        break;
      case 2:
        filePath =
            "/home/arch/personal/college/internship/design-patterns/refactoring/hangman/refactored/HangmanGame/hangman/src/comidas";
        break;
      case 3:
        filePath =
            "/home/arch/personal/college/internship/design-patterns/refactoring/hangman/refactored/HangmanGame/hangman/src/profissoes";
        break;
      default:
        throw new IllegalArgumentException("Invalid option: " + gameOption);
    }

    return filePath;
  }

  @Override
  public void processGuess(
      int encontrou, int[] chances, JLabel labelChances, String palavraDoJogo) {
    if (encontrou == 0) {
      if (chances[0] != 0) {
        chances[0] = chances[0] - 1;
        labelChances.setText(String.valueOf(chances[0]));
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
  public int checkGuess(String palavraDoJogo, char letra, List<JLabel> campos, JButton l) {
    int encontrou = 0;
    int cont = 0;
    String s = "__";

    for (int i = 0; i < palavraDoJogo.length(); i++) {
      // Remove todos os acentos para depois verificar letra por letra
      palavraDoJogo = Normalizer.normalize(palavraDoJogo, Normalizer.Form.NFD);
      palavraDoJogo = palavraDoJogo.replaceAll("[^\\p{ASCII}]", "");

      if (Character.toLowerCase(letra) == palavraDoJogo.charAt(i)
          || Character.toUpperCase(letra) == palavraDoJogo.charAt(i)) {

        campos
            .get(i)
            .setText(l.getText()); // Caso a palavra tenha a letra informada ele insere na posi
        encontrou++;
      }

      if (encontrou >= 0 && campos.get(i).getText() != s) {
        cont++;
      }
    }

    if (cont == palavraDoJogo.length()) {
      JOptionPane.showMessageDialog(null, "Você Ganhou!", "Mensagem", JOptionPane.WARNING_MESSAGE);
      mensagem();
    } else {
      processGuess(encontrou, chances, labelChances, palavraDoJogo);
    }

    l.setVisible(false);
    return encontrou;
  }

  @Override
  public String getPalavraDoJogo() {
    return palavraDoJogo;
  }

  @Override
  public int getEncontrou() {
    return encontrou;
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
