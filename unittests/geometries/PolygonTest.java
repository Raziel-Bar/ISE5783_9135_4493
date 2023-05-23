package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Testing Polygons
 *
 * @author Dan
 * modified by Eliezer E. Segall &amp; Raziel Barchichat
 */
public class PolygonTest {

    /**
     * Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        assertDoesNotThrow(() -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                new Point(-1, 1, 1)), "Failed constructing a correct polygon");

        // TC02: Wrong vertices order
        assertThrows(IllegalArgumentException.class, () -> new Polygon(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0), new Point(-1, 1, 1)),
                "Constructed a polygon with wrong order of vertices");

        // TC03: Not in the same plane
        assertThrows(IllegalArgumentException.class, () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 2, 2)),
                "Constructed a polygon with vertices that are not in the same plane");

        // TC04: Concave quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0.5, 0.25, 0.5)), "Constructed a concave polygon");

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0, 0.5, 0.5)),
                "Constructed a polygon with vertix on a side");

        // TC11: Last point = first point
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)),
                "Constructed a polygon with vertice on a side");

        // TC12: Co-located points
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 1, 0)),
                "Constructed a polygon with vertice on a side");

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Point[] pts =
                {new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1)};
        Polygon pol = new Polygon(pts);
        // ensure there are no exceptions
        assertDoesNotThrow(() -> pol.getNormal(new Point(0, 0, 1)), "");
        // generate the test result
        Vector result = pol.getNormal(new Point(0, 0, 1));
        // ensure |result| = 1
        assertEquals(1, result.length(), 0.00000001, "Polygon's normal is not a unit vector");
        // ensure the result is orthogonal to all the edges
        for (int i = 0; i < 3; ++i)
            assertTrue(isZero(result.dotProduct(pts[i].subtract(pts[i == 0 ? 3 : i - 1]))),
                    "Polygon's normal is not orthogonal to one of the edges");
    }

    /*
    /**
     * Test method for {@link geometries.Polygon#findIntersections(primitives.Ray)}.
     --
     @Test
     public void testFindIntersections() {
     Polygon square = new Polygon(new Point(0, 0, 0), new Point(2, 0, 0),
     new Point(2, 2, 0),new Point(0, 2, 0));

     // ============ Equivalence Partitions Tests ==============
     // TC01: Inside square(1 point)
     Point p1 = new Point(1, 1, 0);
     List<Point> result = square.findIntersections(new Ray(new Vector(1, 1, -1), new Point(0, 0, 1)));
     assertEquals( 1, result.size(),"Wrong number of points");
     assertEquals(List.of(p1), result,"Ray crosses square");

     // TC02: Outside against edge(0 points)
     assertNull(square.findIntersections(new Ray(new Vector(3, 3, -1), new Point(0, 0, 1)))
     ,"Outside against edge");

     // TC03: Outside against vertex(0 points)
     assertNull(square.findIntersections(new Ray(new Vector(1, 3, -1), new Point(0, 0, 1))),"Outside against vertex");

     // =============== Boundary Values Tests ==================
     // TC04: ray crosses edge (0 points)
     assertNull(square.findIntersections(new Ray(new Vector(2, 0, -1), new Point(0, 0, 1))),"Outside against vertex");

     // TC05: ray crosses vertex (0 points)
     assertNull(square.findIntersections(new Ray(new Vector(1, 0, -1), new Point(0, 0, 1))),"Outside against vertex");

     // TC06: ray crosses edge's continuation (0 points)
     assertNull(square.findIntersections(new Ray(new Vector(3, 0, -1), new Point(0, 0, 1))),"Outside against vertex");
     }*/
}
