package renderer;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author 97253 Integration tests unit
 */
public class IntegrationTests {

    /**
     * Tests the camera rays and sphere intersections
     */
    @Test
    public void testCameraAndSphere() {
        //TC01: Camera rays intersects 2 points with sphere
        Camera camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1);
        LinkedList<Ray> rayList = constructPixelsRaysList(camera.setVPSize(3, 3), 3, 3);
        Sphere sph = new Sphere(1, new Point(0, 0, -3));
        assertEquals(2, countIntersections(rayList, sph), "testCameraAndSphere TC01");

        //TC02: Camera rays intersects 18 points with sphere
        camera = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1);
        rayList = constructPixelsRaysList(camera.setVPSize(3, 3), 3, 3);
        sph = new Sphere(2.5, new Point(0, 0, -2.5));
        assertEquals(18, countIntersections(rayList, sph), "testCameraAndSphere TC02");

        //TC03: Camera rays intersects 10 points with sphere
        sph = new Sphere(2, new Point(0, 0, -2));
        assertEquals(10, countIntersections(rayList, sph), "testCameraAndSphere TC03");

        //TC04: Camera rays intersects 9 points with sphere
        sph = new Sphere(4, new Point(0, 0, -2));
        assertEquals(9, countIntersections(rayList, sph), "testCameraAndSphere TC04");

        //TC05: No camera rays intersection with sphere
        sph = new Sphere(0.5, new Point(0, 0, 1));
        assertEquals(0, countIntersections(rayList, sph), "testCameraAndSphere TC05");
    }

    /**
     * Tests the camera rays and plane intersections
     */
    @Test
    public void testCameraAndPlane() {
        Camera camera = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1);
        LinkedList<Ray> rayList = constructPixelsRaysList(camera.setVPSize(3, 3), 3, 3);

        // TC01: Camera intersects 9 points with plane
        Plane plane = new Plane(new Point(0, 0, -4), camera.getvTo());
        assertEquals(9, countIntersections(rayList, plane), "testCameraAndPlane TC01");

        // TC02: Camera rays intersects 9 points with plane
        plane = new Plane(new Point(0, 0, -4), new Vector(0, -0.2, 1));
        assertEquals(9, countIntersections(rayList, plane), "testCameraAndPlane TC02");

        // TC03: Camera rays intersects 6 points with plane
        plane = new Plane(new Point(0, 0, -4), new Vector(0, -1.5, 1));
        assertEquals(6, countIntersections(rayList, plane), "testCameraAndPlane TC03");
    }

    /**
     * Tests the camera rays and triangle intersections
     */
    @Test
    public void testCameraAndTriangle() {
        Camera camera = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1);
        LinkedList<Ray> rayList = constructPixelsRaysList(camera.setVPSize(3, 3), 3, 3);

        // TC01: Camera rays intersects 1 points with triangle
        Triangle tri = new Triangle(new Point(0, 1, -2), new Point(1, -1, -2), new Point(-1, -1, -2));
        assertEquals(1, countIntersections(rayList, tri), "testCameraAndTriangle TC01");

        // TC02: Camera rays intersects 2 points with triangle
        tri = new Triangle(new Point(0, 20, -2), new Point(1, -1, -2), new Point(-1, -1, -2));
        assertEquals(2, countIntersections(rayList, tri), "testCameraAndTriangle TC02");
    }

    /**
     * Calculates with loop all the rays from camera through middle of each pixel
     *
     * @param camera the camera
     * @param nX     number of pixels in X axis
     * @param nY     number of pixels in Y axis
     * @return List of rays from camera through pixels
     */
    public LinkedList<Ray> constructPixelsRaysList(Camera camera, int nX, int nY) {
        LinkedList<Ray> raysList = new LinkedList<>();
        for (int j = 0; j < nY; j++) {
            for (int i = 0; i < nX; i++) { // For each pixel calls "constructRay" function
                raysList.add(camera.constructRay(nX, nY, j, i));
            }
        }
        return raysList;
    }

    /**
     * Finds and sums up the number of camera rays that intersect with a given shape
     *
     * @param rayList List of rays from camera through pixels
     * @param shape   The shape to check intersections with
     * @return Number of intersections of the camera rays with a given shape
     */
    public int countIntersections(LinkedList<Ray> rayList, Intersectable shape) {
        int counter = 0;
        for (Ray ray : rayList) { // The loop checks intersections for each ray with the given shape
            List<Point> pointsList = shape.findIntersections(ray);
            counter += pointsList == null ? 0 : pointsList.size();
        }
        return counter;
    }
}