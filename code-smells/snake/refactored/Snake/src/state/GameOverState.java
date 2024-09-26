package state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import panel.GamePanel;
import panel.GameRenderer;

public class GameOverState implements GameState {

  @Override
  public void update(final GamePanel gamePanel) {}

  @Override
  public void draw(final Graphics graphics, final GameRenderer gameRenderer) {
    gameRenderer.draw(graphics);
    gameRenderer.gameOver(graphics);
  }

  @Override
  public void handleInput(final KeyEvent event, final GamePanel gamePanel) {
    if (event.getKeyCode() == KeyEvent.VK_SPACE) {
      gamePanel.restartGame();
      gamePanel.setGameState(new RunningState());
    }
  }
}
