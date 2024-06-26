package geometries;

import primitives.BoundingBox;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * The Tube class represents a tube in 3D space, with a radius and an axis ray. It inherits from the RadialGeometry class.
 */
public class Tube extends RadialGeometry {

    /**
     * The axis ray of the Tube object.
     */
    protected final Ray axisRay;

    /**
     * Constructs a new Tube object with the specified radius and axis ray.
     *
     * @param r       The radius of the Tube object.
     * @param axisRay The axis ray of the Tube object.
     */
    public Tube(double r, Ray axisRay) {
        super(r);
        this.axisRay = axisRay;

        Point center = axisRay.getP0();
        double minX = center.getX() - radius;
        double minY = Double.NEGATIVE_INFINITY;
        double minZ = center.getZ() - radius;
        double maxX = center.getX() + radius;
        double maxY = Double.POSITIVE_INFINITY;
        double maxZ = center.getZ() + radius;

        this.boundingBox = new BoundingBox(new Point(minX, minY, minZ), new Point(maxX, maxY, maxZ));
    }

    /**
     * Returns the axis ray of the Tube object.
     *
     * @return The axis ray of the Tube object.
     */
    @SuppressWarnings("unused")
    public Ray getAxisRay() {
        return axisRay;
    }

    @Override
    public Vector getNormal(Point p) {
        // t is the distance from the axis ray's starting point to the point on the axis ray that is the closest to p
        double t = axisRay.getDir().dotProduct(p.subtract(axisRay.getP0()));
        return p.subtract(axisRay.getPoint(t)).normalize(); // otherwise
    }

    /**
     * Returns a string representation of the Tube object.
     *
     * @return A string representation of the Tube object.
     */
    @Override
    public String toString() {
        return "Tube: " +
                "axisRay= " + axisRay +
                ", radius= " + radius;
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }
}