public class Car extends Vehicle {
    private final int topSpeed;

    public Car(String brand, String model, String color, int topSpeed) {
        super(brand, model, color);
        this.topSpeed = topSpeed;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    private Car(Car car) {
        super(car);
        this.topSpeed = car.topSpeed;
    }

    @Override
    public String toString() {
        return "Car{" +
                "topSpeed=" + topSpeed +
                ", " + super.toString() +
                '}';
    }

    @Override
    public Car clone() {
        return new Car(this);
    }
}
