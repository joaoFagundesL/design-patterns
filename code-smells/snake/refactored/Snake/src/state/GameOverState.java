package state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import panel.GamePanel;

public class GameOverState implements GameState {

  @Override
  public void update(GamePanel gamePanel) {
    // No updates when game is over
  }

  @Override
  public void draw(Graphics g, GamePanel gamePanel) {
    gamePanel.draw(g);
    gamePanel.gameOver(g);
  }

  @Override
  public void handleInput(KeyEvent e, GamePanel gamePanel) {
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      gamePanel.restartGame();
      gamePanel.setGameState(new RunningState());
    }
  }
}
