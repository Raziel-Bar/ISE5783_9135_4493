package primitives;

import java.lang.Math;
import java.util.Objects;

/**
 * The Point class represents a point in 3D space with x, y, and z coordinates.
 */
public class Point {

    final Double3 xyz;

    /**
     * Returns the x coordinate of the point.
     *
     * @return The x coordinate of the point.
     */
    public double getX() {
        return xyz.d1;
    }

    /**
     * Returns the y coordinate of the point.
     *
     * @return The y coordinate of the point.
     */
    public double getY() { return xyz.d2; }

    /**
     * Returns the z coordinate of the point.
     *
     * @return The z coordinate of the point.
     */
    public double getZ() { return xyz.d3; }


    /**
     * Constructs a new Point object with the specified x, y, and z coordinates.
     *
     * @param x The x coordinate of the point.
     * @param y The y coordinate of the point.
     * @param z The z coordinate of the point.
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * Constructs a new Point object from a Double3 object.
     *
     * @param p The Double3 object to construct the point from.
     */
    Point(Double3 p) {
        this(p.d1, p.d2, p.d3);
    }

    /**
     * Returns a new Point object that is the result of adding the specified vector to this point.
     *
     * @param v The vector to add to this point.
     * @return The resulting Point object.
     */
    public Point add(Vector v) {
        return new Point(xyz.add(v.xyz));
    }

    /**
     * Returns the vector that results from subtracting the specified point from this point.
     *
     * @param p The point to subtract from this point.
     * @return The resulting Vector object.
     */
    public Vector subtract(Point p) {
        return new Vector(xyz.subtract(p.xyz));
    }

    /**
     * Returns the square of the distance between this point and the specified point.
     *
     * @param p The point to calculate the distance to.
     * @return The square of the distance between this point and the specified point.
     */
    public double distanceSquared(Point p) {
        double delta1 = xyz.d1 - p.xyz.d1;
        double delta2 = xyz.d2 - p.xyz.d2;
        double delta3 = xyz.d3 - p.xyz.d3;
        return delta1 * delta1 + delta2 * delta2 + delta3 * delta3;
    }

    /**
     * Returns the distance between this point and the specified point.
     *
     * @param p The point to calculate the distance to.
     * @return The distance between this point and the specified point.
     */
    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return (o instanceof Point point)
                && xyz.equals(point.xyz);
    }

    @Override
    public int hashCode() {
        return xyz.hashCode();
    }

    @Override
    public String toString() {
        return "Point: " + xyz;
    }
}
