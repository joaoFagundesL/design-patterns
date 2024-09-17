package src;

import strategy.MovementStrategy;

public class Item {
  private int horizontal;
  private int directionX = 0;
  private int vertical;
  private int directionY = 0;
  private final int speed;
  private MovementStrategy movementStrategy;

  public Item(final int startX, final int startY, final int totalSize) {
    horizontal = startX;
    vertical = startY;
    speed = totalSize;
  }

  public int getHorizontal() {
    return horizontal;
  }

  public int getVertical() {
    return vertical;
  }

  public int getSpeed() {
    return speed;
  }

  public void setDirectionX(final int directionX) {
    this.directionX = directionX;
  }

  public void setDirectionY(final int directionY) {
    this.directionY = directionY;
  }

  public void setMovementStrategy(final MovementStrategy movementStrategy) {
    this.movementStrategy = movementStrategy;
  }

  public void move() {
    if (movementStrategy != null) {
      movementStrategy.move(this);
    }
  }

  public void updatePosition() {
    horizontal += directionX;
    vertical += directionY;
  }

  public void reverseDirection() {
    directionX = -directionX;
    directionY = -directionY;
  }
}
