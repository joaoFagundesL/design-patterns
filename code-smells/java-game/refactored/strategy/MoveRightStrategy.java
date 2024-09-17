package strategy;

import src.Item;

public class MoveRightStrategy implements MovementStrategy {
  @Override
  public void move(Item item) {
    item.setDirectionX(item.getSpeed());
    item.setDirectionY(0);
  }
}
