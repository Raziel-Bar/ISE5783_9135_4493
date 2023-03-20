package geometries;

import primitives.*;

/**
 * The Geometry interface represents a geometric object that can be intersected with a Ray and has a normal vector.
 */
public interface Geometry {

    /**
     * Returns the normal vector to the geometry object at the specified point.
     *
     * @param p The point on the geometry object.
     * @return The normal vector to the geometry object at the specified point.
     */
    public Vector getNormal(Point p);
}