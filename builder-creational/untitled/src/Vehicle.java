public class Vehicle {
    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    private String example;

    public Vehicle(String example) {
        this.example = example;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "example='" + example + '\'' +
                '}';
    }
}
