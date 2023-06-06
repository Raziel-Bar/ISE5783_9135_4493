package primitives;

import java.util.List;
import java.util.Objects;
import geometries.Intersectable.GeoPoint;
import static primitives.Util.isZero;

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

    /**
     * Constructs a new Ray object with the given direction and starting point.
     *
     * @param p The starting point of the ray.
     * @param v The direction vector of the ray.
     */
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
     *
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

    /**
     * Finds the closest point to the starting point of the ray from the given list of points.
     *
     * @param points The list of points to find the closest point to the starting point of the ray from.
     * @return The closest point to the starting point of the ray from the given list of points.
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * Finds the closest GeoPoint to the starting point of the ray from the given list of GeoPoints.
     *
     * @param geoPoints The list of GeoPoints to find the closest GeoPoint to the starting point of the ray from.
     * @return The closest GeoPoint to the starting point of the ray from the given list of GeoPoints.
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPoints) {
        if (geoPoints == null)
            return null;

        GeoPoint closestPoint = null;
        double closestDistance = Double.POSITIVE_INFINITY;
        for (GeoPoint geoPoint : geoPoints) {
            double distance = geoPoint.point.distance(p0);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestPoint = geoPoint;
            }
        }
        return closestPoint;
    }
}
