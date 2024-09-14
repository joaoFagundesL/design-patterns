package game;

import button.Button;
import button.ButtonBuilder;
import command.Command;
import command.EasyCommand;
import command.ExitCommand;
import command.GoBackCommand;
import command.HardCommand;
import command.InstructionsCommand;
import command.StartCommand;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;
import strategy.EasyGameStrategy;
import strategy.GameStrategy;
import strategy.HardGameStrategy;
import view.GameView;

public class GameM implements ActionListener {
  private final GameView view;

  private final ButtonBuilder builder = new ButtonBuilder();
  private final HashMap<Button, Command> buttonCommands = new HashMap<>();
  private GameStrategy strategy;

  private final Button btn[] = new Button[20];

  private boolean purgatory = false;
  private boolean gameOver = false;
  private int level = 0;
  private int score = 0;

  private String[] board;
  private final int[] boardQ = new int[20];
  private boolean shown = true;
  private int temp = 30;
  private int temp2 = 30;
  private boolean eh = true;

  private final Random randomGenerator = new Random();

  public GameM() {
    view = new GameView(this);
    initializeCommands();
  }

  public GameStrategy getStrategy() {
    return strategy;
  }

  public void setStrategy(final GameStrategy strategy) {
    this.strategy = strategy;
  }

  public boolean isEh() {
    return eh;
  }

  public void setEh(final boolean eh) {
    this.eh = eh;
  }

  public void initializeCommands() {
    buttonCommands.put(view.getStartButton(), new StartCommand(this));
    buttonCommands.put(view.getExitButton(), new ExitCommand());
    buttonCommands.put(
        view.getInstructionsButton(),
        new InstructionsCommand(
            this, view, view.getGoBackButton(), view.getInstructScreen(), view.getStartScreen()));
    buttonCommands.put(view.getGoBackButton(), new GoBackCommand(view, this));
    buttonCommands.put(
        view.getEasyButton(), new EasyCommand(this, view.getEasyButton(), view.getHardButton()));
    buttonCommands.put(
        view.getHardButton(), new HardCommand(this, view.getEasyButton(), view.getHardButton()));
  }

  public void setUpGame(final int x, final Boolean easy) {
    level = x;
    view.clearMain();

    board = new String[2 * x];
    for (int i = 0; i < (x * 2); i++) {
      btn[i] =
          builder
              .setName("")
              .setBackgroundColor(new Color(220, 220, 220))
              .setActionListener(this)
              .setEnabled(true)
              .build();
      view.addButton(btn[i]);
    }

    if (easy) {
      setStrategy(new EasyGameStrategy());
    } else {
      setStrategy(new HardGameStrategy());
    }

    if (strategy != null) {
      strategy.configureBoard(this, level);
    }

    createBoard();
  }

  public void setBoardSymbols(final String[] symbols, final int level) {
    for (int i = 0; i < level; i++) {
      for (int z = 0; z < 2; z++) {
        while (true) {
          final int y = randomGenerator.nextInt(level * 2);
          if (board[y] == null) {
            btn[y].setText(symbols[i]);
            board[y] = symbols[i];
            break;
          }
        }
      }
    }
  }

  public void hideField(final int x) {
    for (int i = 0; i < (x * 2); i++) {
      btn[i].setText("");
    }
    shown = false;
  }

  public void switchSpot(final int i) {
    if (!board[i].equals("done")) {
      if (btn[i].getText().isEmpty()) {
        btn[i].setText(board[i]);
      } else {
        btn[i].setText("");
      }
    }
  }

  public void showSpot(final int i) {
    btn[i].setText(board[i]);
  }

  public void showField(final int x, final String a[]) {
    for (int i = 0; i < (x * 2); i++) {
      btn[i].setText(a[i]);
    }
    shown = true;
  }

  void waitABit() {
    try {
      Thread.sleep(5);
    } catch (final Exception e) {
    }
  }

  public boolean checkWin() {
    for (int i = 0; i < (level * 2); i++) {
      if (!board[i].equals("done")) {
        return false;
      }
    }
    winner();
    return true;
  }

  public void winner() {
    view.showEndScreen(score);
  }

  public void goToMainScreen() {
    new GameM();
  }

  public void createBoard() {
    view.createBoard();
  }

  public void startGame() {
    score = 0;
    gameOver = false;
    view.clearMain();
    setUpGame(level, eh);
  }

  public String getLevelText() {
    return view.getLevelText();
  }

  public boolean isEasy() {
    return eh;
  }

  @Override
  public void actionPerformed(final ActionEvent click) {
    final Object source = click.getSource();

    if (buttonCommands.containsKey(source)) {
      buttonCommands.get(source).execute();
    }

    if (purgatory) {
      switchSpot(temp2);
      switchSpot(temp);
      score++;
      temp = (level * 2);
      temp2 = 30;
      purgatory = false;
    }

    processGameTurn(source);
  }

  public void processGameTurn(final Object source) {
    for (int i = 0; i < (level * 2); i++) {
      if (source == btn[i]) {
        if (shown) {
          hideField(level);
        } else {
          switchSpot(i);
          if (temp >= (level * 2)) {
            temp = i;
          } else {
            if (!board[temp].equals(board[i]) || temp == i) {
              temp2 = i;
              purgatory = true;
            } else {
              board[i] = "done";
              board[temp] = "done";
              checkWin();
              temp = (level * 2);
            }
          }
        }
      }
    }
  }

  public static void main(final String[] args) {
    new GameM();
  }
}
