package view;

import button.Button;
import button.ButtonBuilder;
import game.GameM;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameView {
  private final GameM game;

  public JFrame frame = new JFrame("Memory Game");
  private final JPanel field = new JPanel();
  private final JPanel startScreen = new JPanel();
  private final JPanel menu = new JPanel();
  private final JPanel menu2 = new JPanel();
  private final JPanel menu3 = new JPanel();
  private final JPanel mini = new JPanel();
  private final JLabel label = new JLabel("Enter level from 1 to 10");
  private final JTextField text = new JTextField(10);
  private final JTextArea instructM =
      new JTextArea(
          "When the game begins, the screen will be filled\n"
              + "with pairs of buttons.\n"
              + " Memorize their placement.\n"
              + "Once you press any button, they will all clear. \n"
              + " Your goal is to click the matching buttons in a row.\n"
              + "When you finish that, you win.\n"
              + "Every incorrect click gives you a point (those are bad).\n"
              + " GOOD LUCK! \n"
              + "for a single level: enter a level between 1 and 10,\n"
              + "select easy or hard, then press start.");
  private Button start;
  private Button over;
  private Button easy;
  private Button hard;
  private Button inst;
  private Button redo;
  private Button goBack;

  ButtonBuilder builder = new ButtonBuilder();

  public GameView(final GameM game) {
    this.game = game;
    initializeUI();
  }

  private void initializeUI() {
    frame.setSize(500, 300);
    frame.setLocation(500, 300);
    frame.setLayout(new BorderLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    startScreen.setLayout(new BorderLayout());
    menu.setLayout(new FlowLayout(FlowLayout.CENTER));
    menu2.setLayout(new FlowLayout(FlowLayout.CENTER));
    menu3.setLayout(new FlowLayout(FlowLayout.CENTER));
    mini.setLayout(new FlowLayout(FlowLayout.CENTER));

    start = createButton("Start");
    over = createButton("Exit");
    easy = createButton("Easy");
    hard = createButton("Hard");
    inst = createButton("Instructions");
    redo = createButton("Play Again");
    goBack = createButton("Main Menu");

    start.setEnabled(true);
    menu2.add(start);
    over.setEnabled(true);
    menu2.add(over);
    easy.setEnabled(true);
    hard.setEnabled(true);
    inst.setEnabled(true);

    startScreen.add(menu, BorderLayout.NORTH);
    startScreen.add(menu3, BorderLayout.CENTER);
    startScreen.add(menu2, BorderLayout.SOUTH);
    menu3.add(mini, BorderLayout.CENTER);
    menu.add(label);
    menu.add(text);
    mini.add(easy, BorderLayout.NORTH);
    mini.add(hard, BorderLayout.NORTH);
    mini.add(inst, BorderLayout.SOUTH);

    frame.add(startScreen, BorderLayout.CENTER);
    frame.setVisible(true);
  }

  public Button createButton(final String name) {
    final Button button = builder.setName(name).setActionListener(game).build();
    return button;
  }

  public void addButton(final Button button) {
    field.add(button);
  }

  public void clearMain() {
    startScreen.remove(menu);
    startScreen.remove(menu2);
    startScreen.remove(menu3);

    startScreen.revalidate();
    startScreen.repaint();
  }

  public void showEndScreen(final int score) {
    startScreen.remove(field);
    startScreen.add(new JPanel(), BorderLayout.CENTER);
    startScreen.add(new JTextField("You Win"), BorderLayout.NORTH);
    startScreen.add(new JTextField("Score: " + score), BorderLayout.SOUTH);
    startScreen.add(goBack);
    goBack.setEnabled(true);
    goBack.addActionListener(game);
  }

  public void createBoard() {
    field.setLayout(new BorderLayout());
    startScreen.add(field, BorderLayout.CENTER);

    field.setLayout(new GridLayout(5, 4, 2, 2));
    field.setBackground(Color.black);
    field.requestFocus();
  }

  public JTextArea getInstructM() {
    return instructM;
  }

  public JTextField getText() {
    return text;
  }

  public Button getStartButton() {
    return start;
  }

  public Button getExitButton() {
    return over;
  }

  public Button getEasyButton() {
    return easy;
  }

  public Button getHardButton() {
    return hard;
  }

  public Button getInstructionsButton() {
    return inst;
  }

  public Button getRedoButton() {
    return redo;
  }

  public Button getGoBackButton() {
    return goBack;
  }

  public JPanel getInstructScreen() {
    return new JPanel();
  }

  public JPanel getStartScreen() {
    return startScreen;
  }

  public String getLevelText() {
    return text.getText();
  }
}
