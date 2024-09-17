package state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import src.GamePanel;

public interface GameState {
  void start(GamePanel panel);

  void update(GamePanel panel);

  void draw(GamePanel panel, Graphics graphics);

  void handleInput(GamePanel panel, KeyEvent event);

  public default void handleStart(final GamePanel gamePanel) {}
}
