package geometries;
import primitives.*;
public class Plane implements Geometry{
    private final Point q0;

    private final Vector normal;

    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    public Plane(Point q1, Point q2 , Point q3) {
        q0 = q1;
        normal = q1.subtract(q2).crossProduct(q1.subtract(q3)).normalize();
    }

    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point p) {
        return getNormal();
    }
}
