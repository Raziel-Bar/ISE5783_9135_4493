package geometries;

import primitives.*;

import java.util.List;

/**
 * The Plane class represents a plane in 3D space, defined by a point and a normal vector.
 */
public class Plane extends Geometry {
    /**
     * The base point of the Plane object.
     */
    private final Point q0;
    /**
     * The normal vector to the Plane object.
     */
    private final Vector normal;

    /**
     * Constructs a new Plane object with the specified base point and normal vector.
     *
     * @param q0     The base point of the Plane object.
     * @param normal The normal vector to the Plane object.
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    /**
     * Constructs a new Plane object with the specified three points.
     *
     * @param q1 The first point on the Plane object.
     * @param q2 The second point on the Plane object.
     * @param q3 The third point on the Plane object.
     */
    public Plane(Point q1, Point q2, Point q3) {
        q0 = q1;
        normal = q1.subtract(q2).crossProduct(q1.subtract(q3)).normalize();

        double minX = Double.NEGATIVE_INFINITY;
        double minY = Double.NEGATIVE_INFINITY;
        double minZ = Double.NEGATIVE_INFINITY;
        double maxX = Double.POSITIVE_INFINITY;
        double maxY = Double.POSITIVE_INFINITY;
        double maxZ = Double.POSITIVE_INFINITY;

        this.boundingBox = new BoundingBox(new Point(minX, minY, minZ), new Point(maxX, maxY, maxZ));
    }

    /**
     * Returns the normal vector of the Plane object.
     *
     * @return The normal vector of the Plane object.
     */
    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point p) {
        return normal;
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point p0 = ray.getP0();
        double denominator = normal.dotProduct(ray.getDir());
        if (p0.equals(q0) || Util.isZero(denominator)) return null;
        double t = normal.dotProduct(q0.subtract(p0)) / denominator;
        return Util.alignZero(t) <= 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t)));
    }
}