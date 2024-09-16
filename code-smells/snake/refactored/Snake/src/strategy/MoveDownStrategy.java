package strategy;

public class MoveDownStrategy implements MoveStrategy {
  @Override
  public void move(final int[] horizontal, final int[] vertical, final int unitSize) {
    vertical[0] += unitSize;
  }

  @Override
  public Direction getDirection() {
    return Direction.DOWN;
  }
}
