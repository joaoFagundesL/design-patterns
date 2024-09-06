package command;

import src.GamePanel;

public class StartCommand implements Command {
  private GamePanel gamePanel;

  public StartCommand(GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  @Override
  public void execute() {
    gamePanel.startTimer();
  }
}
