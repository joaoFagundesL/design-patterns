package command;

import game.GameM;

public class StartCommand implements Command {
    private GameM game;

    public StartCommand(GameM game) {
        this.game = game;
    }

    @Override
    public void execute() {
        try {
            int level = Integer.parseInt(game.getLevelText());
            boolean easy = game.isEasy(); 
            game.setUpGame(level, easy);
        } catch (Exception e) {
            game.setUpGame(1, game.isEasy());
        }
    }
}
