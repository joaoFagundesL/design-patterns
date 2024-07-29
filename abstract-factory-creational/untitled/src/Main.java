public class Main {
    public static void main(String[] args) {
        FileFactory fileFactoryPdf = new PdfFactory();
        Page pdfPage = fileFactoryPdf.createPage();
        Document pdfDocument = fileFactoryPdf.createDocument();

        FileFactory fileFactoryWord = new WordFactory();
        Page wordPage = fileFactoryWord.createPage();
        Document wordDocument = fileFactoryWord.createDocument();

        fileFactoryWord.teste();
    }
}