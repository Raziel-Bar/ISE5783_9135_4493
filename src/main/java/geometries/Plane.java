package geometries;

import primitives.*;

/**
 * The Plane class represents a plane in 3D space, defined by a point and a normal vector.
 */
public class Plane implements Geometry {
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
    }

    /**
     * Returns the normal vector of the Plane object.
     *
     * @return The normal vector of the Plane object.
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * Returns the normal vector to the Plane object at the specified point.
     *
     * @param p The point on the Plane object.
     * @return The normal vector to the Plane object at the specified point.
     */
    @Override
    public Vector getNormal(Point p) {
        return getNormal();
    }
}