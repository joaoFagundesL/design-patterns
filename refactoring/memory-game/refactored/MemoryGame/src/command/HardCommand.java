package command;

import button.Button;
import game.GameM;
import java.awt.Color;

public class HardCommand implements Command {

  private GameM game;
  private Button easy;
  private Button hard;

  public HardCommand(GameM game, Button easy, Button hard) {
    this.easy = easy;
    this.hard = hard;
    this.game = game;
  }

  @Override
  public void execute() {
    game.setEh(false);
    hard.setForeground(Color.BLUE);
    easy.setForeground(Color.BLACK);
  }
}
