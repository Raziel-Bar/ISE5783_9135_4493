package lighting;
import primitives.*;

import java.util.List;

/**
 * The PointLight class represents a directional light source in 3D space.
 */
public class DirectionalLight extends Light implements LightSource{
    /**
     * The direction of the light.
     */
    private final Vector direction;

    /**
     * Constructor for the DirectionalLight class.
     *
     * @param intensity The intensity of the light.
     * @param d         The direction of the light.
     */
    public DirectionalLight(Color intensity, Vector d) {
        super(intensity);
        direction = d.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }

    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public double getRadius() {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public List<Point> getGridPoints(Vector l) { return null; }
}
