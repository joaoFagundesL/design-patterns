public interface IBuilder {
    public CarBuilder color(String color);
    public Car build();
    public CarBuilder weight(Double weight);
    public CarBuilder doors(Integer doors);
}
