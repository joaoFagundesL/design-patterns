package button;

import java.awt.event.ActionListener;

public class ButtonBuilder {
  private int horizontal;
  private int vertical;
  private int width;
  private int height;
  private String name;
  private ActionListener actionListener;

  public ButtonBuilder withLetter(final String name) {
    this.name = name;
    return this;
  }

  public ButtonBuilder withPosition(final int horizontal, final int vertical) {
    this.horizontal = horizontal;
    this.vertical = vertical;
    return this;
  }

  public ButtonBuilder withSize(final int width, final int height) {
    this.width = width;
    this.height = height;
    return this;
  }

  public ButtonBuilder setActionListener(final ActionListener listener) {
    this.actionListener = listener;
    return this;
  }

  public Button build() {
    return new Button(name, horizontal, vertical, width, height, actionListener);
  }
}
