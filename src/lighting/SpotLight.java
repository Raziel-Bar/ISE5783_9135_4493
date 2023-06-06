package lighting;
import primitives.*;
import static primitives.Util.*;
/**
 * The SpotLight class represents a spot light source in 3D space.
 */
public class SpotLight extends PointLight {
    /**
     * The direction of the light.
     */
    private Vector direction;

    /**
     * Constructor for the SpotLight class.
     *
     * @param intensity The color of the light.
     * @param position The position of the light.
     * @param direction The direction of the light.
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        Vector l = getL(p);

        if(Util.alignZero(l.dotProduct(direction)) <= 0)
            return Color.BLACK;

        return super.getIntensity(p).scale(l.dotProduct(direction));
    }

    @Override
    public Vector getL(Point p) {
        return super.getL(p);
    }
}
