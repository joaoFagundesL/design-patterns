package command;

import src.GamePanel;

public class MoveLeftCommand implements Command {
  private final GamePanel gamePanel;

  public MoveLeftCommand(final GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  @Override
  public void execute() {
    gamePanel.moveLeft();
  }
}
