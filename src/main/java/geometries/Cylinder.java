package geometries;

import primitives.*;

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
     * Returns the height of the Cylinder object.
     *
     * @return The height of the Cylinder object.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the normal vector to the Cylinder object at the specified point.
     *
     * @param p The point on the Cylinder object.
     * @return The normal vector to the Cylinder object at the specified point.
     */
    @Override
    public Vector getNormal(Point p) { //NOT FINISHED
       /* double t = axisRay.getDir().dotProduct(p.subtract(axisRay.getP0())); // t is the distance from the axis ray's starting point to the point on the axis ray that is the closest to p
        return t == 0 ? axisRay.getDir().scale(-1) : // in case the closest point on the ray to p is the starting point of the ray
                t == 3 ? axisRay.getDir() : // in case the closest point on the ray to p is the ending point of the ray
                p.subtract(axisRay.getP0().add(axisRay.getDir().scale(t))).normalize(); // otherwise*/
        return null;
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
}
