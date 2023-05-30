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
    private final List<Intersectable> geometries = new LinkedList<>();

    /**
     * Constructs an empty Geometries object.
     */
    public Geometries() {
    }


    /**
     * Constructs a Geometries object with the specified geometries.
     *
     * @param geometries The geometries to add to the list.
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * Adds geometries to the list.
     *
     * @param geometries The geometries to add.
     */
    public void add(Intersectable... geometries) {
        this.geometries.addAll(List.of(geometries));
    }

    /**
     * Finds intersections between the given ray and the geometries in the list.
     *
     * @param ray The ray to intersect with the geometries.
     * @return A list of intersection points with the ray and the geometries.
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = null;
        for (Intersectable geo : geometries) {
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
