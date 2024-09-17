package command;

import src.GamePanel;

public class RestartCommand implements Command {
  private final GamePanel gamePanel;

  public RestartCommand(final GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  @Override
  public void execute() {
    gamePanel.reset();
  }
}
