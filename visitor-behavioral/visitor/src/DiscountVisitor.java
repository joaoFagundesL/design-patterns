public class DiscountVisitor implements Visitor {
    private double totalDiscount;

    @Override
    public void visit(Book book) {
        double discount = book.getPrice() * 0.10;
        System.out.println("Book discount: $" + discount);
        totalDiscount += discount;
    }

    @Override
    public void visit(Computer computer) {
        double discount = computer.getPrice() * 0.05;
        System.out.println("Computer discount: $" + discount);
        totalDiscount += discount;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }
}
