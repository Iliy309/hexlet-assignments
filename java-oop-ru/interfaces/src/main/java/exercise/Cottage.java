package exercise;

import java.util.Locale;

// BEGIN

public class Cottage implements Home {

    private double area;
    private int floorCount;

    public Cottage(int area, int floorCount) {
        this.area = Double.valueOf(area);
        this.floorCount = floorCount;
    }

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%d этажный коттедж площадью %.1f метров", floorCount, area);
    }

    public int compareTo(Home home) {
        return Double.compare(this.getArea(), home.getArea());
    }

    public double getArea() {
        return area;
    }
}
// END
