// Proxy
public class ResourceProxy implements Resource {
    private HeavyResource heavyResource;

    @Override
    public void display() {
        if (heavyResource == null) {
            System.out.println("Creating a new heavy resource");
            heavyResource = new HeavyResource();
        }
        heavyResource.display();
    }
}
