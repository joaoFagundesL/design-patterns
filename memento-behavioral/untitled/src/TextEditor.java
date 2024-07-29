public class TextEditor {
    private String text = "";

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public TextMemento saveToMemento() {
        return new TextMemento(text);
    }

    public void restoreFromMemento(TextMemento memento) {
        text = memento.getText();
    }
}
