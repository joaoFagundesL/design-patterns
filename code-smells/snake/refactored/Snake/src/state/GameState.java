package state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import panel.GamePanel;
import panel.GameRenderer;

public interface GameState {
  void update(GamePanel gamePanel);

  void draw(Graphics graphics, GameRenderer gameRenderer);

  void handleInput(KeyEvent event, GamePanel gamePanel);
}
