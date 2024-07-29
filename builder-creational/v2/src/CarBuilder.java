public class CarBuilder implements IBuilder {
    private String color;
    private Integer doors;
    private Double weight;

    @Override
    public CarBuilder color(String color) {
        this.color = color;
        return this;
    }

    @Override
    public CarBuilder doors(Integer doors) {
        this.doors = doors;
        return this;
    }

    @Override
    public CarBuilder weight(Double weight) {
        this.weight = weight;
        return this;
    }

    @Override
    public Car build() {
        return new Car(weight, doors, color);
    }
}
