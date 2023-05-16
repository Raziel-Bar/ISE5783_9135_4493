package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * The Intersectable interface represents an intersectable object in 3D space.
 */
public interface Intersectable {
    /**
     * Returns a list of intersection points of the specified ray with the Intersectable object.
     *
     * @param ray The ray to intersect with the Intersectable object.
     * @return A list of intersection points of the specified ray with the Intersectable object.
     */
    List<Point> findIntersections(Ray ray);
}
