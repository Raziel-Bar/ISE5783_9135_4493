package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * The Camera class represents a camera in 3D space.
 */
public class Camera {
    /**
     * The position of the camera.
     */
    private final Point position;
    /**
     * The vector that points to the up direction of the camera.
     */
    private final Vector vUp;
    /**
     * The vector that points to the right direction of the camera.
     */
    private final Vector vRight;
    /**
     * The vector that points to the front direction of the camera.
     */
    private final Vector vTo;
    /**
     * The distance between the camera and the view plane.
     */
    private double distance;
    /**
     * The width of the view plane.
     */
    private double width;
    /**
     * The height of the view plane.
     */
    private double height;

    /**
     * The number of pixels per width.
     */
    private ImageWriter imageWriter;

    /**
     * The ray tracer base.
     */
    private RayTracerBase rayTracerBase;


    /**
     * Sets the ray tracer base.
     *
     * @param rayTracerBase The ray tracer base.
     * @return The camera itself.
     */
    public Camera setRayTracer(RayTracerBase rayTracerBase) {
        this.rayTracerBase = rayTracerBase;
        return this;
    }

    /**
     * Sets the image writer.
     *
     * @param imageWriter The image writer.
     * @return The camera itself.
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * Constructs a new Camera object with the specified position, up vector, to vector, distance, width, and height.
     *
     * @param position The position of the camera.
     * @param vUp      The vector that points to the up direction of the camera.
     * @param vTo      The vector that points to the front direction of the camera.
     */
    public Camera(Point position, Vector vTo, Vector vUp) {
        if (!isZero(vUp.dotProduct(vTo)))
            throw new IllegalArgumentException("vUp and vTo must be orthogonal.");

        this.position = position;
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();
        this.vRight = this.vTo.crossProduct(this.vUp).normalize();
    }

    /**
     * Returns the position of the camera.
     *
     * @return The position of the camera.
     */
    @SuppressWarnings("unused")
    public Point getPosition() {
        return position;
    }

    /**
     * Returns the vector that points to the up direction of the camera.
     *
     * @return The vector that points to the up direction of the camera.
     */
    @SuppressWarnings("unused")
    public Vector getVUp() {
        return vUp;
    }

    /**
     * Returns the vector that points to the right direction of the camera.
     *
     * @return The vector that points to the right direction of the camera
     */
    @SuppressWarnings("unused")
    public Vector getVRight() {
        return vRight;
    }

    /**
     * Returns the vector that points to the front direction of the camera.
     *
     * @return The vector that points to the front direction of the camera.
     */
    @SuppressWarnings("unused")
    public Vector getVTo() {
        return vTo;
    }

    /**
     * Returns the distance between the camera and the view plane.
     *
     * @return The distance between the camera and the view plane.
     */
    @SuppressWarnings("unused")
    public double getDistance() {
        return distance;
    }

    /**
     * Returns the width of the view plane.
     *
     * @return The width of the view plane.
     */
    @SuppressWarnings("unused")
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the view plane.
     *
     * @return The height of the view plane.
     */
    @SuppressWarnings("unused")
    public double getHeight() {
        return height;
    }

    /**
     * Sets the view plane's size.
     *
     * @param width  The new width of the view plane.
     * @param height The new height of the view plane.
     * @return The camera itself.
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }


    /**
     * Sets the view plane's distance from the camera.
     *
     * @param distance The new distance between the camera and the view plane.
     * @return The camera itself.
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * constructs a Ray from the camera to the given pixel
     *
     * @param nX number of pixels in x-axis
     * @param nY number of pixels in y-axis
     * @param j  index of pixel in x-axis
     * @param i  index of pixel in y-axis
     * @return the Ray from the camera to the given pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point pC = position.add(vTo.scale(distance));

        double rX = width / nX;
        double rY = height / nY;
        double xJ = (j - (nX - 1) / 2.0) * rX;
        double yI = (i - (nY - 1) / 2.0) * rY;

        Point pIJ = pC;
        if (!isZero(xJ)) pIJ = pIJ.add(vRight.scale(xJ));
        if (!isZero(yI)) pIJ = pIJ.add(vUp.scale(-yI));

        Vector vIJ = pIJ.subtract(position).normalize();
        return new Ray(position, vIJ);
    }

    /**
     * renders the image
     */
    public void renderImage() {
        if (imageWriter == null) {
            throw new IllegalStateException("Image writer not set.");
        }
        if (rayTracerBase == null) {
            throw new IllegalStateException("Ray tracer base not set.");
        }
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                Ray ray = constructRay(nX, nY, j, i);
                imageWriter.writePixel(j, i, rayTracerBase.traceRay(ray));
            }
        }
    }

    /**
     * prints a grid on the image
     *
     * @param interval the interval between the lines
     * @param color    the color of the lines
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null) {
            throw new IllegalStateException("Image writer not set.");
        }
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(j, i, color);
                }
            }
        }
    }

    /**
     * writes the image to the file
     */
    public void writeToImage() {
        if (imageWriter == null) {
            throw new IllegalStateException("Image writer not set.");
        }
        imageWriter.writeToImage();
    }

}

