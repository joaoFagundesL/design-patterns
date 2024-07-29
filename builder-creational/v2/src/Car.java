public class Car {
    private String color;
    private Integer doors;

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }

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

    Car(Double weight, Integer doors, String color) {
        this.weight = weight;
        this.doors = doors;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", doors=" + doors +
                ", weight=" + weight +
                '}';
    }
}
