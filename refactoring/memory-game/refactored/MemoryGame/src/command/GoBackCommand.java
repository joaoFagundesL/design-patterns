package command;
import game.GameM;
import view.GameView;

public class GoBackCommand implements Command {
  private GameView view;
  private GameM game;

  public GoBackCommand(GameView view, GameM game) {
    this.game = game;
    this.view = view;
  }

  @Override
  public void execute() {
    view.frame.dispose();
    game.goToMainScreen();
  }

}
