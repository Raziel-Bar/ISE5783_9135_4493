package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * The Sphere class represents a sphere in 3D space. It inherits from the RadialGeometry class.
 */
public class Sphere extends RadialGeometry {

    /**
     * The center point of the sphere.
     */
    private final Point center;

    /**
     * Constructs a new Sphere object with the specified radius and center point.
     *
     * @param r      The radius of the Sphere object.
     * @param center The center point of the Sphere object.
     */
    public Sphere(double r, Point center) {
        super(r);
        this.center = center;
    }

    /**
     * Constructs a new Sphere object with the specified radius and center point.
     *
     * @param r      The radius of the Sphere object.
     * @param center The center point of the Sphere object.
     */
    public Sphere(Point center, double r) {
        super(r);
        this.center = center;
    }

    /**
     * Returns the center point of the Sphere object.
     *
     * @return The center point of the Sphere object.
     */
    @SuppressWarnings("unused")
    public Point getCenter() {
        return center;
    }

    /**
     * Returns the normal vector to the Sphere object at the specified point.
     *
     * @param p The point on the Sphere object.
     * @return The normal vector to the Sphere object at the specified point.
     */
    @Override
    public Vector getNormal(Point p) {
        return p.subtract(center).normalize();
    }

    /**
     * Returns a string representation of the Sphere object.
     *
     * @return A string representation of the Sphere object.
     */
    @Override
    public String toString() {
        return "Sphere: " +
                "center= " + center +
                ", radius=" + radius;
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        if (center.equals(ray.getP0()))
            return List.of(new GeoPoint(this, ray.getPoint(radius)));

        Vector u = center.subtract(ray.getP0());
        double tm = ray.getDir().dotProduct(u);
        double dSquared = u.lengthSquared() - tm * tm;
        double thSquared = radiusSquared - dSquared;
        if (alignZero(thSquared) <= 0) return null;

        double th = Math.sqrt(thSquared);
        double t2 = tm + th;
        if (alignZero(t2) <= 0) return null;

        double t1 = tm - th;
        return alignZero(t1) <= 0 ? List.of(new GeoPoint(this, ray.getPoint(t2))) : List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2)));
    }
}
