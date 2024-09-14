package button;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

public class ButtonBuilder {
  private String name;
  private Color backgroundColor = Color.LIGHT_GRAY;
  private Color textColor = Color.BLACK;
  private Font font = new Font("Arial", Font.PLAIN, 12);
  private String tooltip;
  private boolean enabled = true;
  private ActionListener actionListener;

  public ButtonBuilder setName(final String name) {
    this.name = name;
    return this;
  }

  public ButtonBuilder setBackgroundColor(final Color color) {
    this.backgroundColor = color;
    return this;
  }

  public ButtonBuilder setTextColor(final Color color) {
    this.textColor = color;
    return this;
  }

  public ButtonBuilder setFont(final Font font) {
    this.font = font;
    return this;
  }

  public ButtonBuilder setTooltip(final String tooltip) {
    this.tooltip = tooltip;
    return this;
  }

  public ButtonBuilder setEnabled(final boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  public ButtonBuilder setActionListener(final ActionListener listener) {
    this.actionListener = listener;
    return this;
  }

  public Button build() {
    return new Button(name, backgroundColor, textColor, font, tooltip, enabled, actionListener);
  }
}
