package exercise;

import java.util.Locale;

// BEGIN
public class Flat implements Home {
    private double area;
    private double balconyArea;
    private int floor;

    public Flat(int area, int balconyArea, int floor) {
        this.area = Double.valueOf(area);
        this.balconyArea = Double.valueOf(balconyArea);
        this.floor = floor;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "Квартира площадью %.1f метров на %d этаже", getArea(), floor);
    }

    public double getArea() {
        return area + balconyArea;
    }

    public int compareTo(Home home) {
        return Double.compare(this.getArea(), home.getArea());
    }
}

// END
