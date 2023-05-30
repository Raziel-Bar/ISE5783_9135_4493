package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for geometries.Triangle class
 *
 * @author - Eliezer E. Segall &amp; Raziel Barchichat
 */
class TriangleTest {
    /**
     * Test method for {@link geometries.Triangle#getNormal(Point)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for getting a normal to a triangle
        Point p1 = new Point(1, 0, 1);
        Point p2 = new Point(0, 1, 5);
        Point p3 = new Point(2, 0, 1);
        Triangle t1 = new Triangle(p1, p2, p3);
        Vector v1 = p1.subtract(p2);
        Vector v2 = p1.subtract(p3);
        Vector v3 = t1.getNormal(null);
        assertEquals(0, v3.dotProduct(v1), "Triangle getNormal() TC01.1 failed");
        assertEquals(0, v3.dotProduct(v2), "Triangle getNormal() TC01.2 failed");
        assertEquals(1, v3.length(), "Triangle getNormal() TC01.3 failed");
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(Ray)}.
     */
    @Test
    public void testFindIntersections() {

        Triangle tr = new Triangle(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1));

        // ============ Equivalence Partitions Tests ============
        // TC01: Inside polygon/triangle
        List<Point> result = tr.findIntersections(new Ray(new Vector(1, 2, 1), new Point(-1, -2, -1)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point(0.25, 0.5, 0.25), result.get(0), "Ray crosses triangle once");

        // TC02: Outside against edge
        assertNull(tr.findIntersections(new Ray(new Point(-1, -2, -1), new Vector(1, 3, 3)))
                , "Ray's crosses outside the triangle");

        // TC03: Outside against vertex
        assertNull(tr.findIntersections(new Ray(new Point(-1, -2, -1), new Vector(1, 2, 4)))
                , "Ray's crosses outside the triangle");


        // =============== Boundary Values Tests ==================
        // TC04: In vertex
        assertNull(tr.findIntersections(new Ray(new Point(-1, -2, -1), new Vector(1, 2, 2)))
                , "Ray's crosses the triangle's vertices");

        // TC05: On edge
        assertNull(tr.findIntersections(new Ray(new Point(-1, -2, -1), new Vector(1.5, 2, 1.5)))
                , "Ray's crosses the triangle's edge");

        // TC06: On edge's continuation
        assertNull(tr.findIntersections(new Ray(new Point(-1, -2, -1), new Vector(0, 2, 3)))
                , "Ray's crosses the triangle's edge");
    }
}