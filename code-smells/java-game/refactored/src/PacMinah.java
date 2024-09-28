package src;

import button.Button;
import button.ButtonFactory; // Import the factory
import command.Command;
import command.ExitCommand;
import command.MoveDownCommand;
import command.MoveLeftCommand;
import command.MoveRightCommand;
import command.MoveUpCommand;
import command.RestartCommand;
import command.StartCommand;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PacMinah extends JFrame implements ActionListener {
  private final int SWIDTH = 900;
  private final int SHEIGHT = 700;
  private Button btnStart, btnExit, btnRestart;
  private Button btnLeft, btnRight, btnUp, btnDown;
  private final GamePanel gamePanel;
  private final Map<Button, Command> commandMap;
  private final ButtonFactory buttonFactory;
  private final SoundManager soundManager;

  public PacMinah() {
    super();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Pac Minah v1.0 - By Alister Animesh Baroi 0340938");
    setSize(SWIDTH, SHEIGHT);
    setLayout(null);
    gamePanel = new GamePanel();
    commandMap = new HashMap<>();
    soundManager = new SoundManager(); // Initialize SoundManager
    buttonFactory = new ButtonFactory(); // Initialize ButtonFactory
  }

  public void init() {
    gamePanel.setBounds(25, 25, 600, 630);
    add(gamePanel);

    btnStart = buttonFactory.createButton(650, 150, 100, 25, "START", this);
    btnRestart = buttonFactory.createButton(762, 150, 100, 25, "RESTART", this);
    btnLeft = buttonFactory.createButton(650, 250, 100, 25, "LEFT", this);
    btnRight = buttonFactory.createButton(750, 250, 100, 25, "RIGHT", this);
    btnUp = buttonFactory.createButton(700, 225, 100, 25, "UP", this);
    btnDown = buttonFactory.createButton(700, 275, 100, 25, "DOWN", this);
    btnExit = buttonFactory.createButton(650, 350, 200, 25, "EXIT", this);

    add(btnStart);
    add(btnRestart);
    add(btnLeft);
    add(btnRight);
    add(btnUp);
    add(btnDown);
    add(btnExit);

    final JLabel labName = new JLabel("Tip: Use keyboard arrow keys");
    labName.setBounds(655, 310, 200, 25);
    add(labName);

    commandMap.put(btnStart, new StartCommand(gamePanel));
    commandMap.put(btnRestart, new RestartCommand(gamePanel));
    commandMap.put(btnLeft, new MoveLeftCommand(gamePanel));
    commandMap.put(btnRight, new MoveRightCommand(gamePanel));
    commandMap.put(btnUp, new MoveUpCommand(gamePanel));
    commandMap.put(btnDown, new MoveDownCommand(gamePanel));
    commandMap.put(btnExit, new ExitCommand());

    setVisible(true);
    soundManager.playSound("alister.wav");
  }

  @Override
  public void actionPerformed(final ActionEvent event) {
    final Command command = commandMap.get(event.getSource());
    if (command != null) {
      command.execute();
    }
  }

  public static void main(final String[] args) {
    final PacMinah pm = new PacMinah();
    pm.init();
  }
}
