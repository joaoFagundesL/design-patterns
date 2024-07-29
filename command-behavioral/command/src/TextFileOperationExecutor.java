import java.util.ArrayList;
import java.util.List;

public class TextFileOperationExecutor {
    private final List<TextFileOperation> textFileOperations
            = new ArrayList<>();

    public String executeOperation(TextFileOperation textFileOperation) {
        textFileOperations.add(textFileOperation);
        return textFileOperation.execute();
    }

    public void printOperations() {
        System.out.println("List of Operations:");
        for (TextFileOperation operation : textFileOperations) {
            System.out.println(operation);
        }
    }
}
