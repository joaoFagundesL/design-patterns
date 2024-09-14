package command;

import button.Button;
import game.GameM;
import java.awt.Color;

public class HardCommand implements Command {

  private final GameM game;
  private final Button easy;
  private final Button hard;

  public HardCommand(final GameM game, final Button easy, final Button hard) {
    this.easy = easy;
    this.hard = hard;
    this.game = game;
  }

  @Override
  public void execute() {
    game.setEh(true);
    hard.setForeground(Color.BLUE);
    easy.setForeground(Color.BLACK);
  }
}
