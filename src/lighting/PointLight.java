package lighting;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;

/**
 * The PointLight class represents a point light source in 3D space.
 */
public class PointLight extends Light implements LightSource {
    /**
     * The position of the light.
     */
    private final Point position;

    /**
     * The radius of the light source's "body" for soft shadows
     */
    private final double radius;

    /**
     * The constant attenuation factors.
     */
    private double kC = 1, kL = 0, kQ = 0;

    /**
     * Constructor for the PointLight class.
     *
     * @param intensity The color of the light.
     * @param position  The position of the light.
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
        this.radius = 0.0;
    }

    /**
     * Constructor for the PointLight class.
     *
     * @param intensity The color of the light.
     * @param position  The position of the light.
     * @param radius    The radius of the light source's "body" for soft shadows
     */
    public PointLight(Color intensity, Point position, double radius) {
        super(intensity);
        this.position = position;
        this.radius = radius;
    }

    /**
     * setter for kC
     *
     * @param kC the constant attenuation factor
     * @return the point light
     */
    @SuppressWarnings("unused")

    public PointLight setKC(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * setter for kL
     *
     * @param kL the linear attenuation factor
     * @return the point light
     */
    public PointLight setKL(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * setter for kQ
     *
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

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public List<Point> getGridPoints(Vector l) {
        if (radius == 0) {
            List<Point> li = new LinkedList<>();
            li.add(position);
            return li;
        } else {
            Vector v = !(isZero(l.getX()) || isZero(l.getY())) ? new Vector(-l.getY(), l.getX(), 0) :
                    new Vector(1, 1, 0).normalize();
            return BlackBoard.constructCircleBlackBoard(BlackBoard.getMAX_CELLS(), position, v, v.crossProduct(l), radius);
        }
    }
}
