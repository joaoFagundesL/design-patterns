package command;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import button.Button;
import game.GameM;

public class InstructionsCommand implements Command {
	GameM game;
	
	 public InstructionsCommand(GameM game) {
	        this.game = game;
	    }
	
	@Override
	public void execute() {
		game.clearMain();
		JPanel instructScreen = game.getInstructScreen();
		JPanel startScreen = game.getStartScreen();
		Button goBack = game.getGoBack();

		startScreen.add(instructScreen, BorderLayout.NORTH);

        JPanel one = new JPanel();
        one.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel two = new JPanel();
        two.setLayout(new FlowLayout(FlowLayout.CENTER));
        instructScreen.setLayout(new BorderLayout());
        instructScreen.add(one, BorderLayout.NORTH);
        instructScreen.add(two, BorderLayout.SOUTH);

        one.add(game.getInstructM());
        two.add(goBack);
        
        goBack.addActionListener(game);
        goBack.setEnabled(true);
	}

}
