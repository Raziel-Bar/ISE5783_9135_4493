package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * The RadialGeometry class is an abstract class that represents a geometry object with a radius property.
 * <p>
 * It implements the Geometry interface.
 */
public abstract class RadialGeometry implements Geometry {

    /**
     * The radius of the RadialGeometry object.
     */
    protected final double radius;

    /**
     * Constructs a new RadialGeometry object with the specified radius.
     *
     * @param r The radius of the RadialGeometry object.
     */
    public RadialGeometry(double r) {
        radius = r;
    }

    /**
     * Returns the radius of the RadialGeometry object.
     *
     * @return The radius of the RadialGeometry object.
     */
    public double getRadius() {
        return radius;
    }
}