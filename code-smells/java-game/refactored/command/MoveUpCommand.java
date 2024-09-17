package command;

import src.GamePanel;

public class MoveUpCommand implements Command {
  private final GamePanel gamePanel;

  public MoveUpCommand(final GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  @Override
  public void execute() {
    gamePanel.moveUp();
  }
}
