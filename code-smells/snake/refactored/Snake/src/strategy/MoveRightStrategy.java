package strategy;

public class MoveRightStrategy implements MoveStrategy {
  @Override
  public void move(int[] x, int[] y, int unitSize) {
    x[0] += unitSize;
  }

  @Override
  public Direction getDirection() {
    return Direction.RIGHT;
  }  
}
