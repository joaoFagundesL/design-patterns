import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class Option {

  private JFrame frame;
  private Integer opcao;

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Option window = new Option();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
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

    JButton btnNewButton = new JButton("ANIMAIS");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        opcao = 1;
        frame.dispose();
        Main.newScreen(opcao);
      }
    });
    btnNewButton.setBounds(143, 82, 143, 25);
    frame.getContentPane().add(btnNewButton);

    JButton btnComidas = new JButton("COMIDAS");
    btnComidas.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        opcao = 2;
        frame.dispose();
        Main.newScreen(opcao);
      }
    });
    btnComidas.setBounds(143, 137, 143, 25);
    frame.getContentPane().add(btnComidas);

    JLabel lblJogoDaFoca = new JLabel("JOGO DA FORCA");
    lblJogoDaFoca.setFont(new Font("Dialog", Font.BOLD, 17));
    lblJogoDaFoca.setBounds(132, 12, 169, 39);
    frame.getContentPane().add(lblJogoDaFoca);

    JButton btnNewButton_1 = new JButton("PROFISSÕES");
    btnNewButton_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        opcao = 3;
        frame.dispose();
        Main.newScreen(opcao);
      }
    });
    btnNewButton_1.setBounds(143, 191, 143, 25);
    frame.getContentPane().add(btnNewButton_1);

  }
}
