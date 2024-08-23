package strategy;

import src.Item;

public class MoveUpStrategy implements MovementStrategy {
    @Override
    public void move(Item item) {
        item.setDx(0);
        item.setDy(-item.getSpeed());
    }
}