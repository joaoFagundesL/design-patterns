public class Main {
    public static void main(String[] args) {
        Item[] items = {
                new Book(20.0),
                new Computer(1000.0),
                new Book(15.0)
        };

        DiscountVisitor discountVisitor = new DiscountVisitor();

        for (Item item : items) {
            item.accept(discountVisitor);
        }

        System.out.println("Total discount: $" + discountVisitor.getTotalDiscount());
    }
}