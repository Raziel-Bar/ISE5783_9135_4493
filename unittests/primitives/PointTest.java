package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for primitives.Point class
 *
 * @author - Eliezer E. Segall &amp; Raziel Barchichat
 */
class PointTest {
    Point p1 = new Point(1, 1, 1);
    Point p2 = new Point(2, 2, 2);
    Point p0 = new Point(0, 0, 0);
    Vector v1 = new Vector(-1, -1, -1);

    /**
     * Test method for {@link primitives.Point#add(Vector)}
     */
    @Test
    void testAdd() {
        // =======Equivalence Partitions Tests=======
        // TC01: testing the adding of point and vector (simple test)
        assertEquals(p0, p1.add(v1), "Point testAdd() TC01 failed");
    }

    /**
     * Test method for {@link primitives.Point#subtract(Point)}
     */
    @Test
    void testSubtract() {
        // =======Equivalence Partitions Tests=======
        // TC01: testing the subtracting of point and vector (simple test)
        assertEquals(new Vector(1, 1, 1), p2.subtract(p1), "Point testSubtract() TC01 failed");

        // =======Boundary Values Tests=======
        // TC02: testing the subtracting of point and vector (same point)
        assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1), "Point testSubtract() TC02 failed");


    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(Point)} .
     */
    @Test
    void testDistanceSquared() {
        // =======Equivalence Partitions Tests=======
        // TC01: testing if the distance between two points is correct (simple test)
        assertEquals(3, p1.distanceSquared(p2), "Point testDistanceSquared() TC01 failed");

        //=============== Boundary Values Tests ==================
        // TC02: testing if the distance between two points is correct (same point)
        assertEquals(0, p1.distanceSquared(p1), "Point testDistanceSquared() TC02 failed");
    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}.
     */
    @Test
    void testDistance() {
        // =======Equivalence Partitions Tests=======
        // TC01: testing if the distance between two points is correct (simple test)
        assertEquals(Math.sqrt(3), p1.distance(p2), "Point testDistance() TC01 failed");

        //=============== Boundary Values Tests ==================
        // TC02: testing if the distance between two points is correct (same point)
        assertEquals(0, p1.distance(p1), "Point testDistance() TC02 failed");
    }
}