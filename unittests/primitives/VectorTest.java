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
    Vector zeroVec = new Vector(0, 0, 0);


    /**
     * Test method for {@link primitives.Vector#Vector(primitives.Point3D)}.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for zero vector
        try {
            new Vector(0, 0, 0);
            fail("Constructed a zero vector");
        } catch (IllegalArgumentException e) {
        }
    }


    /**
     * Test method for {@link primitives.Vector#scale(double)}
     */
    @Test
    void testScale() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for scaling a vector
        assertEquals(v2, v1.scale(-2), "ERROR: Vector * scalar does not work correctly");

        // ============ Boundary Partitions Tests ==============
        // TC02: Test for scaling a vector by 1
        assertEquals(v1, v1.scale(1), "ERROR: Scaling a vector by 1 does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC2: Test for scaling a vector by zero
        assertEquals(zeroVec, v1.scale(0), "ERROR: Scaling a vector by zero does not work correctly");
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
        // TC2: Test for adding a zero vector
        assertEquals(v1, v1.add(new Vector(0, 0, 0)), "ERROR: Adding a zero vector does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC3: Test for adding a vector to its opposite
        assertEquals(new Vector(0, 0, 0), v1.add(v2.scale(-0.5)), "ERROR: Adding a vector to its opposite does not work correctly");
    }

    /**
     * Test method for {@link primitives.Vector#subtract(Point)}}.
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for subtracting two vectors
        assertEquals(v1,v3.subtract(p1) , "ERROR: Vector - Vector does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC2: Test for subtracting a zero vector
        assertEquals(v1, v1.subtract(new point(0, 0, 0)), "ERROR: Subtracting a zero vector does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC3: Test for subtracting a vector from its opposite
        assertEquals(zeroVec, v2.subtract(p1), "ERROR: Subtracting a vector from its opposite does not work correctly");
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
        // TC2: Test for dot product with zero vector
        assertEquals(0, v1.dotProduct(zeroVec), "ERROR: dotProduct() for orthogonal vectors is not zero");
    }

    @Test
    void testCrossProduct() {
    }

    @Test
    void testLengthSquared() {
    }

    @Test
    void testLength() {
    }

    @Test
    void testNormalize() {
    }
}