package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.isZero;

/**
 * The Cylinder class represents a cylinder in 3D space, with a radius, axis ray and height. It inherits from the Tube class.
 */
public class Cylinder extends Tube {

    /**
     * The height of the Cylinder object.
     */
    private final double height;

    /**
     * Constructs a new Cylinder object with the specified radius, axis ray and height.
     *
     * @param r       The radius of the Cylinder object.
     * @param axisRay The axis ray of the Cylinder object.
     * @param height  The height of the Cylinder object.
     */
    public Cylinder(double r, Ray axisRay, double height) {
        super(r, axisRay);
        this.height = height;
    }

    /**
     * Returns the normal vector to the Cylinder object at the specified point.
     *
     * @param p The point on the Cylinder object.
     * @return The normal vector to the Cylinder object at the specified point.
     */
    @Override
    public Vector getNormal(Point p) {
        //refactor Point p0 =
        if (p.equals(axisRay.getP0())) // in case the point is the starting point of the axis ray
            return axisRay.getDir().scale(-1);
        double t = axisRay.getDir().dotProduct(p.subtract(axisRay.getP0())); // t is the distance from the axis ray's starting point to the point on the axis ray that is the closest to p
        return isZero(t) ? axisRay.getDir().scale(-1) : // in case the closest point on the ray to p is the starting point of the ray
                isZero(t - height) ? axisRay.getDir() : // in case the closest point on the ray to p is the ending point of the ray
                super.getNormal(p); // otherwise
    }

    /**
     * Returns a string representation of the Cylinder object.
     *
     * @return A string representation of the Cylinder object.
     */
    @Override
    public String toString() {
        return "Cylinder: " +
                "height= " + height +
                ", axisRay= " + axisRay +
                ", radius= " + radius;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
