public class Main {
    public static void main(String[] args) {
        CarBuilder builder = new CarBuilder();

        Vehicle car = builder.color("Red")
                .doors(4)
                .weight(200.50)
                .example("Example")
                .build();

        System.out.println(car);

    }
}