package state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import panel.GamePanel;

public interface GameState {
  void update(GamePanel gamePanel);

  void draw(Graphics graphics, GamePanel gamePanel);

  void handleInput(KeyEvent event, GamePanel gamePanel);
}
