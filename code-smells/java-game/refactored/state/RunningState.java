package state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import src.GamePanel;

public class RunningState implements GameState {

  @Override
  public void start(final GamePanel panel) {}

  @Override
  public void update(final GamePanel panel) {
    panel.updateGame();
    if (panel.isGameOver()) {
      panel.setState(new GameOverState());
    }
  }

  @Override
  public void draw(final GamePanel panel, final Graphics graphics) {
    graphics.setColor(Color.DARK_GRAY);
    graphics.fillRect(0, 0, 600, 600);
    graphics.setColor(Color.BLACK);

    graphics.fillRect(0, 600, 600, 30);
    graphics.setColor(Color.BLACK);
    graphics.fillRect(0, 600, 600, 30);
    panel.getAlister().draw(graphics);
    panel.getMonster().draw(graphics);
    panel.getMonster1().draw(graphics);
    panel.getMonster2().draw(graphics);
    graphics.setColor(Color.WHITE);
    graphics.drawString("S C O R E : " + panel.getScore(), 10, 620);
  }

  @Override
  public void handleInput(final GamePanel panel, final KeyEvent event) {
    panel.processMovement(event);
  }
}
