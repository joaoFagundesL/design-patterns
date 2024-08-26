package command;

import src.GamePanel;

public class MoveLeftCommand implements Command {
  private GamePanel gamePanel;

  public MoveLeftCommand(GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  @Override
  public void execute() {
    gamePanel.moveLeft();
  }
}
