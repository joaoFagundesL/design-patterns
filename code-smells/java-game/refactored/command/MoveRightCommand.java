package command;

import src.GamePanel;

public class MoveRightCommand implements Command {
  private GamePanel gamePanel;

  public MoveRightCommand(GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  @Override
  public void execute() {
    gamePanel.moveRight();
  }
}
