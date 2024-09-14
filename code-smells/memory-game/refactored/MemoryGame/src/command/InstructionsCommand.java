package command;

import button.Button;
import game.GameM;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import view.GameView;

public class InstructionsCommand implements Command {
  private final GameView view;
  private final GameM game;
  private final Button goBack;
  private final JPanel instructScreen;
  private final JPanel startScreen;

  public InstructionsCommand(
      final GameM game,
      final GameView view,
      final Button goBack,
      final JPanel instructScreen,
      final JPanel startScreen) {
    this.game = game;
    this.instructScreen = instructScreen;
    this.startScreen = startScreen;
    this.goBack = goBack;
    this.view = view;
  }

  @Override
  public void execute() {
    view.clearMain();

    startScreen.add(instructScreen, BorderLayout.NORTH);

    final JPanel one = new JPanel();
    one.setLayout(new FlowLayout(FlowLayout.CENTER));
    final JPanel two = new JPanel();
    two.setLayout(new FlowLayout(FlowLayout.CENTER));
    instructScreen.setLayout(new BorderLayout());
    instructScreen.add(one, BorderLayout.NORTH);
    instructScreen.add(two, BorderLayout.SOUTH);

    one.add(view.getInstructM());
    two.add(goBack);

    goBack.addActionListener(game);
    goBack.setEnabled(true);
  }
}
