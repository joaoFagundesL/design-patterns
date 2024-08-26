package command;

import src.GamePanel;

public class RestartCommand implements Command {
  private GamePanel gamePanel;

  public RestartCommand(GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  @Override
  public void execute() {
    gamePanel.reset();
  }
}
