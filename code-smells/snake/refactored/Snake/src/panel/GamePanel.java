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
  static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
  static final int DELAY = 75;
  final int[] horizontal = new int[GAME_UNITS];
  final int[] vertical = new int[GAME_UNITS];
  private static final Color SNAKE_BODY_COLOR = new Color(45, 180, 0);
  int bodyParts = 6;
  int applesEaten;
  int appleX;
  int appleY;
  char direction = 'R';
  boolean running = false;
  Timer timer;
  Random random;

  public GamePanel() {
    random = new Random();
    this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    this.setBackground(Color.black);
    this.setFocusable(true);
    this.addKeyListener(new MyKeyAdapter());
    this.moveStrategy = new MoveRightStrategy();
    this.gameState = new RunningState();
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

  public void paintComponent(final Graphics graphics) {
    super.paintComponent(graphics);
    draw(graphics);
  }

  public void draw(final Graphics graphics) {
    if (running) {
      drawGrid(graphics);
      drawApple(graphics);
      drawSnake(graphics);
    } else {
      gameOver(graphics);
    }
  }

  private void drawGrid(final Graphics graphics) {
    for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
      graphics.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
      graphics.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
    }
  }

  private void drawApple(final Graphics graphics) {
    graphics.setColor(Color.RED);
    graphics.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
  }

  private void drawSnake(final Graphics graphics) {
    for (int i = 0; i < bodyParts; i++) {
      if (i == 0) {
        graphics.setColor(Color.GREEN);
      } else {
        graphics.setColor(SNAKE_BODY_COLOR);
      }
      graphics.fillRect(horizontal[i], vertical[i], UNIT_SIZE, UNIT_SIZE);
    }
  }

  public void newApple() {
    appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
    appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
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
        (horizontal[0] < 0
            || horizontal[0] >= SCREEN_WIDTH
            || vertical[0] < 0
            || vertical[0] >= SCREEN_HEIGHT);

    if (hitSelf || hitWall) {
      running = false;
      setGameState(new GameOverState());
    }
  }

  public void gameOver(final Graphics graphics) {
    graphics.setColor(Color.RED);
    graphics.setFont(new java.awt.Font("Times New Roman", 1, 75));
    graphics.drawString("Game Over", 150, 300);
    graphics.setFont(new java.awt.Font("Times New Roman", 1, 50));
    graphics.drawString("Score: " + applesEaten, 150, 400);
    graphics.setFont(new java.awt.Font("Times New Roman", 1, 25));
    graphics.drawString("Press Space to Restart", 150, 500);
    graphics.setFont(new java.awt.Font("Times New Roman", 1, 25));
    graphics.drawString("Press Esc to Exit", 150, 550);
    // restart the game

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
}
