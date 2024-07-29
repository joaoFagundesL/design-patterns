public class CircleToSquareAdapter extends Square {
    private Circle circle;

    public CircleToSquareAdapter(Circle circle) {
        this.circle = circle;
    }

    @Override
    public double getSideLength() {
        return 2 * this.circle.getRadius();
    }
}