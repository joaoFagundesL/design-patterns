package command;

import java.awt.Color;

import button.Button;
import game.GameM;

public class HardCommand implements Command{

	private GameM game;
	
	public HardCommand(GameM game) {
		this.game = game;
	}
	
	@Override
	public void execute() {
		Button easy = game.getEasy();
		Button hard = game.getHard();
		
		game.setEh(true);
		hard.setForeground(Color.BLUE);
        easy.setForeground(Color.BLACK);
	}

}
