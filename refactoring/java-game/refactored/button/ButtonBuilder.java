package button;

import java.awt.event.ActionListener;

public class ButtonBuilder {
    private int x;
    private int y;
    private int width;
    private int height;
    private String name;
    private ActionListener actionListener;

    public ButtonBuilder withLetter(String name) {
        this.name = name;
        return this;
    }

    public ButtonBuilder withPosition(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public ButtonBuilder withSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }
    
    public ButtonBuilder setActionListener(ActionListener listener) {
        this.actionListener = listener;
        return this;
    }

    public Button build() {
        return new Button(name, x, y, width, height, actionListener);
    }
}