package geometries;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

/**
 * The Geometries class represents a list of geometries.
 * It implements the Intersectable interface.
 */
public class Geometries implements Intersectable{
    /**
     * A list of geometries.
     */
    private List<Intersectable> _geometries;

    /**
     * empty constructor
     */
    public Geometries(){
        _geometries = new LinkedList<Intersectable>();
    }


    /**
     * constructor
     * @param geometries
     */
    public Geometries(Intersectable... geometries){
        _geometries = new LinkedList<Intersectable>();
        add(geometries);
    }

    /**
     * add geometries to the list
     * @param geometries
     */
    public void add(Intersectable... geometries){
        for (Intersectable geo : geometries){
            _geometries.add(geo);
        }
    }

    /**
     * find intersections
     * @param ray
     * @return list of intersections points with the ray and the geometries
     */
    @Override
    public List<Point> findIntersections(Ray ray){
        List<Point> intersections = null;
        for (Intersectable geo : _geometries){
            List<Point> tempIntersections = geo.findIntersections(ray);
            if (tempIntersections != null) {
                if (intersections == null) {
                    intersections = new LinkedList<Point>();
                }
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;
    }
}
