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

  private Random rdm = new Random();

  public Monster(int sx, int sy, int s) {
    super(sx, sy, s);
  }

  public void draw(Graphics g) {
    int x = super.getX();
    int y = super.getY();
    g.setColor(Color.RED);

    int[] xOffsets = {8, 16, 24};
    int[] yOffsets = {6, 12, 18, 24, 30};

    int[] xpts = new int[] {
      x + xOffsets[0], x + xOffsets[1], x + xOffsets[1], x + xOffsets[2], 
      x + xOffsets[2], x + xOffsets[1], x + xOffsets[1], x + xOffsets[2], 
      x + xOffsets[2], x + xOffsets[2], x + xOffsets[1], x + xOffsets[0], 
      x + xOffsets[0], x + xOffsets[0], x, x, x, x + xOffsets[0], x + xOffsets[0], 
      x, x, x + xOffsets[0]
    };

    int[] ypts = new int[] {
      y, y, y + yOffsets[0], y + yOffsets[0], 
      y + yOffsets[1], y + yOffsets[1], y + yOffsets[2], y + yOffsets[2], 
      y + yOffsets[3], y + yOffsets[4], y + yOffsets[4], y + yOffsets[3], 
      y + yOffsets[3], y + yOffsets[2], y + yOffsets[4], y + yOffsets[3], 
      y + yOffsets[2], y + yOffsets[2], y + yOffsets[1], y + yOffsets[1], 
      y + yOffsets[0], y + yOffsets[0]
    };

    g.fillPolygon(xpts, ypts, xpts.length);
  }


  public void handleBorderCollision() {
    int x = super.getX();
    int y = super.getY();
    if ((x <= 0) | (x >= 570) | (y <= 0) | (y >= 570))
    super.reverseDirection();
    updatePosition();
  }

  public void hunting() {
    int dir = rdm.nextInt(20);
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
