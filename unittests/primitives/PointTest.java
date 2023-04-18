package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

 // ask dan about align zero and stuff
/**
 * Unit tests for primitives.Point class
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
        assertEquals(p0, p1.add(v1), "the adding does not work correctly");

        // =======Boundary Values Tests=======
        // TC02: testing the adding of point and vector (zero point)
        assertEquals(new Point(v1.xyz), p0.add(v1), "the adding does not work correctly");
    }

    /**
     * Test method for {@link primitives.Point#subtract(Point)}
     */
    @Test
    void testSubtract() {
        // =======Equivalence Partitions Tests=======
        // TC01: testing the subtracting of point and vector (simple test)
        assertEquals(new Vector(1,1,1), p2.subtract(p1), "the subtracting does not work correctly");

        // TC02: testing the subtracting of point and vector (negative point)
        assertEquals(new Vector(3,3,3), p1.subtract(new Point(-2, -2, -2)), "the subtracting does not work correctly");

        // =======Boundary Values Tests=======
        // TC03: testing the subtracting of point and vector (zero point)
        assertEquals(new Vector(v1.xyz), p0.subtract(p1), "the subtracting does not work correctly");

        // TC04: testing the subtracting of point and vector (same point)
        assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1), "The Vector (0,0,0) is illegal for use.");


    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(Point)} .
     */
    @Test
    void testDistanceSquared() {
        // =======Equivalence Partitions Tests=======
        // TC01: testing if the distance between two points is correct (simple test)
        assertEquals(3, p1.distanceSquared(p2), "ERROR: DistanceSquared doesn't work correctly");

        // TC02: testing if the distance between two points is correct (negative point)
        assertEquals(12, p1.distanceSquared(new Point(-1, -1, -1)), "ERROR: DistanceSquared doesn't work correctly");

        //=============== Boundary Values Tests ==================
        // TC03: testing if the distance between two points is correct (zero point)
        assertEquals(3, p1.distanceSquared(p0), "ERROR: DistanceSquared doesn't work correctly");

        // TC04: testing if the distance between two points is correct (same point)
        assertEquals(0, p1.distanceSquared(p1), "ERROR: DistanceSquared doesn't work correctly");
    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}.
     */
    @Test
    void testDistance() {
        // =======Equivalence Partitions Tests=======
        // TC01: testing if the distance between two points is correct (simple test)
        assertEquals(Math.sqrt(3), p1.distance(p2), "ERROR: DistanceSquared doesn't work correctly");

        // TC02: testing if the distance between two points is correct (negative point)
        assertEquals(Math.sqrt(12), p1.distance(new Point(-1, -1, -1)), "ERROR: DistanceSquared doesn't work correctly");

        //=============== Boundary Values Tests ==================
        // TC03: testing if the distance between two points is correct (zero point)
        assertEquals(Math.sqrt(3), p1.distance(p0), "ERROR: DistanceSquared doesn't work correctly");

        // TC04: testing if the distance between two points is correct (same point)
        assertEquals(0, p1.distance(p1), "ERROR: DistanceSquared doesn't work correctly");
    }
}