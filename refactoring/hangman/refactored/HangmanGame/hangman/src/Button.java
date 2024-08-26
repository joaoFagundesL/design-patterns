import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Button extends JButton implements Component{
  private Mediator mediator;
  private String letter;
  private int x;
  private int y;
  private int width;
  private int height;


  public Button(String letter, int x, int y, int width, int height) {
    super(letter);
    this.letter = letter;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;

    setFont(new Font("Dialog", Font.BOLD, 12));
    setToolTipText("");
    setBounds(x, y, width, height);

    addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        fireActionPerformed(e);
      }
    });
  }

  public String getLetter() {
    return letter;
  }

  public void setLetter(String letter) {
    this.letter = letter;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public Mediator getMediator() {
    return mediator;
  }

  @Override
  public void setMediator(Mediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public String getName() {
    return letter;
  }

  protected void fireActionPerformed(ActionEvent actionEvent) {
    String palavraDoJogo = mediator.getPalavraDoJogo();
    int[] chances = mediator.getChances();
    JLabel labelChances = mediator.getLabelChances();
    int encontrou = mediator.getEncontrou();
    List<JLabel> campos = mediator.getCampos();

    encontrou = mediator.checkGuess(palavraDoJogo, letter.charAt(0), campos, this);
    mediator.processGuess(encontrou, chances, labelChances, palavraDoJogo);

  }

}
