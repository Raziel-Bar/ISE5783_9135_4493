package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for primitives.Vector class
 */
class VectorTest {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(-1, -2, -3);
    Point p1 = new Point(-2, -4, -6);

    /**
     * Test method for {@link primitives.Vector#Vector(double, double, double)}.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for creating a simple vector
        try {
            new Vector(1, 2, 3);
        } catch (IllegalArgumentException e) {
            fail("ERROR: Vector's constructor is wrong");
        }

        // =============== Boundary Values Tests ==================
        // TC02: Test for zero vector
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0), "The Vector (0,0,0) is illegal for use.");
    }


    /**
     * Test method for {@link primitives.Vector#scale(double)}
     */
    @Test
    void testScale() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for scaling a vector
        assertEquals(v2, v1.scale(-2), "ERROR: Vector * scalar does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC02: Test for scaling a vector by 1
        assertEquals(v1, v1.scale(1), "ERROR: Scaling a vector by 1 does not work correctly");

        // TC2: Test for scaling a vector by zero
        assertThrows(IllegalArgumentException.class, () -> v1.scale(0), "ERROR: Scaling a vector by 0 does not work correctly");
    }

    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for adding two vectors
        assertEquals(v3, v1.add(v2), "ERROR: Vector + Vector does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC2: Test for adding a vector to its opposite
        assertThrows(IllegalArgumentException.class, () -> v1.add(v3), "ERROR: Adding a vector to its opposite does not work correctly");
    }

    /**
     * Test method for {@link primitives.Vector#subtract(Point)}}.
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for subtracting a simple point
        assertEquals(v1, v3.subtract(p1), "ERROR: Vector - Vector does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC2: Test for subtracting a zero vector
        assertEquals(v1, v1.subtract(new Point(0, 0, 0)), "ERROR: Subtracting a zero vector does not work correctly");

        // TC3: Test for subtracting a vector from its opposite
        assertThrows(IllegalArgumentException.class, () -> v2.subtract(p1), "ERROR: Subtracting a vector from its opposite does not work correctly");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for dot product of two vectors
        assertEquals(-28, v1.dotProduct(v2), "ERROR: dotProduct() for orthogonal vectors is not zero");

        // =============== Boundary Values Tests ==================
        // TC2: Test for dot product of orthogonal vectors
        assertEquals(0, v1.dotProduct(new Vector(1,1,-1)), "ERROR: dotProduct() for orthogonal vectors is not zero");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for cross product of two vectors
        assertEquals(new Vector(0, 3, -2), v1.crossProduct(new Vector(1,0,0)), "ERROR: crossProduct() wrong result length");

        // =============== Boundary Values Tests ==================
        // TC2: Test for cross product of a vector with itself
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v1), "ERROR: crossProduct() for parallel vectors does not throw an exception");

    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for lengthSquared of a vector
        assertEquals(14, v1.lengthSquared(), "ERROR: lengthSquared() wrong value");
        // TC02: Test for lengthSquared of a negative vector
        assertEquals(14, v3.lengthSquared(), "ERROR: lengthSquared() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#length()}.
     */
    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for lengthSquared of a vector
        assertEquals(Math.sqrt(14), v1.length(), "ERROR: lengthSquared() wrong value");
        // TC02: Test for lengthSquared of a negative vector
        assertEquals(Math.sqrt(14), v3.length(), "ERROR: lengthSquared() wrong value");

    }

    /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
    @Test
    void testNormalize() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for normalize of a vector
        assertEquals(new Vector(1/Math.sqrt(14), 2/Math.sqrt(14), 3/Math.sqrt(14)), v1.normalize(), "ERROR: normalize() wrong value");
        // TC02: Test for normalize of a negative vector
        assertEquals(new Vector(-1/Math.sqrt(14), -2/Math.sqrt(14), -3/Math.sqrt(14)), v3.normalize(), "ERROR: normalize() wrong value");
    }
}