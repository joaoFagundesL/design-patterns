package command;
import game.GameM;

public class GoBackCommand implements Command {
	private GameM game;
	
	public GoBackCommand(GameM game) {
		this.game = game;
	}
	
	@Override
	public void execute() {
		game.frame.dispose();
		game.goToMainScreen();
	}

}
