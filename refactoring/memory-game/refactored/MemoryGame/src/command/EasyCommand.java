package command;
import java.awt.Color;

import button.Button;
import game.GameM;
 

public class EasyCommand implements Command{
	private GameM game;
	private Button easy;
	private Button hard;
				
	public EasyCommand(GameM game, Button easy, Button hard) {
		this.game = game;
		this.easy = easy;
		this.hard = hard;
	}
	
	@Override
	public void execute() {
		game.setEh(true);
		easy.setForeground(Color.BLUE);
        hard.setForeground(Color.BLACK);
	}

}
