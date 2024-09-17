package state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import src.GamePanel;

public class GameOverState implements GameState {

  @Override
  public void start(final GamePanel panel) {
    panel.reset(); // Reset the game for a new start
    panel.setState(new WaitingState());
  }

  @Override
  public void update(final GamePanel panel) {}

  @Override
  public void draw(final GamePanel panel, final Graphics graphics) {
    graphics.setColor(Color.DARK_GRAY);
    graphics.fillRect(0, 0, 600, 600);
    graphics.setColor(Color.BLACK);
    graphics.setColor(Color.WHITE);
    graphics.drawString("G A M E O V E R", 250, 300);
  }

  @Override
  public void handleInput(final GamePanel panel, final KeyEvent event) {
    if (event.getKeyCode() == KeyEvent.VK_ENTER) {
      start(panel);
    }
  }
}
