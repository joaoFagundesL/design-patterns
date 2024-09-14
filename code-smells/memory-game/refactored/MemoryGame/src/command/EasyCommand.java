package command;

import button.Button;
import game.GameM;
import java.awt.Color;

public class EasyCommand implements Command {
  private final GameM game;
  private final Button easy;
  private final Button hard;

  public EasyCommand(final GameM game, final Button easy, final Button hard) {
    this.game = game;
    this.easy = easy;
    this.hard = hard;
  }

  @Override
  public void execute() {
    game.setEh(true);
    easy.setForeground(Color.BLUE);
    hard.setForeground(Color.BLACK);
  }
}
