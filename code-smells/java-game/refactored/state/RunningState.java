package state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import src.GamePanel;

public class RunningState implements GameState {

  @Override
  public void start(GamePanel panel) {
  }

  @Override
  public void update(GamePanel panel) {
    panel.updateGame();
    if (panel.isGameOver()) {
      panel.setState(new GameOverState());
    }
  }

  @Override
  public void draw(GamePanel panel, Graphics g) {
    g.setColor(Color.DARK_GRAY);
    g.fillRect(0, 0, 600, 600);
    g.setColor(Color.BLACK);

    g.fillRect(0, 600, 600, 30);
    g.setColor(Color.BLACK);
    g.fillRect(0, 600, 600, 30);
    panel.getAlister().draw(g);
    panel.getMonster().draw(g);
    panel.getMonster1().draw(g);
    panel.getMonster2().draw(g);
    g.setColor(Color.WHITE);
    g.drawString("S C O R E : " + panel.getScore(), 10, 620);
  }

  @Override
  public void handleInput(GamePanel panel, KeyEvent e) {
    panel.processMovement(e); 
  }

  //    @Override
  //    public void startTimer(GamePanel panel) {
  //    }
  //
  //    @Override
  //    public void stopTimer(GamePanel panel) {
  //        panel.getaTimer().stop();
  //    }
}
