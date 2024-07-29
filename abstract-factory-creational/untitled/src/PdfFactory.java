public class PdfFactory extends FileFactory {
    @Override
    public Document createDocument() {
        System.out.println("creating pdf document...");
        return new PdfDocument();
    }

    @Override
    public Page createPage() {
        System.out.println("creating pdf page...");
        return new PdfPage();
    }
}
