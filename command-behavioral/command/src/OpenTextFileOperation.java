public class OpenTextFileOperation implements  TextFileOperation {
    private TextFile textFile;

    public OpenTextFileOperation(TextFile textFile) {
        this.textFile = textFile;
    }

    @Override
    public String execute() {
        return textFile.open();
    }

    @Override
    public String toString() {
        return "OpenTextFileOperation{" +
                "textFile=" + textFile +
                '}';
    }
}
