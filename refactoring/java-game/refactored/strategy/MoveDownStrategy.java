package strategy;

import src.Item;

public class MoveDownStrategy implements MovementStrategy {
  @Override
  public void move(Item item) {
    item.setDx(0);
    item.setDy(item.getSpeed());
  }
}
