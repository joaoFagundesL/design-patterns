package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import button.Button;
import button.ButtonBuilder;
import command.Command;
import command.ExitCommand;
import command.MoveDownCommand;
import command.MoveLeftCommand;
import command.MoveRightCommand;
import command.MoveUpCommand;
import command.RestartCommand;
import command.StartCommand;

public class PacMinah extends JFrame implements ActionListener {
    private final int SWIDTH = 900;
    private final int SHEIGHT = 700;
    private Button btnStart, btnExit, btnRestart;
    private Button btnLeft, btnRight, btnUp, btnDown;
    private GamePanel gamePanel;
    private Map<JButton, Command> commandMap;
    private ButtonBuilder builder;

    public PacMinah() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Pac Minah v1.0 - By Alister Animesh Baroi 0340938");
        setSize(SWIDTH, SHEIGHT);
        setLayout(null);
        gamePanel = new GamePanel();
        builder = new ButtonBuilder();
        commandMap = new HashMap<>();
    }

    public void init() {
        gamePanel.setBounds(25, 25, 600, 630);
        add(gamePanel);
        
        btnStart = createButton(btnStart, 650, 150, 100, 25, "START", this);
        btnRestart = createButton(btnRestart, 762, 150, 100, 25, "RESTART", this);
        btnLeft = createButton(btnLeft, 650, 250, 100, 25, "LEFT", this);
        btnRight = createButton(btnRight, 750, 250, 100, 25, "RIGHT", this);
        btnUp = createButton(btnUp, 700, 225, 100, 25, "UP", this);
        btnDown = createButton(btnDown, 700, 275, 100, 25, "DOWN", this);
        btnExit = createButton(btnExit, 650, 350, 200, 25, "EXIT", this);
        add(btnStart);
        add(btnRestart);
        add(btnLeft);
        add(btnRight);
        add(btnUp);
        add(btnDown);
        add(btnExit);
        
        JLabel labName = new JLabel("Tip: Use keyboard arrow keys");
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
        File soundFile = new File("src/alister.wav").getAbsoluteFile();
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            Clip myClip = AudioSystem.getClip();
            myClip.open(ais);
            myClip.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public Button createButton(Button btn, int x, int y, int width, int height, String name, ActionListener al) {
    	btn = builder.withPosition(x, y)
    			.withLetter(name)
    			.withSize(width, height)
    			.setActionListener(al)
    			.build();
    	return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Command command = commandMap.get(e.getSource());
        if (command != null) {
            command.execute();
        }
    }

    public static void main(String[] args) {
        PacMinah pm = new PacMinah();
        pm.init();
    }
}
