package command;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import button.Button;
import game.GameM;

public class InstructionsCommand implements Command {
	private GameM game;
	private Button goBack;
	private JPanel instructScreen;
	private JPanel startScreen;

	public InstructionsCommand(GameM game, Button goBack, JPanel instructScreen, JPanel startScreen) {
		this.game = game;
		this.instructScreen = instructScreen;
		this.startScreen = startScreen;
		this.goBack = goBack;
	}

	@Override
	public void execute() {
		game.clearMain();

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
