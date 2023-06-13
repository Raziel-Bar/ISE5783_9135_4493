package lighting;
import primitives.*;

/**
 * The LightSource interface represents a light source in 3D space.
 */
public interface LightSource {
    /**
     * Returns the intensity of the light source at the specified point.
     *
     * @param p The point at which the intensity of the light source is to be calculated.
     * @return The intensity of the light source at the specified point.
     */
    public Color getIntensity(Point p);

    /**
     * Returns the direction of the light source at the specified point.
     *
     * @param p The point at which the direction of the light source is to be calculated.
     * @return The direction of the light source at the specified point.
     */
    public Vector getL(Point p);

    /**
     * Returns the distance between the light source and the specified point.
     *
     * @param point The point at which the distance is to be calculated.
     * @return The distance between the light source and the specified point.
     */
    public double getDistance(Point point);
}
