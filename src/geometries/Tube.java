package geometries;

import primitives.*;

import static primitives.Util.isZero;

/**
 * The Tube class represents a tube in 3D space, with a radius and an axis ray. It inherits from the RadialGeometry class.
 */
public class Tube extends RadialGeometry {

    /**
     * The axis ray of the Tube object.
     */
    protected final Ray axisRay;

    /**
     * Constructs a new Tube object with the specified radius and axis ray.
     *
     * @param r       The radius of the Tube object.
     * @param axisRay The axis ray of the Tube object.
     */
    public Tube(double r, Ray axisRay) {
        super(r);
        this.axisRay = axisRay;
    }

    /**
     * Returns the axis ray of the Tube object.
     *
     * @return The axis ray of the Tube object.
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * Returns the normal vector to the Tube object at the specified point.
     *
     * @param p The point on the Tube object.
     * @return The normal vector to the Tube object at the specified point.
     */
    @Override
    public Vector getNormal(Point p) {
        double t = axisRay.getDir().dotProduct(p.subtract(axisRay.getP0())); // t is the distance from the axis ray's starting point to the point on the axis ray that is the closest to p
        Point o = isZero(t) ? axisRay.getP0() : axisRay.getP0().add(axisRay.getDir().scale(t)); // in case the closest point on the ray to p is the starting point of the ray
        return p.subtract(o).normalize(); // otherwise
    }

    /**
     * Returns a string representation of the Tube object.
     *
     * @return A string representation of the Tube object.
     */
    @Override
    public String toString() {
        return "Tube: " +
                "axisRay= " + axisRay +
                ", radius= " + radius;
    }
}