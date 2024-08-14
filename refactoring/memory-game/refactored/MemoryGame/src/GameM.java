// note the count variable is not reset when a new game is pressed

// https://github.com/dfutran/Memory-Game/blob/master/MemoryGame.java

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.io.*;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
// btn1.setBackground(colors[index]
public class GameM implements ActionListener {
    JFrame frame = new JFrame("Memory Game");

    JPanel field = new JPanel();
    JPanel menu = new JPanel();
    JPanel menu2 = new JPanel();
    JPanel menu3 = new JPanel();
    JPanel mini = new JPanel();

    ButtonBuilder builder = new ButtonBuilder();
    
    private HashMap<Button, Command> buttonCommands = new HashMap<>();
    private StartCommand startCommand;
    private ExitCommand exitCommand;
    
    private GameStrategy strategy;

    JPanel start_screen = new JPanel();
    JPanel end_screen = new JPanel();
    JPanel instruct_screen = new JPanel();

    Button btn[] = new Button[20];

    Button start;
    Button over;
    Button easy;
    Button hard;
    Button inst;
    Button redo;
    Button goBack;

    Random randomGenerator = new Random();
    private boolean purgatory = false;
    JLabel winner;
    Boolean game_over = false;
    private int level = 0;
    private int score = 0;

    private String[] board;
    private int[] boardQ = new int[20];
    Boolean shown = true;
    int temp = 30;
    int temp2 = 30;
    String a[] = new String[10];
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
        start_screen.setLayout(new BorderLayout());
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
       
        start_screen.add(menu, BorderLayout.NORTH);
        start_screen.add(menu3, BorderLayout.CENTER);
        start_screen.add(menu2, BorderLayout.SOUTH);
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

        frame.add(start_screen, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private Button createButton(String name) {
        return builder.setName(name).setActionListener(this).build();
    }
    
    public void initializeCommands() {
         buttonCommands.put(start, new StartCommand(this));
         buttonCommands.put(over, new ExitCommand());
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
        start_screen.remove(field);
        start_screen.add(end_screen, BorderLayout.CENTER);
        end_screen.add(new TextField("You Win"), BorderLayout.NORTH);
        end_screen.add(new TextField("Score: " + score), BorderLayout.SOUTH);
        end_screen.add(goBack);
        goBack.setEnabled(true);
        goBack.addActionListener(this);
    }

    public void goToMainScreen() { new GameM(); }

    public void createBoard() { // this is just gui stuff to show the board
        field.setLayout(new BorderLayout());
        start_screen.add(field, BorderLayout.CENTER);

        field.setLayout(new GridLayout(5, 4, 2, 2));
        field.setBackground(Color.black);
        field.requestFocus();
    }
    public void clearMain() { // clears the main menu so i can add the board or instructions
        start_screen.remove(menu);
        start_screen.remove(menu2);
        start_screen.remove(menu3);

        start_screen.revalidate();
        start_screen.repaint();
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
        
        if (source == inst) { // this just sets the instruction screen
            clearMain();

            start_screen.add(instruct_screen, BorderLayout.NORTH);

            JPanel one = new JPanel();
            one.setLayout(new FlowLayout(FlowLayout.CENTER));
            JPanel two = new JPanel();
            two.setLayout(new FlowLayout(FlowLayout.CENTER));
            instruct_screen.setLayout(new BorderLayout());
            instruct_screen.add(one, BorderLayout.NORTH);
            instruct_screen.add(two, BorderLayout.SOUTH);

            one.add(instructM);
            two.add(goBack);
            goBack.addActionListener(this);
            goBack.setEnabled(true);
        }
        
        if (source == goBack) { // backt to main screen
            frame.dispose();
            goToMainScreen();
        }
        
        if (source == easy) { // sets the type. ex. if easy is clicked it turns
                              // blue and hard remains black
            eh = true;
            easy.setForeground(Color.BLUE);
            hard.setForeground(Color.BLACK);
        } else if (source == hard) {
            eh = false;
            hard.setForeground(Color.BLUE);
            easy.setForeground(Color.BLACK);
        }

        for (int i = 0; i < (level * 2);
             i++) { // gameplay when a button is pressed
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