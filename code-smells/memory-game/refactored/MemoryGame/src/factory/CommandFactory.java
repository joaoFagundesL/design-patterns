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
  public static Command createCommand(final String type, final GameM game, final GameView view) {
    switch (type) {
      case "start":
        return new StartCommand(game);
      case "exit":
        return new ExitCommand();
      case "instructions":
        return new InstructionsCommand(
            game, view, view.getGoBackButton(), view.getInstructScreen(), view.getStartScreen());
      case "goback":
        return new GoBackCommand(view, game);
      case "easy":
        return new EasyCommand(game, view.getEasyButton(), view.getHardButton());
      case "hard":
        return new HardCommand(game, view.getEasyButton(), view.getHardButton());
      default:
        throw new IllegalArgumentException("Unknown command type: " + type);
    }
  }
}
