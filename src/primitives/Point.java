package primitives;

import java.lang.Math;
import java.util.Objects;

public class Point {

    final Double3 xyz;

    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    Point(Double3 p) {
        this(p.d1, p.d2, p.d3);
    }

    public Point add(Vector v) {
        return new Point(xyz.add(v.xyz));
    }

    public Vector subtract(Point p) {
        return new Vector(xyz.subtract(p.xyz));
    }

    public double distanceSquared(Point p) {
        double delta1 = xyz.d1 - p.xyz.d1;
        double delta2 = xyz.d2 - p.xyz.d2;
        double delta3 = xyz.d3 - p.xyz.d3;
        return delta1 * delta1 + delta2 * delta2 + delta3 * delta3;
    }

    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;
        return xyz.equals(point.xyz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    @Override
    public String toString() {
        return "Point: " + xyz;
    }
}
