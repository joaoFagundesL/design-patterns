public class Main {
    public static void main(String[] args) {
        SquareHole squareHole = new SquareHole(5);

        Square square = new Square(5);
        System.out.println(squareHole.canFit(square));            // true

        Circle circle = new Circle(5);
        CircleToSquareAdapter circleAdapter = new CircleToSquareAdapter(circle);
        System.out.println(squareHole.canFit(circleAdapter));     // false

    }
}