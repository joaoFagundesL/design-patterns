public abstract class Beverage {
    // methods that are not common
    protected abstract void brew();
    protected abstract void addCondiments();

    // way of doing these is the same it doesn't matter the beverage
    private void boilWater() {
        System.out.println("Boiling water");
    }

    private void pourInCup() {
        System.out.println("Pouring into cup");
    }

    // template of how it should be done
    public final void prepareBeverage() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }
}
