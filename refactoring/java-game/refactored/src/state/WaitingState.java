package state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import src.GamePanel;


public class WaitingState implements GameState {

    @Override
    public void start(GamePanel panel) {
        panel.setState(new RunningState());  
    }

    @Override
    public void update(GamePanel panel) {
    }

    @Override
    public void draw(GamePanel panel, Graphics g) {
    	g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 600, 600);
        g.setColor(Color.WHITE);
        g.drawString("PRESS START TO BEGIN..", 250, 300);
    }

    @Override
    public void handleInput(GamePanel panel, KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            start(panel);
        }
    }

//    @Override
//    public void startTimer(GamePanel panel) {
//        panel.getaTimer().start();
//        panel.setState(new RunningState()); // Transition to running state
//    }
//
//    @Override
//    public void stopTimer(GamePanel panel) {
//        panel.getaTimer().stop(); // Optionally stop the timer if needed
//    }
}