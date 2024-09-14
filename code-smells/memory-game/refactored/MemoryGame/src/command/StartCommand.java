package command;

import game.GameM;

public class StartCommand implements Command {
  private final GameM game;

  public StartCommand(final GameM game) {
    this.game = game;
  }

  @Override
  public void execute() {
    try {
      final int level = Integer.parseInt(game.getLevelText());
      final boolean easy = game.isEasy();
      game.setUpGame(level, easy);
    } catch (final Exception e) {
      game.setUpGame(1, game.isEasy());
    }
  }
}
