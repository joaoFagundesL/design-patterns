package command;

import src.GamePanel;

public class MoveDownCommand implements Command {
  private GamePanel gamePanel;

  public MoveDownCommand(GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  @Override
  public void execute() {
    gamePanel.moveDown();
  }
}
