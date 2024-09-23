package com.utfpr.src.app;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Option {

  private JFrame frame;
  private Integer opcao;
  private static final Logger LOGGER = Logger.getLogger(Option.class.getName());

  public static void main(String[] args) {
    EventQueue.invokeLater(
        new Runnable() {
          @Override
          public void run() {
            try {
              final Option window = new Option();
              window.frame.setVisible(true);
            } catch (Exception exception) {
              LOGGER.log(
                  Level.SEVERE, "An error occurred while trying to open the window.", exception);
            }
          }
        });
  }

  public Option() {
    initialize();
  }

  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 450, 300);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    final JButton animaisButton = new JButton("ANIMAIS");
    animaisButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent arg0) {
            opcao = 1;
            frame.dispose();
            HangmanGameManager.newScreen(opcao);
          }
        });
    animaisButton.setBounds(143, 82, 143, 25);
    frame.getContentPane().add(animaisButton);

    final JButton btnComidas = new JButton("COMIDAS");
    btnComidas.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent arg0) {
            opcao = 2;
            frame.dispose();
            HangmanGameManager.newScreen(opcao);
          }
        });
    btnComidas.setBounds(143, 137, 143, 25);
    frame.getContentPane().add(btnComidas);

    final JLabel lblJogoDaForca = new JLabel("JOGO DA FORCA");
    lblJogoDaForca.setFont(new Font("Dialog", Font.BOLD, 17));
    lblJogoDaForca.setBounds(132, 12, 169, 39);
    frame.getContentPane().add(lblJogoDaForca);

    final JButton profissoesButton = new JButton("PROFISSÕES");
    profissoesButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent arg0) {
            opcao = 3;
            frame.dispose();
            HangmanGameManager.newScreen(opcao);
          }
        });
    profissoesButton.setBounds(143, 191, 143, 25);
    frame.getContentPane().add(profissoesButton);
  }
}
