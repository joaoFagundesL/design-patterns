import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Main implements Mediator{
  private JFrame frame;
  private String palavraDoJogo;
  private List<JLabel> campos = new ArrayList<>();
  private int[] chances = {4};
  private JLabel labelChances;
  private int encontrou;
  ButtonBuilder builder = new ButtonBuilder();
  private FileReader fileReader = new FileReader(); 

  public static void newScreen(int op) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Main window = new Main(op);
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public Main(int op) {
    initialize(op);
  }

  public void mensagem() {
    int dialogButton = JOptionPane.YES_NO_OPTION;
    int dialogResult = JOptionPane.showConfirmDialog(null, "Deseja Continuar?", "Mensagem", dialogButton);

    if(dialogResult == 0) {
      frame.dispose();
      Option.main(null);
    }else { 
      System.exit(0);
      frame.dispose();
      frame.setVisible(false);
    } // Usuario saiu do programa
  }

  private void initialize(int op) {
    frame = new JFrame();
    frame.setBounds(100, 100, 450, 300);
    frame.setSize(800, 600);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    JLabel lblNewLabel = new JLabel("Chances Restantes: ");
    lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
    lblNewLabel.setBounds(22, 24, 246, 16);
    frame.getContentPane().add(lblNewLabel);

    String s = String.valueOf(chances[0]);
    labelChances = new JLabel(s);
    labelChances.setFont(new Font("Dialog", Font.BOLD, 18));
    labelChances.setBounds(252, 25, 70, 15);
    frame.getContentPane().add(labelChances);

    List<String> palavras = new ArrayList<>();

    String filePath = getFilePath(op);
    palavras = fileReader.loadWords(filePath);

    Random rand = new Random();
    palavraDoJogo = palavras.get(rand.nextInt(palavras.size()));

    int espaco = 129;

    for (int i = 0; i < palavraDoJogo.length(); i++) {
      JLabel label = new JLabel("__");
      label.setBounds(espaco, 128, 53, 19);
      frame.getContentPane().add(label);
      campos.add(label);
      espaco += 20;
    }

    char[] letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    int startX = 22;
    int startY = 373;
    int width = 71;
    int height = 19;
    int gap = 94;

    for (int i = 0; i < letras.length; i++) {
      String letter = String.valueOf(letras[i]);
      int x = startX + (i % 10) * gap;
      int y = startY + (i / 10) * (height + 10);
      int index = i;

      Button button = builder.withLetter(letter)
      .withPosition(x, y)
      .withSize(width, height)
      .build();

      button.setMediator(this);

      frame.getContentPane().add(button);
    }
  }


  @Override
  public void processGuess(int encontrou, int[] chances, JLabel labelChances, String palavraDoJogo) {
    if (encontrou == 0) {
      if (chances[0] != 0) {
        chances[0] = chances[0] - 1; 
        labelChances.setText(String.valueOf(chances[0])); 
      } else { 
        JOptionPane.showMessageDialog(null, 
          "Você Perdeu! A palavra é: " + palavraDoJogo, 
          "Mensagem", 
          JOptionPane.WARNING_MESSAGE);

        mensagem();
      }
    }
  }

  private String getFilePath(int op) {
    switch (op) {
      case 1:
      return "/home/arch/Documents/design-patterns/refactoring/hangman/refactored/HangmanGame/hangman/src/animais";
      case 2:
      return "/home/arch/Documents/design-patterns/refactoring/hangman/refactored/HangmanGame/hangman/src/comidas";
      case 3:
      return "/home/arch/Documents/design-patterns/refactoring/hangman/refactored/HangmanGame/hangman/src/profissoes";
      default:
      throw new IllegalArgumentException("Invalid option: " + op);
    }
  }

  @Override
  public int checkGuess (String palavraDoJogo, char letra, List<JLabel> campos, JButton l) {
    int encontrou = 0;
    int cont = 0;
    String s = "__";

    for (int i = 0; i < palavraDoJogo.length(); i++) {
      //Remove todos os acentos para depois verificar letra por letra
      palavraDoJogo = Normalizer.normalize(palavraDoJogo, Normalizer.Form.NFD);
      palavraDoJogo = palavraDoJogo.replaceAll("[^\\p{ASCII}]", ""); 

      if (Character.toLowerCase(letra) == palavraDoJogo.charAt(i) || 
      Character.toUpperCase(letra) == palavraDoJogo.charAt(i)) {

        campos.get(i).setText(l.getText()); // Caso a palavra tenha a letra informada ele insere na posi
        encontrou++;
      } 

      if (encontrou >= 0 && campos.get(i).getText() != s) { 
        cont++;
      } 
    }

    if (cont == palavraDoJogo.length()) {
      JOptionPane.showMessageDialog(null, 
        "Você Ganhou!", 
        "Mensagem", 
        JOptionPane.WARNING_MESSAGE);


      mensagem(); 
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
    return chances;
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
