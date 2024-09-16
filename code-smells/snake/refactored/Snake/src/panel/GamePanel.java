package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
import state.GameOverState;
import state.GameState;
import state.RunningState;
import strategy.MoveRightStrategy;
import strategy.MoveStrategy;

public class GamePanel extends JPanel implements ActionListener {

  private MoveStrategy moveStrategy;
  private GameState gameState;

  static final int SCREEN_WIDTH = 700;
  static final int SCREEN_HEIGHT = 700;
  static final int UNIT_SIZE = 26;
  static final int GAME_UNITS = SCREEN_WIDTH * SCREEN_HEIGHT / UNIT_SIZE;
  static final int DELAY = 75;
  final int[] horizontal = new int[GAME_UNITS];
  final int[] vertical = new int[GAME_UNITS];
  static final Color SNAKE_BODY_COLOR = new Color(45, 180, 0);

  int bodyParts = 6;
  int applesEaten;
  int appleX;
  int appleY;
  char direction = 'R';
  boolean running = false;

  Timer timer;
  Random random;
  private final GameRenderer renderer;

  public GamePanel() {
    super();
    random = new Random();
    this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    this.setBackground(Color.black);
    this.setFocusable(true);
    this.addKeyListener(new MyKeyAdapter());
    this.moveStrategy = new MoveRightStrategy();
    this.gameState = new RunningState();
    this.renderer = new GameRenderer(this);
    startGame();
  }

  public void setMoveStrategy(final MoveStrategy moveStrategy) {
    this.moveStrategy = moveStrategy;
  }

  public void setGameState(final GameState gameState) {
    this.gameState = gameState;
  }

  public MoveStrategy getMoveStrategy() {
    return moveStrategy;
  }

  public void startGame() {
    newApple();
    running = true;
    timer = new Timer(DELAY, this);
    timer.start();
  }

  @Override
  public void paintComponent(final Graphics graphics) {
    super.paintComponent(graphics);
    renderer.draw(graphics);
  }

  public void newApple() {
    appleX = random.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
    appleY = random.nextInt(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;
  }

  public void move() {
    for (int i = bodyParts; i > 0; i--) {
      horizontal[i] = horizontal[i - 1];
      vertical[i] = vertical[i - 1];
    }
    moveStrategy.move(horizontal, vertical, UNIT_SIZE);
  }

  public void checkApple() {
    if ((horizontal[0] == appleX) && (vertical[0] == appleY)) {
      bodyParts++;
      applesEaten++;
      newApple();
    }
    if (bodyParts == 6) {
      running = true;
    }
    if (applesEaten == 6) {
      running = true;
    }
    if (!running) {
      timer.stop();
    }
  }

  public void checkCollisions() {
    boolean hitSelf = false;
    for (int i = bodyParts; i > 0; i--) {
      if ((horizontal[0] == horizontal[i]) && (vertical[0] == vertical[i])) {
        hitSelf = true;
        break;
      }
    }
    final boolean hitWall =
        horizontal[0] < 0
            || horizontal[0] >= SCREEN_WIDTH
            || vertical[0] < 0
            || vertical[0] >= SCREEN_HEIGHT;

    if (hitSelf || hitWall) {
      running = false;
      setGameState(new GameOverState());
    }
  }

  public void restartGame() {
    bodyParts = 6;
    applesEaten = 0;
    direction = 'R';
    running = true;

    for (int i = 0; i < bodyParts; i++) {
      horizontal[i] = 0;
      vertical[i] = 0;
    }

    newApple();
    setMoveStrategy(new MoveRightStrategy());

    timer.restart();
    repaint();
  }

  public class MyKeyAdapter extends KeyAdapter {
    @Override
    public void keyPressed(final KeyEvent event) {
      gameState.handleInput(event, GamePanel.this);
    }
  }

  @Override
  public void actionPerformed(final ActionEvent event) {
    if (running) {
      move();
      checkApple();
      checkCollisions();
    }
    repaint();
  }

  public int getHorizontal(final int index) {
    return horizontal[index];
  }

  public int getVertical(final int index) {
    return vertical[index];
  }

  public int getBodyParts() {
    return bodyParts;
  }

  public void setBodyParts(final int bodyParts) {
    this.bodyParts = bodyParts;
  }

  public int getApplesEaten() {
    return applesEaten;
  }

  public void setApplesEaten(final int applesEaten) {
    this.applesEaten = applesEaten;
  }

  public int getAppleX() {
    return appleX;
  }

  public void setAppleX(final int appleX) {
    this.appleX = appleX;
  }

  public int getAppleY() {
    return appleY;
  }

  public void setAppleY(final int appleY) {
    this.appleY = appleY;
  }

  public char getDirection() {
    return direction;
  }

  public void setDirection(final char direction) {
    this.direction = direction;
  }

  public boolean isRunning() {
    return running;
  }

  public void setRunning(final boolean running) {
    this.running = running;
  }

  public Timer getTimer() {
    return timer;
  }

  public void setTimer(final Timer timer) {
    this.timer = timer;
  }

  public GameState getGameState() {
    return gameState;
  }
}
