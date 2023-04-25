package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Plane class
 *
 * @author - Eliezer E. Segall & Raziel Barchichat
 *
 */
class PlaneTest {

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
        assertEquals(0,  v3.dotProduct(v1) ,"Plane getNormal() TC01.1 failed");
        assertEquals(0,  v3.dotProduct(v2) ,"Plane getNormal() TC01.2 failed");
        assertEquals(1, v3.length(),  "Plane getNormal() TC01.3 failed");
    }
}