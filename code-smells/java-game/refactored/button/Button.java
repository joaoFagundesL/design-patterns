package button;

import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Button extends JButton {

  private String name;
  private int horizontal;
  private int vertical;
  private int width;
  private int height;
  private final ActionListener actionListener;

  public Button(
      final String name,
      final int horizontal,
      final int vertical,
      final int width,
      final int height,
      final ActionListener actionListener) {
    super(name);
    this.name = name;
    this.horizontal = horizontal;
    this.vertical = vertical;
    this.width = width;
    this.height = height;
    this.actionListener = actionListener;

    setFont(new Font("Dialog", Font.BOLD, 12));
    setToolTipText("");
    setBounds(horizontal, vertical, width, height);

    addActionListener(actionListener);
  }

  public String getName() {
    return name;
  }

  public void setLetter(final String letter) {
    this.name = letter;
  }

  public int getHorizontal() {
    return horizontal;
  }

  public void setHorizontal(final int horizontal) {
    this.horizontal = horizontal;
  }

  public int getVertical() {
    return vertical;
  }

  public void setVertical(final int vertical) {
    this.vertical = vertical;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(final int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(final int height) {
    this.height = height;
  }
}
