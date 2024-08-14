package command;
import java.awt.Color;

import button.Button;
import game.GameM;
 

public class EasyCommand implements Command{
	private GameM game;
				
	public EasyCommand(GameM game) {
		this.game = game;
	}
	
	@Override
	public void execute() {
		Button easy = game.getEasy();
		Button hard = game.getHard();
		
		game.setEh(true);
		easy.setForeground(Color.BLUE);
        hard.setForeground(Color.BLACK);
	}

}
