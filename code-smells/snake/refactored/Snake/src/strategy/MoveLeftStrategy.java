package strategy;

public class MoveLeftStrategy implements MoveStrategy {
  @Override
  public void move(final int[] horizontal, final int[] vertical, final int unitSize) {
    horizontal[0] -= unitSize;
  }

  @Override
  public Direction getDirection() {
    return Direction.LEFT;
  }
}
