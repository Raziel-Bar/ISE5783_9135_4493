package geometries;

import primitives.BoundingBox;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * The Intersectable interface represents an intersectable object in 3D space.
 */
public abstract class Intersectable {

    protected boolean isBvH = true;

    /**
     * Returns a list of intersection points of the specified ray with the Intersectable object.
     *
     * @param ray The ray to intersect with the Intersectable object.
     * @return A list of intersection points of the specified ray with the Intersectable object.
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * A class that represents a point on a geometry object and the geometry object itself.
     */
    public static class GeoPoint {
        /**
         * The geometry object.
         */
        public Geometry geometry;

        /**
         * The point on the geometry object.
         */
        public Point point;

        /**
         * Constructs a new GeoPoint object with the specified geometry object and point.
         *
         * @param geometry The geometry object.
         * @param point    The point on the geometry object.
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        /**
         * Checks if the specified object is equal to this GeoPoint object.
         *
         * @param o The object to compare this GeoPoint object to.
         * @return true if the specified object is equal to this GeoPoint object, false otherwise.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            return (o instanceof GeoPoint p)
                    && geometry.equals(p.geometry)
                    && point.equals(p.point);
        }

        /**
         * @return The string representation of this GeoPoint object.
         */
        @Override
        public String toString() {
            return "Geometry: " + geometry +
                    ", Point: " + point +
                    '}';
        }
    }

    /**
     * Returns a list of intersection GeoPoints of the specified ray with the Intersectable object.
     *
     * @param ray The ray to intersect with the Intersectable object.
     * @return A list of intersection GeoPoints of the specified ray with the Intersectable object.
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) { return findGeoIntersectionsHelper(ray); }

    /**
     * Returns a list of intersection GeoPoints of the specified ray with the Intersectable object.
     *
     * @param ray The ray to intersect with the Intersectable object.
     * @return A list of intersection GeoPoints of the specified ray with the Intersectable object.
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    /**
     * Returns the bounding box of the Intersectable object.
     *
     * @return The bounding box of the Intersectable object.
     */
    public abstract BoundingBox getBoundingBox();
}