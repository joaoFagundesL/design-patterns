public class Car implements CarPrototype {
    private String brand;
    private String color;
    private String model;
    private int topSpeed;

    public Car(String brand, String color, String model, int topSpeed) {
        this.brand = brand;
        this.color = color;
        this.model = model;
        this.topSpeed = topSpeed;
    }

    public Car(Car car) {
        this.brand = car.brand;
        this.color = car.color;
        this.model = car.model;
        this.topSpeed = car.topSpeed;
    }

    @Override
    public Car clone() {
        return new Car(this);
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", model='" + model + '\'' +
                ", topSpeed=" + topSpeed +
                '}';
    }
}
