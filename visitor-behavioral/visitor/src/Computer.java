public class Computer implements Item {
    private Double price;

    public Computer(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
