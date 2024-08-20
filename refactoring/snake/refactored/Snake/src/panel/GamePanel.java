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
import strategy.Direction;
import strategy.MoveDownStrategy;
import strategy.MoveLeftStrategy;
import strategy.MoveRightStrategy;
import strategy.MoveStrategy;
import strategy.MoveUpStrategy;

public class GamePanel extends JPanel implements ActionListener {

	private MoveStrategy moveStrategy;
	private GameState gameState;
	
	static final int SCREEN_WIDTH = 700;
	static final int SCREEN_HEIGHT = 700;
	static final int UNIT_SIZE = 26;
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
	static final int DELAY = 75;
	final int[] x = new int[GAME_UNITS];
	final int[] y = new int[GAME_UNITS];
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

	public void setMoveStrategy(MoveStrategy moveStrategy) {
		this.moveStrategy = moveStrategy;
	}

	public void setGameState(GameState gameState) {
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

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	public void draw(Graphics g) {
		if (running) {
			drawGrid(g);
			drawApple(g);
			drawSnake(g);
		} else {
			gameOver(g);
		}
	}

	private void drawGrid(Graphics g) {
		for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
			g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
			g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
		}
	}

	private void drawApple(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
	}

	private void drawSnake(Graphics g) {
		for (int i = 0; i < bodyParts; i++) {
			if (i == 0) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(SNAKE_BODY_COLOR);
			}
			g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
		}
	}

	public void newApple() {
		appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
		appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
	}

	public void move() {
		for (int i = bodyParts; i > 0; i--) {
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}
		moveStrategy.move(x, y, UNIT_SIZE);
	}

	public void checkApple() {
		if ((x[0] == appleX) && (y[0] == appleY)) {
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
			if ((x[0] == x[i]) && (y[0] == y[i])) {
				hitSelf = true;
				break;
			}
		}
		boolean hitWall = (x[0] < 0 || x[0] >= SCREEN_WIDTH || y[0] < 0 || y[0] >= SCREEN_HEIGHT);

		if (hitSelf || hitWall) {
			running = false;
			setGameState(new GameOverState());
		}
	}

	public void gameOver(Graphics g) {
		g.setColor(Color.RED);
		g.setFont(new java.awt.Font("Times New Roman", 1, 75));
		g.drawString("Game Over", 150, 300);
		g.setFont(new java.awt.Font("Times New Roman", 1, 50));
		g.drawString("Score: " + applesEaten, 150, 400);
		g.setFont(new java.awt.Font("Times New Roman", 1, 25));
		g.drawString("Press Space to Restart", 150, 500);
		g.setFont(new java.awt.Font("Times New Roman", 1, 25));
		g.drawString("Press Esc to Exit", 150, 550);
		// restart the game

	}

	public void restartGame() {
		bodyParts = 6;
		applesEaten = 0;
		direction = 'R';
		running = true;

		for (int i = 0; i < bodyParts; i++) {
			x[i] = 0;
			y[i] = 0;
		}

		newApple();
		setMoveStrategy(new MoveRightStrategy());

		timer.restart();
		repaint();
	}

	public class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			gameState.handleInput(e, GamePanel.this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
	}
}
