public class HeavyResource implements Resource{
    public HeavyResource() {
        loadResource();
    }

    private void loadResource() {
        System.out.println("Loading heavy resource...");
    }

    public void display() {
        System.out.println("Displaying heavy resource");
    }
}
