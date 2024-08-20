package state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import panel.GamePanel;
import strategy.Direction;
import strategy.MoveDownStrategy;
import strategy.MoveLeftStrategy;
import strategy.MoveRightStrategy;
import strategy.MoveUpStrategy;

public class RunningState implements GameState {

    @Override
    public void update(GamePanel gamePanel) {
        gamePanel.move();
        gamePanel.checkApple();
        gamePanel.checkCollisions();
    }

    @Override
    public void draw(Graphics g, GamePanel gamePanel) {
        gamePanel.draw(g);
    }

    @Override
    public void handleInput(KeyEvent e, GamePanel gamePanel) {
        Direction currentDirection = gamePanel.getMoveStrategy().getDirection();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (currentDirection != Direction.RIGHT) {
                    gamePanel.setMoveStrategy(new MoveLeftStrategy());
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (currentDirection != Direction.LEFT) {
                    gamePanel.setMoveStrategy(new MoveRightStrategy());
                }
                break;
            case KeyEvent.VK_UP:
                if (currentDirection != Direction.DOWN) {
                    gamePanel.setMoveStrategy(new MoveUpStrategy());
                }
                break;
            case KeyEvent.VK_DOWN:
                if (currentDirection != Direction.UP) {
                    gamePanel.setMoveStrategy(new MoveDownStrategy());
                }
                break;
        }
    }
}
