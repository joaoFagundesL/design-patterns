package button;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Button extends JButton {

  private String name;
  private Color backgroundColor = Color.LIGHT_GRAY;
  private Color textColor = Color.BLACK;
  private Font font = new Font("Arial", Font.PLAIN, 12);
  private String tooltip;
  private boolean enabled = true;
  private final ActionListener actionListener;

  Button(
      final String name,
      final Color backgroundColor,
      final Color textColor,
      final Font font,
      final String tooltip,
      final boolean enabled,
      final ActionListener actionListener) {
    super(name);
    this.name = name;
    this.backgroundColor = backgroundColor;
    this.textColor = textColor;
    this.font = font;
    this.tooltip = tooltip;
    this.enabled = enabled;
    this.actionListener = actionListener;

    addActionListener(this.actionListener);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(final String name) {
    this.name = name;
  }

  public Color getBackgroundColor() {
    return backgroundColor;
  }

  public void setBackgroundColor(final Color backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  public Color getTextColor() {
    return textColor;
  }

  public void setTextColor(final Color textColor) {
    this.textColor = textColor;
  }

  @Override
  public Font getFont() {
    return font;
  }

  @Override
  public void setFont(final Font font) {
    this.font = font;
  }

  public String getTooltip() {
    return tooltip;
  }

  public void setTooltip(final String tooltip) {
    this.tooltip = tooltip;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  @Override
  public void setEnabled(final boolean enabled) {
    this.enabled = enabled;
  }
}
