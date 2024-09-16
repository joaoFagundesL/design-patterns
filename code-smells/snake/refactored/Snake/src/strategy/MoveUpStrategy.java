package strategy;

public class MoveUpStrategy implements MoveStrategy {

  @Override
  public void move(int[] horizontal, int[] vertical, int unitSize) {
    vertical[0] -= unitSize;
  }

  @Override
  public Direction getDirection() {
    return Direction.UP;
  }
}
