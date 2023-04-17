package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit tests for primitives.Point class
 */
class PointTest {
    Point p1 = new Point(1, 1, 1);
    Point p2 = new Point(2, 2, 2);
    Vector v1 = new Vector(-1, -1, -1);


    /**
     * Test method for {@link primitives.Point#add(Vector)}
     */
    @Test
    void testAdd() {

        // =======Equivalence Partitions Tests=======
        // TC01: testing the adding of point and vector (simple test)
        assertEquals(p1.add(v1), new Point(0, 0, 0), "the adding does not work correctly");
    }

    /**
     * Test method for {@link primitives.Point#subtract(Point)}
     */
    @Test
    void testSubtract() {
        // =======Equivalence Partitions Tests=======
        // TC01: testing the subtracting of point and vector (simple test)
        assertEquals(p2.subtract(v1), new Vector(3, 3, 3), "the subtracting does not work correctly");
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(Point)} .
     */
    @Test
    void testDistanceSquared() {
        // =======Equivalence Partitions Tests=======
        // TC01: testing if the distance between two points is correct (simple test)
        assertEquals(3, p1.distanceSquared(p2), "ERROR: DistanceSquared doesn't work correctly");
    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}.
     */
    @Test
    void testDistance() {
        // =======Equivalence Partitions Tests=======
        // TC01: testing if the distance between two points -with sqrt- is correct (simple test)
        assertEquals(Math.sqrt(3), p1.distance(p2), "ERROR: Distance doesn't work correctly");
    }
}