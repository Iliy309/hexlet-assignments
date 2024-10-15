package exercise;

// BEGIN
public class Circle {

    private Point centerPoint;
    private int radius;

    public Circle(Point centerPoint, int radius) {
        this.centerPoint = centerPoint;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if(getRadius() < 0) {
            throw new NegativeRadiusException();
        };
        return Math.PI * Math.pow(getRadius(), 2);
    }
}
// END
