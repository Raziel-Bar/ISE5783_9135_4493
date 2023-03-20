package geometries;

import primitives.Point;

/**
 * The Triangle class represents a triangle in 3D space, defined by three points.
 */
public class Triangle extends Polygon {
    /**
     * Constructs a new Triangle object with the specified vertices.
     *
     * @param vertices The vertices of the Triangle object.
     */
    public Triangle(Point... vertices) {
        super(vertices);
    }
}
