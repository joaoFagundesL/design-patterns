public class Tea extends Beverage {
    @Override
    protected void brew() {
        System.out.println("Brewing Tea");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Tea condiments");
    }
}
