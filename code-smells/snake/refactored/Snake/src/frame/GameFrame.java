package frame;

import javax.swing.JFrame;
import panel.GamePanel;

public class GameFrame extends JFrame {

  public GameFrame() {
    super();
    this.add(new GamePanel());
    this.setTitle("Snake Game");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.pack();
    this.setVisible(true);
    this.setLocationRelativeTo(null);
  }
}
