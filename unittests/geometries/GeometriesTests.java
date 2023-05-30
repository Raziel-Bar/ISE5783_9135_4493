package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for geometries.Geometries class
 */
public class GeometriesTests {

    /**
     * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Some of the geometries intersect with the ray but not all (2 points)
        Geometries geometries = new Geometries(
                new Sphere(1, new Point(1, 0, 0)),
                new Plane(new Point(0, 0, 1), new Vector(0, 0, 1)),
                new Triangle(new Point(0, 1, 0), new Point(0, 0, 1), new Point(1, 0, 0))
        );
        assertEquals(2, geometries.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 0, 0))).size(), "Wrong number of points");

        // =============== Boundary Values Tests ==================
        // TC02: None of the geometries intersect with the ray
        assertNull(geometries.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(-1, 0, 0))), "Wrong number of points");

        // TC03: All geometries intersect with the ray (4 points)
        assertEquals(4, geometries.findIntersections(new Ray(new Point(-1, -1, -1), new Vector(1, 1, 1))).size(), "Wrong number of points");

        // TC04: Only one geometry intersect with the ray (1 point)
        assertEquals(1, geometries.findIntersections(new Ray(new Point(0, 0, 2), new Vector(0, 0, -1))).size(), "Wrong number of points");

        // TC05: the geometries is empty (0 points)
        geometries = new Geometries();
        assertNull(geometries.findIntersections(new Ray(new Point(0, 0, 2), new Vector(0, 0, -1))), "Wrong number of points");
    }
}
