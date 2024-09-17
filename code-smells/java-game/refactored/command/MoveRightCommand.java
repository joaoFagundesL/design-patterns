package command;

import src.GamePanel;

public class MoveRightCommand implements Command {
  private final GamePanel gamePanel;

  public MoveRightCommand(final GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  @Override
  public void execute() {
    gamePanel.moveRight();
  }
}
