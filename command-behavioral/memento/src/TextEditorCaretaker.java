import java.util.Stack;

public class TextEditorCaretaker {
    private final Stack<TextMemento> mementoStack = new Stack<>();

    public void save(TextMemento memento) {
        mementoStack.push(memento);
    }

    public TextMemento undo() {
        if (!mementoStack.isEmpty()) {
            return mementoStack.pop();
        }
        return null;
    }
}
