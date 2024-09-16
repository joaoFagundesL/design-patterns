package state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import panel.GamePanel;
import panel.GameRenderer;
import strategy.Direction;
import strategy.MoveDownStrategy;
import strategy.MoveLeftStrategy;
import strategy.MoveRightStrategy;
import strategy.MoveUpStrategy;

public class RunningState implements GameState {

  @Override
  public void update(final GamePanel gamePanel) {
    gamePanel.move();
    gamePanel.checkApple();
    gamePanel.checkCollisions();
  }

  @Override
  public void draw(final Graphics graphics, final GameRenderer gamePanel) {
    gamePanel.draw(graphics);
  }

  @Override
  public void handleInput(final KeyEvent event, final GamePanel gamePanel) {
    final Direction currentDirection = gamePanel.getMoveStrategy().getDirection();
    switch (event.getKeyCode()) {
      case KeyEvent.VK_LEFT:
        if (currentDirection != Direction.RIGHT) {
          gamePanel.setMoveStrategy(new MoveLeftStrategy());
        }
        break;
      case KeyEvent.VK_RIGHT:
        if (currentDirection != Direction.LEFT) {
          gamePanel.setMoveStrategy(new MoveRightStrategy());
        }
        break;
      case KeyEvent.VK_UP:
        if (currentDirection != Direction.DOWN) {
          gamePanel.setMoveStrategy(new MoveUpStrategy());
        }
        break;
      case KeyEvent.VK_DOWN:
        if (currentDirection != Direction.UP) {
          gamePanel.setMoveStrategy(new MoveDownStrategy());
        }
        break;
    }
  }
}
