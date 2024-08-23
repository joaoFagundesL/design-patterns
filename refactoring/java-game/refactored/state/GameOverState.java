package state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import src.GamePanel;

public class GameOverState implements GameState {

    @Override
    public void start(GamePanel panel) {
        panel.reset();  // Reset the game for a new start
        panel.setState(new WaitingState());
    }

    @Override
    public void update(GamePanel panel) {
    }

    @Override
    public void draw(GamePanel panel, Graphics g) {
    	g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 600, 600);
        g.setColor(Color.BLACK);
        g.setColor(Color.WHITE);
        g.drawString("G A M E O V E R", 250, 300);
    }

    @Override
    public void handleInput(GamePanel panel, KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            start(panel);
        }
    }

//	@Override
//	public void startTimer(GamePanel panel) {
//		
//	}
//
//	@Override
//	public void stopTimer(GamePanel panel) {
//		
//	}
}