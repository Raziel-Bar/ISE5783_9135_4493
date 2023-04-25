package geometries;
import primitives.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for geometries.Triangle class
 *
 * @author - Eliezer E. Segall & Raziel Barchichat
 *
 */
class TriangleTest {
        /**
         * Test method for {@link geometries.Triangle#getNormal(Point)}.
         */
        @Test
        void getNormal() {
            // ============ Equivalence Partitions Tests ==============
            // TC01: Test for getting a normal to a triangle
            Point p1 = new Point(1, 0, 1);
            Point p2 = new Point(0, 1, 5);
            Point p3 = new Point(2, 0, 1);
            Triangle t1 = new Triangle(p1, p2, p3);
            Vector v1 = p1.subtract(p2);
            Vector v2 = p1.subtract(p3);
            Vector v3 = t1.getNormal(null);
            assertEquals(0,  v3.dotProduct(v1) ,"Triangle getNormal() TC01.1 failed");
            assertEquals(0,  v3.dotProduct(v2) ,"Triangle getNormal() TC01.2 failed");
            assertEquals(1, v3.length(),  "Triangle getNormal() TC01.3 failed");
        }
}