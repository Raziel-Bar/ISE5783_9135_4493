package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * The Geometries class represents a list of geometries.
 * It implements the Intersectable interface.
 */
public class Geometries implements Intersectable {
    /**
     * A list of geometries.
     */
    private final List<Intersectable> _geometries = new LinkedList<>();

    /**
     * empty constructor
     */
    public Geometries() {
    }


    /**
     * constructor
     *
     * @param geometries
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * add geometries to the list
     *
     * @param geometries
     */
    public void add(Intersectable... geometries) {
        _geometries.addAll(List.of(geometries));
    }

    /**
     * find intersections
     *
     * @param ray
     * @return list of intersections points with the ray and the geometries
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = null;
        for (Intersectable geo : _geometries) {
            List<Point> tempIntersections = geo.findIntersections(ray);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new LinkedList<>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;
    }
}
