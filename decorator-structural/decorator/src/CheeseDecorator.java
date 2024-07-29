public class CheeseDecorator extends PizzaDecorator {
    public CheeseDecorator(Pizza pizza) {
        super(pizza);
    }

    public String prepare() {
        return pizza.prepare() + addCheese();
    }

    public String addCheese() {
        return "Cheese added\n";
    }
}
