package com.utfpr.src.button;

import com.utfpr.src.mediator.Component;
import com.utfpr.src.mediator.Mediator;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Button extends JButton implements Component {
  private Mediator mediator;
  private String letter;
  private int positionX;
  private int positionY;
  private int width;
  private int height;

  public Button(
      final String letter,
      final int positionX,
      final int positionY,
      final int width,
      final int height) {
    super(letter);
    this.letter = letter;
    this.positionX = positionX;
    this.positionY = positionY;
    this.width = width;
    this.height = height;

    setFont(new Font("Dialog", Font.BOLD, 12));
    setToolTipText("");
    setBounds(positionX, positionY, width, height);

    addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(final ActionEvent actionEvent) {
            fireActionPerformed(actionEvent);
          }
        });
  }

  public String getLetter() {
    return letter;
  }

  public void setLetter(final String letter) {
    this.letter = letter;
  }

  @Override
  public int getX() {
    return positionX;
  }

  public void setX(final int positionX) {
    this.positionX = positionX;
  }

  @Override
  public int getY() {
    return positionY;
  }

  public void setY(final int positionY) {
    this.positionY = positionY;
  }

  @Override
  public int getWidth() {
    return width;
  }

  public void setWidth(final int width) {
    this.width = width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  public void setHeight(final int height) {
    this.height = height;
  }

  public Mediator getMediator() {
    return mediator;
  }

  @Override
  public void setMediator(final Mediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public String getName() {
    return letter;
  }

  @Override
  protected void fireActionPerformed(final ActionEvent actionEvent) {
    setEnabled(false);
    final int encontrou = mediator.checkGuess(letter.charAt(0));
    // mediator.processGuess(encontrou);
  }
}
