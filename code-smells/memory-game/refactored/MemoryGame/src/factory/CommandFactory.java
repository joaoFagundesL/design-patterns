package factory;

import command.Command;
import command.EasyCommand;
import command.ExitCommand;
import command.GoBackCommand;
import command.HardCommand;
import command.InstructionsCommand;
import command.StartCommand;
import game.GameM;
import view.GameView;

public class CommandFactory {
  public static Command createCommand(
      final CommandType type, final GameM game, final GameView view) {
    switch (type) {
      case START:
        return new StartCommand(game);
      case EXIT:
        return new ExitCommand();
      case INSTRUCTIONS:
        return new InstructionsCommand(
            game, view, view.getGoBackButton(), view.getInstructScreen(), view.getStartScreen());
      case GOBACK:
        return new GoBackCommand(view, game);
      case EASY:
        return new EasyCommand(game, view.getEasyButton(), view.getHardButton());
      case HARD:
        return new HardCommand(game, view.getEasyButton(), view.getHardButton());
      default:
        throw new IllegalArgumentException("Unknown command type: " + type);
    }
  }
}
