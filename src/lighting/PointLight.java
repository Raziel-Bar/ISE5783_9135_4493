package lighting;
import primitives.*;

/**
 * The PointLight class represents a point light source in 3D space.
 */
public class PointLight extends Light implements LightSource{
    /**
     * The position of the light.
     */
    private Point position;

    /**
     * The constant attenuation factors.
     */
    private double kC = 1, kL = 0, kQ = 0;

    /**
     * Constructor for the PointLight class.
     *
     * @param intensity The color of the light.
     * @param position The position of the light.
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * setter for kC
     * @param kC the constant attenuation factor
     * @return the point light
     */
    public PointLight setKC(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * setter for kL
     * @param kL the linear attenuation factor
     * @return the point light
     */
    public PointLight setKL(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * setter for kQ
     * @param kQ the quadratic attenuation factor
     * @return the point light
     */
    public PointLight setKQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double d = position.distance(p);
        double attenuation = 1 / (kC + kL * d + kQ * d * d);
        return getIntensity().scale(attenuation);
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point point) {
        return point.distance(position);
    }
}
