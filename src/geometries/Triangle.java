package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

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
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> result = plane.findGeoIntersections(ray);
        if (result == null) return null;

        Point p = ray.getP0();
        Vector v = ray.getDir();
        Vector v1 = vertices.get(0).subtract(p);
        Vector v2 = vertices.get(1).subtract(p);
        Vector n1 = v1.crossProduct(v2).normalize();
        double sign1 = alignZero(v.dotProduct(n1));
        if (sign1 == 0) return null;

        Vector v3 = vertices.get(2).subtract(p);
        Vector n2 = v2.crossProduct(v3).normalize();
        double sign2 = alignZero(v.dotProduct(n2));
        if (sign1 * sign2 <= 0) return null;

        Vector n3 = v3.crossProduct(v1).normalize();
        double sign3 = alignZero(v.dotProduct(n3));
        if (sign1 * sign3 <= 0) return null;

        for (GeoPoint geoPoint : result) {
            geoPoint.geometry = this;
        }
        return result;
    }
}
