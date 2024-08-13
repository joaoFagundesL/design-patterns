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
    private ActionListener actionListener;
	
	Button(String name, Color backgroundColor, Color textColor, Font font, String tooltip, boolean enabled, ActionListener actionListener) {
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}