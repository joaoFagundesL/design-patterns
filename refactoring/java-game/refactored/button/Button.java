package button;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Button extends JButton {
	
	private String name;
	private int x;
	private int y;
	private int width;
	private int height;
	private ActionListener actionListener;

	public Button(String name, int x, int y, int width, int height, ActionListener actionListener) {
		super(name);
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.actionListener = actionListener;

		setFont(new Font("Dialog", Font.BOLD, 12));
		setToolTipText("");
		setBounds(x, y, width, height);

		addActionListener(actionListener);
	}

	public String getName() {
		return name;
	}

	public void setLetter(String letter) {
		this.name = letter;
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

}