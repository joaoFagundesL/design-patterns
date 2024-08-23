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
    private JButton btnStart, btnExit, btnRestart;
    private JButton btnLeft, btnRight, btnUp, btnDown;
    private GamePanel gamePanel;
    private Map<JButton, Command> commandMap;

    public PacMinah() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Pac Minah v1.0 - By Alister Animesh Baroi 0340938");
        setSize(SWIDTH, SHEIGHT);
        setLayout(null);
        gamePanel = new GamePanel();
        commandMap = new HashMap<>();
    }

    public void init() {
        gamePanel.setBounds(25, 25, 600, 630);
        add(gamePanel);

        btnStart = new JButton("START");
        btnStart.setBounds(650, 150, 100, 25);
        btnStart.addActionListener(this);
        add(btnStart);

        btnRestart = new JButton("RESTART");
        btnRestart.setBounds(762, 150, 100, 25);
        btnRestart.addActionListener(this);
        add(btnRestart);

        btnLeft = new JButton("LEFT");
        btnLeft.setBounds(650, 250, 100, 25);
        btnLeft.addActionListener(this);
        add(btnLeft);

        btnRight = new JButton("RIGHT");
        btnRight.setBounds(750, 250, 100, 25);
        btnRight.addActionListener(this);
        add(btnRight);

        btnUp = new JButton("UP");
        btnUp.setBounds(700, 225, 100, 25);
        btnUp.addActionListener(this);
        add(btnUp);

        btnDown = new JButton("DOWN");
        btnDown.setBounds(700, 275, 100, 25);
        btnDown.addActionListener(this);
        add(btnDown);

        JLabel labName = new JLabel("Tip: Use keyboard arrow keys");
        labName.setBounds(655, 310, 200, 25);
        add(labName);

        btnExit = new JButton("EXIT");
        btnExit.setBounds(650, 350, 200, 25);
        btnExit.addActionListener(this);
        add(btnExit);

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
