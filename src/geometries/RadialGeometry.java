package geometries;

import primitives.BoundingBox;

/**
 * The RadialGeometry class is an abstract class that represents a geometry object with a radius property.
 * <p>
 * It implements the Geometry interface.
 */
public abstract class RadialGeometry extends Geometry {

    /**
     * The radius of the RadialGeometry object.
     */
    protected final double radius;
    /**
     * The squared radius of the RadialGeometry object.
     */
    protected final double radiusSquared;

    /**
     * The bounding box of the geometry.
     */
    protected BoundingBox boundingBox;

    /**
     * Constructs a new RadialGeometry object with the specified radius.
     *
     * @param r The radius of the RadialGeometry object.
     */
    public RadialGeometry(double r) {
        radius = r;
        radiusSquared = r * r;
    }

    /**
     * Returns the radius of the RadialGeometry object.
     *
     * @return The radius of the RadialGeometry object.
     */
    @SuppressWarnings("unused")
    public double getRadius() {
        return radius;
    }
}