package state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import src.GamePanel;

public interface GameState {
    void start(GamePanel panel);
    void update(GamePanel panel);
    void draw(GamePanel panel, Graphics g);
    void handleInput(GamePanel panel, KeyEvent e);

    public default void handleStart(GamePanel gamePanel) {
    }
    
}