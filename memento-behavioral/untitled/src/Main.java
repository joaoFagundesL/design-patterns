import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextEditor textEditor = new TextEditor();
        TextEditorCaretaker caretaker = new TextEditorCaretaker();

        while (true) {
            System.out.println("Current Text: " + textEditor.getText());
            System.out.println("Options: ");
            System.out.println("1. Edit Text");
            System.out.println("2. Undo");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter new text: ");
                    String newText = scanner.nextLine();
                    caretaker.save(textEditor.saveToMemento());
                    textEditor.setText(newText);
                    break;
                case 2:
                    TextMemento memento = caretaker.undo();
                    if (memento != null) {
                        textEditor.restoreFromMemento(memento);
                    } else {
                        System.out.println("No states to undo.");
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
