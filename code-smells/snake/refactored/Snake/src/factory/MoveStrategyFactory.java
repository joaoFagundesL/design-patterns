package factory;

import java.awt.event.KeyEvent;
import strategy.Direction;
import strategy.MoveDownStrategy;
import strategy.MoveLeftStrategy;
import strategy.MoveRightStrategy;
import strategy.MoveStrategy;
import strategy.MoveUpStrategy;

public class MoveStrategyFactory {

  public static MoveStrategy createMoveStrategy(
      final Direction currentDirection, final int keyCode) {
    switch (keyCode) {
      case KeyEvent.VK_LEFT:
        if (currentDirection != Direction.RIGHT) {
          return new MoveLeftStrategy();
        }
        break;
      case KeyEvent.VK_RIGHT:
        if (currentDirection != Direction.LEFT) {
          return new MoveRightStrategy();
        }
        break;
      case KeyEvent.VK_UP:
        if (currentDirection != Direction.DOWN) {
          return new MoveUpStrategy();
        }
        break;
      case KeyEvent.VK_DOWN:
        if (currentDirection != Direction.UP) {
          return new MoveDownStrategy();
        }
        break;
    }
    return null;
  }
}
