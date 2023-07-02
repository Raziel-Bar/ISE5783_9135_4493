package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The Geometries class represents a list of geometries.
 * It implements the Intersectable interface.
 */
public class Geometries extends Intersectable {
    /**
     * A list of geometries.
     */
    private List<Intersectable> geometries = new LinkedList<>();

    /**
     * The bounding box of the geometries.
     */
    protected BoundingBox boundingBox;

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
     * builds the bounding box that contains all the geometries.
     *
     * @return this
     */
    public Geometries BuildBoundingBox() {
        if (geometries.isEmpty()) {
            return null;
        }
        this.boundingBox = new BoundingBox(geometries.get(0).getBoundingBox().getMinPoint(), geometries.get(0).getBoundingBox().getMaxPoint());
        for (Intersectable geo : geometries) {
            this.boundingBox.expand(geo.getBoundingBox());
        }
        return this;
    }

    /**
     * builds the hierarchical tree of the geometries.
     *
     * @return this
     */
    public Geometries buildTree() {
        if (geometries.isEmpty()) {
            return null;
        }
        BuildBoundingBox();
        return buildTree(this, 0);
    }

    /**
     * builds the hierarchical tree of the geometries.
     *
     * @param geometries The geometries to add to the list.
     * @param level      The level of the tree.
     * @return this
     */
    private Geometries buildTree(Geometries geometries, int level) {

        if (level == 7 || geometries == null) {
            return this;
        }

        double midX = geometries.getBoundingBox().getMidPointX();

        Geometries leftGeometries = new Geometries();
        Geometries rightGeometries = new Geometries();

        for (Intersectable geometry : geometries.geometries) {
            if (geometry.getBoundingBox().getMidPointX() <= midX) {
                leftGeometries.add(geometry);
            } else {
                rightGeometries.add(geometry);
            }
        }

        this.geometries = new ArrayList<>();
        this.geometries.add(leftGeometries.buildTree(leftGeometries.BuildBoundingBox(), level + 1));
        this.geometries.add(rightGeometries.buildTree(rightGeometries.BuildBoundingBox(), level + 1));

        return this;
    }

    /**
     * Finds intersections between the given ray and the geometries in the list.
     *
     * @param ray The ray to intersect with the geometries.
     * @return A list of intersection points with the ray and the geometries.
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        if (isBvH) {
            return findGeoIntersectionsHelperBvH(ray);
        }
        List<GeoPoint> intersections = null;
        for (Intersectable geo : geometries) {
            List<GeoPoint> tempIntersections = geo.findGeoIntersections(ray);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new LinkedList<>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;
    }

    /**
     * acts like findGeoIntersectionsHelper but with the hierarchical tree of bounding boxes.
     *
     * @param ray The ray to intersect with the geometries.
     * @return A list of intersection points with the ray and the geometries.
     */
    public List<GeoPoint> findGeoIntersectionsHelperBvH(Ray ray) {
        List<GeoPoint> intersections = null;
        for (Intersectable geo : geometries) {
            if (geo.getBoundingBox() != null && !geo.getBoundingBox().intersectsWith(ray)) {
                continue;
            }
            List<GeoPoint> tempIntersections = geo.findGeoIntersections(ray);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new LinkedList<>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return boundingBox;
    }
}
