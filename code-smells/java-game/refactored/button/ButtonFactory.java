package button;

import java.awt.event.ActionListener;

public class ButtonFactory {
  private final ButtonBuilder builder;

  public ButtonFactory() {
    this.builder = new ButtonBuilder();
  }

  public Button createButton(
      final int horizontal,
      final int vertical,
      final int width,
      final int height,
      final String text,
      final ActionListener actionListener) {
    return builder
        .withPosition(horizontal, vertical)
        .withLetter(text)
        .withSize(width, height)
        .setActionListener(actionListener)
        .build();
  }
}
