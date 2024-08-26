package strategy;

public class MoveDownStrategy implements MoveStrategy {
  @Override
  public void move(int[] x, int[] y, int unitSize) {
    y[0] += unitSize;
  }

  @Override
  public Direction getDirection() {
    return Direction.DOWN;
  }
}
