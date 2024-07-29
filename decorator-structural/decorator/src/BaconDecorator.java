public class BaconDecorator extends PizzaDecorator {
    public BaconDecorator(Pizza pizza) {
        super(pizza);
    }

    public String prepare() {
        return pizza.prepare() + addBacon();
    }

    public String addBacon() {
        return "Bacon added\n";
    }
}
