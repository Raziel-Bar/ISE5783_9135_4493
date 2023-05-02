package geometries;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import primitives.*;

/**
 * Unit tests for geometries.Tube class
 *
 * @author - Eliezer E. Segall & Raziel Barchichat
 *
 */
class TubeTest {

    /**
     * Test method for {@link geometries.Tube#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for getting a normal to a tube's simple point (point is not in front of the axisRay's p0)
        Tube t1 = new Tube(2, new Ray(new Vector(0, 0, 2), new Point(0, 0, 0)));
        Point p1 = new Point(0, 2, 3);
        Point p2 = new Point(0, 2, 0);
        Vector v1 = t1.getNormal(p1);
        assertEquals(new Vector(0, 1, 0), v1, "Tube getNormal() TC01.1 failed");
        assertEquals(1, v1.length(), "Tube getNormal() TC01.2 failed");

        // =============== Boundary Values Tests ==================
        // TC02: Test for getting a normal to a tube's point that is in front of the axisRay's p0
        // meaning that the vector (point - p0) is orthogonal to the axisRay's direction vector
        Vector v2 = t1.getNormal(p2);
        assertEquals(new Vector(0,1,0), v2, "Tube getNormal() TC02.1 failed");
        assertEquals(1, v2.length(), "Tube getNormal() TC02.2 failed");
    }
}