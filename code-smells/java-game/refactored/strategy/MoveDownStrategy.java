package strategy;

import src.Item;

public class MoveDownStrategy implements MovementStrategy {
  @Override
  public void move(final Item item) {
    item.setDirectionX(0);
    item.setDirectionY(item.getSpeed());
  }
}
