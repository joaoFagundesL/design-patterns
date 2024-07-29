public class TextFile {
    private String name;

    public TextFile(String name) {
        this.name = name;
    }

    public String open() {
        return "Opening file " + name;
    }

    public String save() {
        return "Saving file " + name;
    }

    @Override
    public String toString() {
        return "TextFile{" +
                "name='" + name + '\'' +
                '}';
    }
}
