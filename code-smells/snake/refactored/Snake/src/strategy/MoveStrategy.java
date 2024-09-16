package strategy;

public interface MoveStrategy {
  void move(int[] horizontal, int[] vertical, int unitSize);

  Direction getDirection();
}
