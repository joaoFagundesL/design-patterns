package command;

import src.GamePanel;

public class MoveUpCommand implements Command {
    private GamePanel gamePanel;

    public MoveUpCommand(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void execute() {
        gamePanel.moveUp();
    }
}