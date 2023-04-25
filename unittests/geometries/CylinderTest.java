package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Cylinder class
 *
 * @author Eliezer E. Segall - Raziel Barchichat
 *
 */
class CylinderTest {
    /**
     * Test method for {@link geometries.Cylinder#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        Cylinder c1 = new Cylinder(2, new Ray(new Vector(0, 0, 1), new Point(0, 0, 0)), 3);
        Point p1 = new Point(0, 2, 2);
        Point p2 = new Point(0, 1, 3);
        Point p3 = new Point(0, 1, 0);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for getting a normal to a point on the cylinder's side
        Vector v1 = c1.getNormal(p1);
        assertEquals(new Vector(0, 1, 0), v1, "Cylinder getNormal() TC01.1 failed");
        assertEquals(1, v1.length(), "Cylinder getNormal() TC01.2 failed");

        // TC02: Test for getting a normal to a point on the cylinder's top base
        Vector v2 = c1.getNormal(p2);
        assertEquals(c1.axisRay.getDir(), v2, "Cylinder getNormal() TC02.1 failed");
        assertEquals(1, v2.length(), "Cylinder getNormal() TC02.2 failed");

        // TC03: Test for getting a normal to a point on the cylinder's bottom base
        Vector v3 = c1.getNormal(p3);
        assertEquals(c1.axisRay.getDir().scale(-1), v3, "Cylinder getNormal() TC03.1 failed");
        assertEquals(1, v3.length(), "Cylinder getNormal() TC03.2 failed");

        // =============== Boundary Values Tests ==================
        // TC04: Test for getting a normal to a point on the cylinder's top base's center
        assertEquals(c1.axisRay.getDir(), c1.getNormal(new Point(0,0,3)), "Cylinder getNormal() TC04 failed");

        // TC05: Test for getting a normal to a point on the cylinder's bottom base's center
        assertEquals(c1.axisRay.getDir().scale(-1), c1.getNormal(new Point(0,0,0)), "Cylinder getNormal() TC05 failed");
    }
}