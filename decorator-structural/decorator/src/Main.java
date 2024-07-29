public class Main {
    public static void main(String[] args) {
        Pizza pizza = new CheeseDecorator(new BaconDecorator(new BaconDecorator(new BasePizza())));
        System.out.println(pizza.prepare());
    }
}