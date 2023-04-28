package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

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
        try {
            new Plane(q1, q2, q3);
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct plane");
        }

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
}