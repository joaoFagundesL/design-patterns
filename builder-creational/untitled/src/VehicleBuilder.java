public class VehicleBuilder {
    private String example;

    public VehicleBuilder example(String example) {
        this.example = example;
        return this;
    }

    public Vehicle build() {
        return new Vehicle(example);
    }
}
