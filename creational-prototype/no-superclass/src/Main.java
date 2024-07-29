public class Main {
    public static void main(String[] args) {
        Car car = new Car("A", "A", "A", 0);
        Car carCopy = car.clone();

        System.out.println("Original car: " + car);
        System.out.println("Original car copy: " + carCopy);
    }
}