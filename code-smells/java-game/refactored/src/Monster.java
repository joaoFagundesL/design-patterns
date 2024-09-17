package src;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import strategy.MoveDownStrategy;
import strategy.MoveLeftStrategy;
import strategy.MoveRightStrategy;
import strategy.MoveUpStrategy;
import strategy.MovementStrategy;

public class Monster extends Item {

  private final Random random = new Random();

  public Monster(final int initialX, final int initialY, final int totalSize) {
    super(initialX, initialY, totalSize);
  }

  public void draw(final Graphics graphics) {
    final int horizontal = super.getHorizontal();
    final int vertical = super.getVertical();
    graphics.setColor(Color.RED);

    final int[] xOffsets = {8, 16, 24};
    final int[] yOffsets = {6, 12, 18, 24, 30};

    final int[] xpts =
        new int[] {
          horizontal + xOffsets[0],
          horizontal + xOffsets[1],
          horizontal + xOffsets[1],
          horizontal + xOffsets[2],
          horizontal + xOffsets[2],
          horizontal + xOffsets[1],
          horizontal + xOffsets[1],
          horizontal + xOffsets[2],
          horizontal + xOffsets[2],
          horizontal + xOffsets[2],
          horizontal + xOffsets[1],
          horizontal + xOffsets[0],
          horizontal + xOffsets[0],
          horizontal + xOffsets[0],
          horizontal,
          horizontal,
          horizontal,
          horizontal + xOffsets[0],
          horizontal + xOffsets[0],
          horizontal,
          horizontal,
          horizontal + xOffsets[0]
        };

    final int[] ypts =
        new int[] {
          vertical,
          vertical,
          vertical + yOffsets[0],
          vertical + yOffsets[0],
          vertical + yOffsets[1],
          vertical + yOffsets[1],
          vertical + yOffsets[2],
          vertical + yOffsets[2],
          vertical + yOffsets[3],
          vertical + yOffsets[4],
          vertical + yOffsets[4],
          vertical + yOffsets[3],
          vertical + yOffsets[3],
          vertical + yOffsets[2],
          vertical + yOffsets[4],
          vertical + yOffsets[3],
          vertical + yOffsets[2],
          vertical + yOffsets[2],
          vertical + yOffsets[1],
          vertical + yOffsets[1],
          vertical + yOffsets[0],
          vertical + yOffsets[0]
        };

    graphics.fillPolygon(xpts, ypts, xpts.length);
  }

  public void handleBorderCollision() {
    final int horizontal = super.getHorizontal();
    final int vertical = super.getVertical();
    if ((horizontal <= 0) | (horizontal >= 570) | (vertical <= 0) | (vertical >= 570))
      super.reverseDirection();
    updatePosition();
  }

  public void hunting() {
    final int dir = random.nextInt(20);
    MovementStrategy strategy = null;

    switch (dir) {
      case 0:
        strategy = new MoveLeftStrategy();
        break;
      case 1:
        strategy = new MoveUpStrategy();
        break;
      case 2:
        strategy = new MoveRightStrategy();
        break;
      case 3:
        strategy = new MoveDownStrategy();
        break;
      default:
        return;
    }

    if (strategy != null) {
      setMovementStrategy(strategy);
      move();
    }
  }
}
