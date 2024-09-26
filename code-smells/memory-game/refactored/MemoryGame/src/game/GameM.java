package game;

import button.Button;
import button.ButtonBuilder;
import command.Command;
import factory.ButtonFactory;
import factory.CommandFactory;
import factory.GameStrategyFactory;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;
import strategy.GameStrategy;
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
  private boolean shown = true;
  private int temp = 30;
  private int temp2 = 30;
  private boolean easyMode = true;

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

  public boolean isEasyMode() {
    return easyMode;
  }

  public void setEasyMode(final boolean easyMode) {
    this.easyMode = easyMode;
  }

  public void initializeCommands() {
    buttonCommands.put(view.getStartButton(), CommandFactory.createCommand("start", this, view));
    buttonCommands.put(view.getExitButton(), CommandFactory.createCommand("exit", this, view));
    buttonCommands.put(
        view.getInstructionsButton(), CommandFactory.createCommand("instructions", this, view));
    buttonCommands.put(view.getGoBackButton(), CommandFactory.createCommand("goback", this, view));
    buttonCommands.put(view.getEasyButton(), CommandFactory.createCommand("easy", this, view));
    buttonCommands.put(view.getHardButton(), CommandFactory.createCommand("hard", this, view));
  }

  public void setUpGame(final int gameLevel, final Boolean easy) {
    level = gameLevel;
    view.clearMain();
    board = new String[2 * gameLevel];

    for (int i = 0; i < (gameLevel * 2); i++) {
      btn[i] = ButtonFactory.createButton("", new Color(220, 220, 220), this);
      view.addButton(btn[i]);
    }

    strategy = GameStrategyFactory.createStrategy(easy);
    if (strategy != null) {
      strategy.configureBoard(this, level);
    }

    createBoard();
  }

  public void setBoardSymbols(final String[] symbols, final int level) {
    for (int i = 0; i < level; i++) {
      for (int j = 0; j < 2; j++) {
        while (true) {
          final int value = randomGenerator.nextInt(level * 2);
          if (board[value] == null) {
            btn[value].setText(symbols[i]);
            board[value] = symbols[i];
            break;
          }
        }
      }
    }
  }

  public void hideField(final int level) {
    for (int i = 0; i < (level * 2); i++) {
      btn[i].setText("");
    }
    shown = false;
  }

  public void switchSpot(final int index) {
    if (!board[index].equals("done")) {
      if (btn[index].getText().isEmpty()) {
        btn[index].setText(board[index]);
      } else {
        btn[index].setText("");
      }
    }
  }

  public void showSpot(final int index) {
    btn[index].setText(board[index]);
  }

  public void showField(final int level, final String symbols[]) {
    for (int i = 0; i < (level * 2); i++) {
      btn[i].setText(symbols[i]);
    }
    shown = true;
  }

  void waitABit() {
    try {
      Thread.sleep(5);
    } catch (final Exception exception) {
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
    setUpGame(level, easyMode);
  }

  public String getLevelText() {
    return view.getLevelText();
  }

  public boolean isEasy() {
    return easyMode;
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
