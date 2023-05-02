package geometries;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import primitives.*;

/**
 * Unit tests for geometries.Sphere class
 *
 * @author - Eliezer E. Segall & Raziel Barchichat
 *
 */
class SphereTest {

    /**
     * Test method for {@link geometries.Sphere#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for getting a normal to a sphere's given point
        Sphere s1 = new Sphere(2, new Point(1, 1, 1));
        Point p1 = new Point(1, 1, 3);
        Vector v1 = s1.getNormal(p1);
        assertEquals(new Vector(0, 0, 1), v1, "Sphere getNormal() TC01.1 failed");
        assertEquals(1, v1.length(), "Sphere getNormal() TC01.2 failed");
    }
}