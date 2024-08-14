// note the count variable is not reset when a new game is pressed

// https://github.com/dfutran/Memory-Game/blob/master/MemoryGame.java
package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import button.*;
import command.*;
import strategy.*;

public class GameM implements ActionListener {
    public JFrame frame = new JFrame("Memory Game");

	JPanel field = new JPanel();
    JPanel menu = new JPanel();
    JPanel menu2 = new JPanel();
    JPanel menu3 = new JPanel();
    JPanel mini = new JPanel();

    ButtonBuilder builder = new ButtonBuilder();
    
    private HashMap<Button, Command> buttonCommands = new HashMap<>();
    
    private GameStrategy strategy;

    private JPanel startScreen = new JPanel();
 
	private JPanel endScreen = new JPanel();
    private JPanel instructScreen = new JPanel();

    Button btn[] = new Button[20];

    public Button start;
    public Button over;
    public Button easy;

    public Button hard;
    public Button inst;
    public Button redo;
    public Button goBack;

	Random randomGenerator = new Random();
    private boolean purgatory = false;
    private JLabel winner;
    private Boolean game_over = false;
    private int level = 0;
    private int score = 0;

    private String[] board;
    private int[] boardQ = new int[20];
    Boolean shown = true;
    private int temp = 30;
    private int temp2 = 30;
    private String a[] = new String[10];
    private boolean eh = true;
	public GameStrategy getStrategy() { return strategy; }

    public void setStrategy(GameStrategy strategy) { this.strategy = strategy; }

    private JLabel label = new JLabel("Enter level from 1 to 10");
    private JTextField text = new JTextField(10);
    private JTextArea instructM = new JTextArea(
        "When the game begins, the screen will be filled\nwith pairs of buttons.\n Memorize their placement.\nOnce you press any button, they will all clear. \n Your goal is to click the matching buttons in a row.\nWhen you finish that, you win.\nEvery incorrect click gives you a point (those are bad).\n GOOD LUCK! \n"
        +
        "for a single level: enter a level between 1 and 10,\nselect easy or hard, then press start.");

   

	public GameM() {
        frame.setSize(500, 300);
        frame.setLocation(500, 300);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startScreen.setLayout(new BorderLayout());
        menu.setLayout(new FlowLayout(FlowLayout.CENTER));
        menu2.setLayout(new FlowLayout(FlowLayout.CENTER));
        menu3.setLayout(new FlowLayout(FlowLayout.CENTER));
        mini.setLayout(new FlowLayout(FlowLayout.CENTER));

        start = createButton("Start");
        over = createButton("Exit");
        easy = createButton("Easy");
        hard = createButton("Hard");
        inst = createButton("Instructions");
        redo = createButton("Play Again");
        goBack = createButton("Main Menu");
        
        initializeCommands();
       
        startScreen.add(menu, BorderLayout.NORTH);
        startScreen.add(menu3, BorderLayout.CENTER);
        startScreen.add(menu2, BorderLayout.SOUTH);
        menu3.add(mini, BorderLayout.CENTER);
        menu.add(label);
        menu.add(text);
        mini.add(easy, BorderLayout.NORTH);
        mini.add(hard, BorderLayout.NORTH);
        mini.add(inst, BorderLayout.SOUTH);

        start.setEnabled(true);
        menu2.add(start);
        over.setEnabled(true);
        menu2.add(over);
        easy.setEnabled(true);
        hard.setEnabled(true);
        inst.setEnabled(true);

        frame.add(startScreen, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    private Button createButton(String name) {
        return builder.setName(name).setActionListener(this).build();
    }
    
    public boolean isEh() {
		return eh;
	}

	public void setEh(boolean eh) {
		this.eh = eh;
	}

    
    public JTextArea getInstructM() {
		return instructM;
	}

	public void setInstructM(JTextArea instructM) {
		this.instructM = instructM;
	}
    
    public void initializeCommands() {
         buttonCommands.put(start, new StartCommand(this));
         buttonCommands.put(over, new ExitCommand());
         buttonCommands.put(inst, new InstructionsCommand(this, goBack, instructScreen, startScreen));
         buttonCommands.put(goBack, new GoBackCommand(this));
         buttonCommands.put(easy, new EasyCommand(this, easy, hard));
         buttonCommands.put(hard, new HardCommand(this, easy, hard));
    }

    public void setUpGame(int x, Boolean easy) {
        level = x;
        clearMain();

        board = new String[2 * x];
        for (int i = 0; i < (x * 2); i++) {
            btn[i] = builder.setName("")
                         .setBackgroundColor(new Color(220, 220, 220))
                         .setActionListener(this)
                         .setEnabled(true)
                         .build();

            field.add(btn[i]);
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
        // Logic to place symbols on the board
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

    public void hideField(int x) { // this sets all the boxes blank
        for (int i = 0; i < (x * 2); i++) {
            /*if(boardQ[i]==0)*/
            btn[i].setText("");
        }
        shown = false;
    }
    
    public void switchSpot(int i) { // this will switch the current spot to
                                    // either blank or what it should have
        if (board[i] != "done") { // when a match is correctly chosen, it will
                                  // no longer switch when pressed
            if (btn[i].getText() == "") {
                btn[i].setText(board[i]);
                // shown=true;
            } else {
                btn[i].setText("");
                // shown=false;
            }
        }
    }
    public void showSpot(int i) { btn[i].setText(board[i]); }

    public void showField(int x, String a[]) { // this shows all the symbols on the field
        for (int i = 0; i < (x * 2); i++) {
            btn[i].setText(a[i]);
        }
        shown = true;
    }
    
    void waitABit() { // this was an attempt at fixing the glitch i told you about

        try {
            Thread.sleep(5);
        } catch (Exception e) {
        }
    }
    
    public boolean checkWin() { // checks if every spot is labeled as done
        for (int i = 0; i < (level * 2); i++) {
            if (board[i] != "done")
                return false;
        }
        winner();
        return true;
    }
    
    public void winner() {
        startScreen.remove(field);
        startScreen.add(endScreen, BorderLayout.CENTER);
        endScreen.add(new TextField("You Win"), BorderLayout.NORTH);
        endScreen.add(new TextField("Score: " + score), BorderLayout.SOUTH);
        endScreen.add(goBack);
        goBack.setEnabled(true);
        goBack.addActionListener(this);
    }

    public void goToMainScreen() { new GameM(); }

    public void createBoard() { // this is just gui stuff to show the board
        field.setLayout(new BorderLayout());
        startScreen.add(field, BorderLayout.CENTER);

        field.setLayout(new GridLayout(5, 4, 2, 2));
        field.setBackground(Color.black);
        field.requestFocus();
    }
    public void clearMain() { // clears the main menu so i can add the board or instructions
        startScreen.remove(menu);
        startScreen.remove(menu2);
        startScreen.remove(menu3);

        startScreen.revalidate();
        startScreen.repaint();
    }
    
    public void startGame() {
        score = 0;
        game_over = false;
        clearMain(); 
        setUpGame(level, eh); 
    }

    
    public String getLevelText() {
        return text.getText(); 
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
	    for(int i = 0; i < (level * 2); i++) { // gameplay when a button is pressed
	        if (source == btn[i]) {
	            if (shown) {
	                hideField(level); // if first time, hides field
	            } else {              // otherwise play
	                switchSpot(i);
	                if (temp >= (level * 2)) {
	                    temp = i;
	                } else {
	                    if ((board[temp] != board[i]) || (temp == i)) {
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
        new GameM(); // Calling the class construtor.
    }
}