package strategy;

import src.Item;

public class MoveUpStrategy implements MovementStrategy {
  @Override
  public void move(Item item) {
    item.setDirectionX(0);
    item.setDirectionY(-item.getSpeed());
  }
}
