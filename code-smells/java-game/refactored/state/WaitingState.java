package state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import src.GamePanel;

public class WaitingState implements GameState {

  @Override
  public void start(final GamePanel panel) {
    panel.setState(new RunningState());
  }

  @Override
  public void update(final GamePanel panel) {}

  @Override
  public void draw(final GamePanel panel, final Graphics graphics) {
    graphics.setColor(Color.DARK_GRAY);
    graphics.fillRect(0, 0, 600, 600);
    graphics.setColor(Color.WHITE);
    graphics.drawString("PRESS START TO BEGIN...", 250, 300);
  }

  @Override
  public void handleInput(final GamePanel panel, final KeyEvent event) {
    if (event.getKeyCode() == KeyEvent.VK_ENTER) {
      start(panel);
    }
  }

  @Override
  public void handleStart(final GamePanel gamePanel) {
    gamePanel.setState(new RunningState());
  }
}
