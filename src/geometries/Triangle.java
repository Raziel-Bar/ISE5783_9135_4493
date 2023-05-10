package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import primitives.Util;

import java.util.List;

/**
 * The Triangle class represents a triangle in 3D space, defined by three points.
 */
public class Triangle extends Polygon {
    /**
     * constructs a new triangle using the given vertices
     *
     * @param p1 first vertex
     * @param p2 second vertex
     * @param p3 third vertex
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> result = plane.findIntersections(ray);
        if (result == null) return null;
        Point p = ray.getP0();
        Vector v1 = vertices.get(0).subtract(p);
        Vector v2 = vertices.get(1).subtract(p);
        Vector v3 = vertices.get(2).subtract(p);
        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();
        Vector v = ray.getDir();
        if (Util.alignZero(v.dotProduct(n1)) > 0 && Util.alignZero(v.dotProduct(n2)) > 0 && Util.alignZero(v.dotProduct(n3)) > 0
                || Util.alignZero(v.dotProduct(n1)) < 0 && Util.alignZero(v.dotProduct(n2)) < 0 && Util.alignZero(v.dotProduct(n3)) < 0)
                return result;
        return null;
    }
}
