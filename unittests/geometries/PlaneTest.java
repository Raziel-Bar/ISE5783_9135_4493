package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import primitives.*;

import java.util.List;

/**
 * Unit tests for geometries.Plane class
 *
 * @author - Eliezer E. Segall & Raziel Barchichat
 */
class PlaneTest {

    /**
     * Test method for {@link geometries.Plane#Plane(Point, Point, Point)}.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for correct plane creation
        Point q1 = new Point(1, 0, 0);
        Point q2 = new Point(0, 1, 0);
        Point q3 = new Point(0, 0, 1);
        assertDoesNotThrow(() -> new Plane(q1, q2, q3), "Plane constructor() TC01 failed");


        // =============== Boundary Values Tests ==================
        // TC02: Test for creating a plane with two points that are the same
        assertThrows(IllegalArgumentException.class, () -> new Plane(q1, q1, q3), "Plane constructor() TC02 failed");

        // TC03: Test for creating a plane with three points that are on the same line
        Point q4 = new Point(2, 0, 0);
        Point q5 = new Point(4, 0, 0);
        assertThrows(IllegalArgumentException.class, () -> new Plane(q1, q4, q5), "Plane constructor() TC03 failed");
    }

    /**
     * Test method for {@link geometries.Plane#getNormal(Point)}.
     */
    @Test
    void getNormal() {
        Point q1 = new Point(1, 0, 0);
        Point q2 = new Point(0, 1, 0);
        Point q3 = new Point(0, 0, 1);
        Plane p1 = new Plane(q1, q2, q3);
        Vector v1 = q1.subtract(q2);
        Vector v2 = q1.subtract(q3);
        Vector v3 = p1.getNormal();
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for getting a normal to a plane
        assertEquals(0, v3.dotProduct(v1), "Plane getNormal() TC01.1 failed");
        assertEquals(0, v3.dotProduct(v2), "Plane getNormal() TC01.2 failed");
        assertEquals(1, v3.length(), "Plane getNormal() TC01.3 failed");
    }

    /**
     * Test method for {@link geometries.Plane#findIntersections(Ray)}.
     */
    @Test
    void testFindIntersections() {
        Plane p1 = new Plane(new Point(1, 0, 0), new Point(0,1,0), new Point(1, 1, 0)); // xy plane
        // ============ Equivalence Partitions Tests ==============
        // In those cases, the ray is neither parallel nor orthogonal to the plane
        // TC01: Test for intersection with the plane (1 point)
        Ray r1 = new Ray(new Vector(1, 1, -1), new Point(0, 0, 1));
        List<Point> l1 = p1.findIntersections(r1);
        assertEquals(1, l1.size(), "Plane findIntersections() TC01.1 failed");
        assertEquals(new Point(1, 1, 0), l1.get(0), "Plane findIntersections() TC01.2 failed");

        // TC02: Test for no intersections with the plane (0 points)
        Ray r2 = new Ray(new Vector(1, 1, 1), new Point(0, 0, 1));
        assertNull(p1.findIntersections(r2), "Plane findIntersections() TC02 failed");

        // =============== Boundary Values Tests ==================
        // group: ray is parallel to the plane
        // TC03: Test for ray included in the plane (0 points)
        Ray r3 = new Ray(new Vector(1, 0, 0), new Point(0, 2, 0));
        assertNull(p1.findIntersections(r3), "Plane findIntersections() TC03 failed");

        // TC04: Test for ray not included in the plane (0 points)
        Ray r4 = new Ray(new Vector(1, 0, 0), new Point(0, 2, 1));
        assertNull(p1.findIntersections(r4), "Plane findIntersections() TC04 failed");

        // group: ray is orthogonal to the plane
        // TC05: Test for ray after the plane (1 point)
        Ray r5 = new Ray(new Vector(0, 0, 1), new Point(0, 0, 1));
        assertNull(p1.findIntersections(r5), "Plane findIntersections() TC05 failed");

        // TC06: Test for ray in the plane (0 points)
        Ray r6 = new Ray(new Vector(0, 0, 1), new Point(0, 1, 0));
        assertNull(p1.findIntersections(r6), "Plane findIntersections() TC06 failed");

        // TC07: Test for ray before the plane (0 points)
        Ray r7 = new Ray(new Vector(0, 0, 1), new Point(0, 0, -1));
        List<Point> l2 = p1.findIntersections(r7);
        assertEquals(1, l2.size(), "Plane findIntersections() TC07.1 failed");
        assertEquals(new Point(0, 0, 0), l2.get(0), "Plane findIntersections() TC07.2 failed");
    }
}