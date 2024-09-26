package state;

import factory.MoveStrategyFactory;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import panel.GamePanel;
import panel.GameRenderer;
import strategy.Direction;
import strategy.MoveStrategy;

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

    final MoveStrategy newStrategy =
        MoveStrategyFactory.createMoveStrategy(currentDirection, event.getKeyCode());

    if (newStrategy != null) {
      gamePanel.setMoveStrategy(newStrategy);
    }
  }
}
