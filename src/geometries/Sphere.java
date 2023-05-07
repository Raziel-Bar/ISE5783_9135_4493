package geometries;

import primitives.*;

import java.util.List;

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
     * @param r       The radius of the Sphere object.
     * @param _center The center point of the Sphere object.
     */
    public Sphere(double r, Point _center) {
        super(r);
        this.center = _center;
    }

    /**
     * Returns the center point of the Sphere object.
     *
     * @return The center point of the Sphere object.
     */
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
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
