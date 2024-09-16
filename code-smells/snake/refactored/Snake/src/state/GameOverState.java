package state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import panel.GamePanel;

public class GameOverState implements GameState {

  @Override
  public void update(final GamePanel gamePanel) {
    // No updates when game is over
  }

  @Override
  public void draw(final Graphics graphics, final GamePanel gamePanel) {
    gamePanel.draw(graphics);
    gamePanel.gameOver(graphics);
  }

  @Override
  public void handleInput(final KeyEvent event, final GamePanel gamePanel) {
    if (event.getKeyCode() == KeyEvent.VK_SPACE) {
      gamePanel.restartGame();
      gamePanel.setGameState(new RunningState());
    }
  }
}
