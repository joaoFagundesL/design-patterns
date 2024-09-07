package strategy;

import src.Item;

public class MoveLeftStrategy implements MovementStrategy {
  @Override
  public void move(Item item) {
    item.setDx(-item.getSpeed());
    item.setDy(0);
  }
}