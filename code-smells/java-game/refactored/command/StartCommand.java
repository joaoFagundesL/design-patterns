package command;

import src.GamePanel;

public class StartCommand implements Command {
  private final GamePanel gamePanel;

  public StartCommand(final GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  @Override
  public void execute() {
    gamePanel.startTimer();
  }
}
