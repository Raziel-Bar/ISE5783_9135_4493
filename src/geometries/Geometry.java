package geometries;

import primitives.*;

/**
 * The Geometry interface represents a geometric object that can be intersected with a Ray and has a normal vector.
 */
public abstract class Geometry extends Intersectable {

    /**
     * The emission color of the geometry.
     */
    protected Color emission = Color.BLACK;

    /**
     * The material of the geometry.
     */
    private Material material = new Material();

    /**
     * getter for the material
     *
     * @return the material
     */
    public Material getMaterial() { return material; }

    /**
     * setter for the material
     *
     * @param material the material
     * @return the geometry
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * getter for the emission color
     *
     * @return the emission color
     */
    public Color getEmission() { return emission; }

    /**
     * setter for the emission color
     *
     * @param emission the emission color
     * @return the geometry
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * Returns the normal vector to the geometry object at the specified point on the surface of the object.
     *
     * @param p The point on the geometry object surface.
     * @return The normal vector to the geometry object at the specified point.
     */
    public abstract Vector getNormal(Point p);
}