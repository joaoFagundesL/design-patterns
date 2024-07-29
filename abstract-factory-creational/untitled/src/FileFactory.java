public abstract class FileFactory {
    // business logic here
    public void teste() {
        Document wordDoc = createDocument();
        wordDoc.save();
    }

    public abstract Document createDocument();

    public abstract Page createPage();
}
