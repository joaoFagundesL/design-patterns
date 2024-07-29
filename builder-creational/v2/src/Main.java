public class Main {
    public static void main(String[] args) {
        Director director = new Director();
        CarBuilder builder = new CarBuilder();

        director.buildBugatti(builder);
        Car car = builder.build();

        System.out.println(car);

    }
}