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
    Vector v4 = new Vector(1, 0, 0);
    Vector v5 = new Vector(1, 0, 1);
    Point p1 = new Point(-2, -4, -6);

    /**
     * Test method for {@link primitives.Vector#Vector(double, double, double)}.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for creating a simple vector
        assertDoesNotThrow(() -> new Vector(1, 2, 3), "Error: testConstructor() TC01 failed");

        // =============== Boundary Values Tests ==================
        // TC02: Test for zero vector
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0), "Error: testConstructor() TC02 failed");
    }


    /**
     * Test method for {@link primitives.Vector#scale(double)}
     */
    @Test
    void testScale() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for scaling a vector by a negative scalar
        assertEquals(v2, v1.scale(-2), "Error: testScale() TC01 failed");

        // TC02: Test for scaling a vector by a positive scalar
        assertEquals(v1, v1.scale(1), "Error: testScale() TC02 failed");

        // =============== Boundary Values Tests ==================
        // TC2: Test for scaling a vector by zero
        assertThrows(IllegalArgumentException.class, () -> v1.scale(0), "Error: testScale() TC03 failed");
    }

    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for adding two vectors
        assertEquals(v3, v1.add(v2), "Error: testAdd() TC01 failed");

        // =============== Boundary Values Tests ==================
        // TC2: Test for adding a vector to its opposite
        assertThrows(IllegalArgumentException.class, () -> v1.add(v3), "Error: testAdd() TC02 failed");
    }

    /**
     * Test method for {@link primitives.Vector#subtract(Point)}}.
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for subtracting a simple point
        assertEquals(v1, v3.subtract(p1), "Error: testSubtract() TC01 failed");

        // =============== Boundary Values Tests ==================
        // TC2: Test for subtracting a vector from its opposite
        assertThrows(IllegalArgumentException.class, () -> v2.subtract(p1), "Error: testSubtract() TC02 failed");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for a negative dot product of two vectors (obtuse angle)
        assertEquals(-28, v1.dotProduct(v2), "Error: testDotProduct() TC01 failed");

        // TC02: Test for a positive dot product of two vectors (acute angle)
        assertEquals(11, v1.dotProduct(new Vector(1, 2, 2)), "Error: testDotProduct() TC02 failed");

        // =============== Boundary Values Tests ==================
        // TC03: Test for dot product of orthogonal vectors (90 degrees)
        assertEquals(0, v1.dotProduct(new Vector(1, 1, -1)), "Error: testDotProduct() TC03 failed");

        // TC04: Test for dot product of a vector with itself
        assertEquals(14, v1.dotProduct(v1), "Error: testDotProduct() TC04 failed");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for cross product of two vectors (angle is less than 180 degrees)
        assertEquals(new Vector(0, 1, 0), v4.crossProduct(v5), "Error: testCrossProduct() TC01 failed");

        // TC02: Test for cross product of two vectors (angle is greater than 180 degrees)
        assertEquals(new Vector(0, -1, 0), v5.crossProduct(v4), "Error: testCrossProduct() TC02 failed");

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
        assertEquals(new Vector(1 / Math.sqrt(14), 2 / Math.sqrt(14), 3 / Math.sqrt(14)), v1.normalize(), "ERROR: normalize() wrong value");
        // TC02: Test for normalize of a negative vector
        assertEquals(new Vector(-1 / Math.sqrt(14), -2 / Math.sqrt(14), -3 / Math.sqrt(14)), v3.normalize(), "ERROR: normalize() wrong value");
    }
}