package command;

import src.GamePanel;

public class MoveDownCommand implements Command {
  private final GamePanel gamePanel;

  public MoveDownCommand(final GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  @Override
  public void execute() {
    gamePanel.moveDown();
  }
}
