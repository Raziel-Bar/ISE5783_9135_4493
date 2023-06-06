package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for primitives.Ray class
 */
class RayTest {

    /**
     * Test method for getting the closest point to ray point
     */
    @Test
    void findClosestPoint() {
        Ray ray = new Ray(new Point(1, 0, 0), new Vector(-1, 1, 0));

        // ============ Equivalence Partitions Tests ==============
        // TC01: The point that was found is in the middle of the list

        assertEquals(new Point(2, 0, 0),
                ray.findClosestPoint(List.of(new Point(5, 0, 0), new Point(2, 0, 0), new Point(8, 0, 0))), "The point is not the closest to the ray (middle test)");

        // =============== Boundary Values Tests ==================

        // TC11: No point was found (empty list)
        assertNull(ray.findClosestPoint(null), "The list should return null");

        // TC12: The point that was found is in the head of the list
        assertEquals(new Point(2, 0, 0),
                ray.findClosestPoint(List.of(new Point(2, 0, 0), new Point(5, 0, 0), new Point(8, 0, 0))), "The point is not close to the ray (head test)");

        // TC13: The point that was found is in the tail of the list
        assertEquals(new Point(2, 0, 0),
                ray.findClosestPoint(List.of(new Point(8, 0, 0), new Point(5, 0, 0), new Point(2, 0, 0))), "The point is not close to the ray (tail test)");
    }

    /**
     * Test method for getting the point on the ray
     */
    @Test
    void getPoint() {
        Ray ray = new Ray(new Point(1, 0, 0), new Vector(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============
        // TC01: point on positive side of the ray (t > 0)

        assertEquals(new Point(3, 0, 0),
                ray.getPoint(2), "ray.getPoint() TC01 failed");

        // TC02: point on negative side of the ray (t < 0)
        assertEquals(new Point(-1, 0, 0),
                ray.getPoint(-2), "ray.getPoint() TC02 failed");

        // =============== Boundary Values Tests ==================
        // TC03: point is the ray head (t = 0)
        assertEquals(new Point(1, 0, 0), ray.getPoint(0), "ray.getPoint() TC03 failed");
    }
}