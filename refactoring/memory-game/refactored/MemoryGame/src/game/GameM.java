package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

import button.Button;
import button.ButtonBuilder;
import command.Command;
import command.EasyCommand;
import command.ExitCommand;
import command.GoBackCommand;
import command.HardCommand;
import command.InstructionsCommand;
import command.StartCommand;
import strategy.EasyGameStrategy;
import strategy.GameStrategy;
import strategy.HardGameStrategy;
import view.GameView;

public class GameM implements ActionListener {
  private GameView view;

  private ButtonBuilder builder = new ButtonBuilder();
  private HashMap<Button, Command> buttonCommands = new HashMap<>();
  private GameStrategy strategy;

  private Button btn[] = new Button[20];

  private boolean purgatory = false;
  private boolean game_over = false;
  private int level = 0;
  private int score = 0;

  private String[] board;
  private int[] boardQ = new int[20];
  private boolean shown = true;
  private int temp = 30;
  private int temp2 = 30;
  private boolean eh = true;

  private Random randomGenerator = new Random();

  public GameM() {
    view = new GameView(this);
    initializeCommands();
  }

  public GameStrategy getStrategy() {
    return strategy;
  }

  public void setStrategy(GameStrategy strategy) {
    this.strategy = strategy;
  }

  public boolean isEh() {
    return eh;
  }

  public void setEh(boolean eh) {
    this.eh = eh;
  }

  public void initializeCommands() {
    buttonCommands.put(view.getStartButton(), new StartCommand(this));
    buttonCommands.put(view.getExitButton(), new ExitCommand());
    buttonCommands.put(view.getInstructionsButton(), new InstructionsCommand(this, view, view.getGoBackButton(), view.getInstructScreen(), view.getStartScreen()));
    buttonCommands.put(view.getGoBackButton(), new GoBackCommand(view, this));
    buttonCommands.put(view.getEasyButton(), new EasyCommand(this, view.getEasyButton(), view.getHardButton()));
    buttonCommands.put(view.getHardButton(), new HardCommand(this, view.getEasyButton(), view.getHardButton()));
  }

  public void setUpGame(int x, Boolean easy) {
    level = x;
    view.clearMain();

    board = new String[2 * x];
    for (int i = 0; i < (x * 2); i++) {
      btn[i] = builder.setName("").setBackgroundColor(new Color(220, 220, 220)).setActionListener(this).setEnabled(true).build();
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

  public void setBoardSymbols(String[] symbols, int level) {
    for (int i = 0; i < level; i++) {
      for (int z = 0; z < 2; z++) {
        while (true) {
          int y = randomGenerator.nextInt(level * 2);
          if (board[y] == null) {
            btn[y].setText(symbols[i]);
            board[y] = symbols[i];
            break;
          }
        }
      }
    }
  }

  public void hideField(int x) {
    for (int i = 0; i < (x * 2); i++) {
      btn[i].setText("");
    }
    shown = false;
  }

  public void switchSpot(int i) {
    if (!board[i].equals("done")) {
      if (btn[i].getText().isEmpty()) {
        btn[i].setText(board[i]);
      } else {
        btn[i].setText("");
      }
    }
  }

  public void showSpot(int i) {
    btn[i].setText(board[i]);
  }

  public void showField(int x, String a[]) {
    for (int i = 0; i < (x * 2); i++) {
      btn[i].setText(a[i]);
    }
    shown = true;
  }

  void waitABit() {
    try {
      Thread.sleep(5);
    } catch (Exception e) {
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
    game_over = false;
    view.clearMain();
    setUpGame(level, eh);
  }

  public String getLevelText() {
    return view.getLevelText();
  }

  public boolean isEasy() {
    return eh;
  }

  public void actionPerformed(ActionEvent click) {
    Object source = click.getSource();

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

  public void processGameTurn(Object source) {
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

  public static void main(String[] args) {
    new GameM();
  }
}
