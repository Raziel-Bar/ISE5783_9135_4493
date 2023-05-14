package renderer;

import primitives.*;

/**
 * The Camera class represents a camera in 3D space.
 */
public class Camera {
    /**
     * The position of the camera.
     */
    private Point position;
    /**
     * The vector that points to the up direction of the camera.
     */
    private Vector vUp;
    /**
     * The vector that points to the right direction of the camera.
     */
    private Vector vRight;
    /**
     * The vector that points to the front direction of the camera.
     */
    private Vector vTo;
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
     * Constructs a new Camera object with the specified position, up vector, to vector, distance, width, and height.
     *
     * @param _position The position of the camera.
     * @param _vUp      The vector that points to the up direction of the camera.
     * @param _vTo      The vector that points to the front direction of the camera.
     */
    public Camera(Point _position, Vector _vTo, Vector _vUp) {
        if (_vUp.dotProduct(_vTo) != 0) {
            throw new IllegalArgumentException("vUp and vTo must be orthogonal.");
        }
        this.position = _position;
        this.vUp = _vUp.normalize();
        this.vTo = _vTo.normalize();
        this.vRight = this.vTo.crossProduct(this.vUp).normalize();
    }

    /**
     * Returns the position of the camera.
     *
     * @return The position of the camera.
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Returns the vector that points to the up direction of the camera.
     *
     * @return The vector that points to the up direction of the camera.
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * Returns the vector that points to the right direction of the camera.
     *
     * @return The vector that points to the right direction of the camera
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * Returns the vector that points to the front direction of the camera.
     *
     * @return The vector that points to the front direction of the camera.
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * Returns the distance between the camera and the view plane.
     *
     * @return The distance between the camera and the view plane.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Returns the width of the view plane.
     *
     * @return The width of the view plane.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the view plane.
     *
     * @return The height of the view plane.
     */
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
        if (!Util.isZero(xJ)) {
            pIJ = pIJ.add(vRight.scale(xJ));
        }
        if (!Util.isZero(yI)) {
            pIJ = pIJ.add(vUp.scale(-yI));
        }
        Vector vIJ = pIJ.subtract(position).normalize();
        return new Ray(position, vIJ);
    }

}

