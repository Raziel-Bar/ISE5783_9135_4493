package lighting;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.*;

/**
 * The SpotLight class represents a spot light source in 3D space.
 */
public class SpotLight extends PointLight {
    /**
     * The direction of the light.
     */
    private final Vector direction;

    /**
     * The points on the target area grid
     */
    private List<Point> targetPoints = null;

    /**
     * getter for the direction of the light
     *
     * @return the direction of the light
     */
    @SuppressWarnings("unused")
    public List<Point> getTargetPoints() {
        return targetPoints;
    }

    /**
     * Constructor for the SpotLight class.
     *
     * @param intensity The color of the light.
     * @param position  The position of the light.
     * @param direction The direction of the light.
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }


    /**
     * Constructor for the SpotLight class.
     *
     * @param intensity The color of the light.
     * @param position  The position of the light.
     * @param direction The direction of the light.
     * @param radius    The radius of the light source's "body" for soft shadows
     */
    public SpotLight(Color intensity, Point position, Vector direction, double radius) {
        super(intensity, position, radius);
        this.direction = direction.normalize();
        Vector v = !(isZero(direction.getX()) || isZero(direction.getY())) ? new Vector(direction.getY(), -direction.getX(), 0) :
                new Vector(1, 1, 0).normalize();
        if (radius == 0) {
            targetPoints = new LinkedList<>();
            targetPoints.add(position);
        } else
            targetPoints = BlackBoard.constructCircleBlackBoard(BlackBoard.getMAX_CELLS(), position, v, direction.crossProduct(v), radius);
    }

    @Override
    public Color getIntensity(Point p) {
        Vector l = getL(p);

        if (Util.alignZero(l.dotProduct(direction)) <= 0)
            return Color.BLACK;

        return super.getIntensity(p).scale(l.dotProduct(direction));
    }

    @Override
    public Vector getL(Point p) {
        return super.getL(p);
    }

    @Override
    public List<Point> getGridPoints(Vector l) {
        return this.targetPoints;
    }
}
