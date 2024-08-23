package src;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import state.GameOverState;
import state.GameState;
import state.RunningState;
import state.WaitingState;
import strategy.MoveDownStrategy;
import strategy.MoveLeftStrategy;
import strategy.MoveRightStrategy;
import strategy.MoveUpStrategy;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private Timer aTimer;
    private GameState currentState;
    private Alister alister;
    private Monster mons;
    private Monster mons1;
    private Monster mons2;
    private int score = 0;

    public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public GamePanel() {
        aTimer = new Timer(50, this); // 50 ms 0.05 sec or 20x per second
        addKeyListener(this);
        reset();
    }

    public void reset() {
        alister = new Alister(300, 500, 5);
        mons = new Monster(300, 100, 5);
        mons1 = new Monster(350, 100, 5);
        mons2 = new Monster(250, 100, 5);
        score = 0;
        aTimer.start();
        setState(new WaitingState()); // Initialize with a waiting state
    }

    public void setState(GameState state) {
        this.currentState = state;
    }

    public boolean isGameOver() {
        return alister.collideBorder() || alister.collideWithMonster(mons) ||
               alister.collideWithMonster(mons1) || alister.collideWithMonster(mons2);
    }

    public void startTimer() {
        aTimer.start();
        currentState.handleStart(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        currentState.handleInput(this, e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentState != null) {
            currentState.draw(this, g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == aTimer) {
            currentState.update(this);
            repaint();
            requestFocus();
        }
    }

    public void moveLeft() {
        alister.setMovementStrategy(new MoveLeftStrategy());
        alister.move();
    }

    public void moveRight() {
        alister.setMovementStrategy(new MoveRightStrategy());
        alister.move();
    }

    public void moveUp() {
        alister.setMovementStrategy(new MoveUpStrategy());
        alister.move();
    }

    public void moveDown() {
        alister.setMovementStrategy(new MoveDownStrategy());
        alister.move();
    }

    public void updateGame() {
        // Update game state, such as moving monsters, updating positions, etc.
        score++;
        if (isGameOver()) {
            setState(new GameOverState());
        } else {
            mons.hunting();
            mons1.hunting();
            mons2.hunting();
            alister.updatePosition();
            mons.updatePosition();
            mons1.updatePosition();
            mons2.updatePosition();
            mons.handleBorderCollision();
            mons1.handleBorderCollision();
            mons2.handleBorderCollision();
        }
    }

    public Alister getAlister() {
        return alister;
    }

    public Monster getMonster() {
        return mons;
    }

    public Monster getMonster1() {
        return mons1;
    }

    public Monster getMonster2() {
        return mons2;
    }
    
    public void processMovement(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            moveLeft();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            moveRight();
        } else if (keyCode == KeyEvent.VK_UP) {
            moveUp();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            moveDown();
        }
    }
}