public class Coffee extends Beverage {
    @Override
    protected void brew() {
        System.out.println("Coffee is brewing");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Coffee condiments...");
    }
}
