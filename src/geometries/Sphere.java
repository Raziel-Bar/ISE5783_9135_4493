package geometries;

import primitives.*;

public class Sphere extends RadialGeometry {

    private final Point center;

    public Sphere(double r, Point _center) {
        super(r);
        this.center = _center;
    }

    public Point getCenter() {
        return center;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }

    @Override
    public String toString() {
        return "Sphere: " +
                "center= " + center +
                ", radius=" + radius;
    }
}
