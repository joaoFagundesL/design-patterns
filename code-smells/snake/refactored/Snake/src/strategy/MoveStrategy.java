package strategy;

public interface MoveStrategy {
  void move(int[] x, int[] y, int unitSize);
  Direction getDirection();
}
