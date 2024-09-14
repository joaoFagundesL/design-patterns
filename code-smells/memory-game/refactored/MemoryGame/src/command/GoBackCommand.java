package command;

import game.GameM;
import view.GameView;

public class GoBackCommand implements Command {
  private final GameView view;
  private final GameM game;

  public GoBackCommand(final GameView view, final GameM game) {
    this.game = game;
    this.view = view;
  }

  @Override
  public void execute() {
    view.frame.dispose();
    game.goToMainScreen();
  }
}
