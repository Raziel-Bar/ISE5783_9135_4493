package geometries;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import primitives.*;

/**
 * Unit tests for geometries.Sphere class
 *
 * @author - Eliezer E. Segall & Raziel Barchichat
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

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(1d, new Point(1, 0, 0));
        Sphere sphere2 = new Sphere(1, new Point(2, 0, 0)); // easier to calculate

    // ============ Equivalence Partitions Tests ==============
    // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Vector(1, 1, 0), new Point(-1, 0, 0))),
                "Ray's line out of sphere");

    // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(new Ray(new Vector(3, 1, 0),new Point(-1, 0, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

    // TC03: Ray starts inside the sphere (1 point)
        p1 = new Point(2.5,0 ,Math.sqrt(0.75));
        result = sphere2.findIntersections(new Ray(new Vector(0, 0, 1), new Point(2.5, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(p1), result, "Ray from inside sphere");

    // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere2.findIntersections(new Ray(new Vector(0, 0, 1), new Point(2, 0, 3))),
                "Ray's line out of sphere");



    // =============== Boundary Values Tests ==================
    // **** Group: Ray's line crosses the sphere (but not the center)
    // TC11: Ray starts at sphere and goes inside (1 point)
        p1 = new Point(2, 0, 1);
        result = sphere2.findIntersections(new Ray(new Vector(1, 0, 1), new Point(1, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(p1), result, "Ray from sphere inside");

    // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere2.findIntersections(new Ray(new Vector(1, 0, 1), new Point(2, 0, 1))),
                "Ray's line out of sphere");


    // **** Group: Ray's line goes through the center
    // TC13: Ray starts before the sphere (2 points)
        p1 = new Point(1,0,0);
        p2 = new Point(3,0,0);
        result = sphere2.findIntersections(new Ray(new Vector(1, 0, 0), new Point(-1, 0, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Line through O, ray crosses sphere");

    // TC14: Ray starts at sphere and goes inside (1 point)
        p1 = new Point(3,0,0);
        result = sphere2.findIntersections(new Ray(new Vector(1, 0, 0), new Point(1, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(p1), result, "Line through O, ray from and crosses sphere");

    // TC15: Ray starts inside (1 point)
        p1 = new Point(3,0,0);
        result = sphere2.findIntersections(new Ray(new Vector(1, 0, 0), new Point(1.5, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(p1), result, "Line through O, ray from inside sphere");

    // TC16: Ray starts at the center (1 point)
        result = sphere2.findIntersections(new Ray(new Vector(1, 0, 0), new Point(2, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(p1), result, "Line through O, ray from O");

    // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere2.findIntersections(new Ray(new Vector(1, 0, 0), new Point(3, 0, 0))),
                "Line through O, ray from sphere outside");

    // TC18: Ray starts after sphere (0 points)
        assertNull(sphere2.findIntersections(new Ray(new Vector(1, 0, 0), new Point(4, 0, 0))),
                "Line through O, ray outside sphere");

    // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
    // TC19: Ray starts before the tangent point
        assertNull(sphere2.findIntersections(new Ray(new Vector(1, 0, 0), new Point(0, 0, 1))),
                "Tangent line, ray before sphere");

    // TC20: Ray starts at the tangent point
        assertNull(sphere2.findIntersections(new Ray(new Vector(1, 0, 0), new Point(2, 0, 1))),
                "Tangent line, ray at sphere");

    // TC21: Ray starts after the tangent point
        assertNull(sphere2.findIntersections(new Ray(new Vector(1, 0, 0), new Point(3, 0, 1))),
                "Tangent line, ray after sphere");

    // **** Group: Special cases
    // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere2.findIntersections(new Ray(new Vector(1, 0, 0), new Point(2, 0, 2))),
                "Ray orthogonal to ray head -> O line");
    }
}