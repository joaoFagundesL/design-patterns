public class CarBuilder extends VehicleBuilder {
    private String color;
    private Integer doors;
    private Double weight;
    private String example;

    public CarBuilder color(String color) {
        this.color = color;
        return this;
    }

    public CarBuilder doors(Integer doors) {
        this.doors = doors;
        return this;
    }

    public CarBuilder weight(Double weight) {
        this.weight = weight;
        return this;
    }

    public CarBuilder example(String example) {
        this.example = example;
        return this;
    }

    public Car build() {
        return new Car(example, color, weight, doors);
    }
}
