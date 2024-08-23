package src;


import strategy.MovementStrategy;

public class Item {
    private int x;
    private int dx = 0;
    private int y;
    private int dy = 0;
    private int speed;
    private MovementStrategy movementStrategy;

    public Item(int sx, int sy, int s) {
        x = sx;
        y = sy;
        speed = s;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public void move() {
        if (movementStrategy != null) {
            movementStrategy.move(this);
        }
    }

    public void updatePosition() {
        x += dx;
        y += dy;
    }

    public void reverseDirection() {
        dx = -dx;
        dy = -dy;
    }
}