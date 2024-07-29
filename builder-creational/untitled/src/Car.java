public class Car extends Vehicle {
    private String color;
    private Integer doors;
    private Double weight;

    public String getColor() {
        return color;
    }

    public Integer getDoors() {
        return doors;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    public Car(String example, String color, Double weight, Integer doors) {
        super(example);
        this.color = color;
        this.weight = weight;
        this.doors = doors;
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", doors=" + doors +
                ", weight=" + weight +
                ", example=" + super.toString() +
                '}';
    }
}
