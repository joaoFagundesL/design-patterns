package factory;

import button.Button;
import button.ButtonBuilder;
import java.awt.Color;
import java.awt.event.ActionListener;

public class ButtonFactory {
  public static Button createButton(
      final String name, final Color backgroundColor, final ActionListener actionListener) {
    return new ButtonBuilder()
        .setName(name)
        .setBackgroundColor(backgroundColor)
        .setActionListener(actionListener)
        .setEnabled(true)
        .build();
  }

  public static Button createDefaultButton(final String name, final ActionListener actionListener) {
    return createButton(name, Color.LIGHT_GRAY, actionListener);
  }

  public static Button createDisabledButton(
      final String name, final ActionListener actionListener) {
    final Button button = createButton(name, Color.LIGHT_GRAY, actionListener);
    button.setEnabled(false);
    return button;
  }

  public static Button createCustomButton(
      final String name,
      final Color backgroundColor,
      final ActionListener actionListener,
      final boolean enabled) {
    return new ButtonBuilder()
        .setName(name)
        .setBackgroundColor(backgroundColor)
        .setActionListener(actionListener)
        .setEnabled(enabled)
        .build();
  }
}
