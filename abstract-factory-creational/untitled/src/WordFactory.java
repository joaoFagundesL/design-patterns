public class WordFactory extends FileFactory{
    @Override
    public Document createDocument() {
        return new WordDocument();
    }

    @Override
    public Page createPage() {
        return new WordPage();
    }
}
