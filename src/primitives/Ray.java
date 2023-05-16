package primitives;

import java.util.Objects;

import static primitives.Util.*;

/**
 * The Ray class represents a ray in 3D space.
 */
public class Ray {

    /**
     * The starting point of the ray.
     */
    final private Point p0;

    /**
     * The direction vector of the ray.
     */
    final private Vector dir;

    /**
     * Constructs a new Ray object with the given direction and starting point.
     *
     * @param v The direction vector of the ray.
     * @param p The starting point of the ray.
     */
    public Ray(Vector v, Point p) {
        p0 = p;
        dir = v.normalize();
    }

    // second constructor in case we want to change the order of the parameters
    public Ray(Point p, Vector v) {
        this(v, p);
    }

    /**
     * Returns the starting point of the ray.
     *
     * @return The starting point of the ray.
     */
    public Point getP0() {
        return p0;
    }

    /**
     * calculates a point on the ray at a given distance from the head of the ray
     * @param t the distance
     * @return the point
     */
    public Point getPoint(double t) {
        return isZero(t) ? p0 : p0.add(dir.scale(t));
    }

    /**
     * Returns the direction vector of the ray.
     *
     * @return The direction vector of the ray.
     */
    public Vector getDir() {
        return dir;
    }


    /**
     * Returns whether the given object is equal to this Ray object.
     *
     * @param o The object to compare to this Ray object.
     * @return Whether the given object is equal to this Ray object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof Ray ray
                && p0.equals(ray.p0)
                && dir.equals(ray.dir);
    }

    /**
     * Returns the hash code value for this Ray object.
     *
     * @return The hash code value for this Ray object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    /**
     * Returns a string representation of this Ray object.
     *
     * @return A string representation of this Ray object.
     */
    @Override
    public String toString() {
        return "Ray: " +
                "p0= " + p0 +
                ", dir= " + dir;
    }
}
