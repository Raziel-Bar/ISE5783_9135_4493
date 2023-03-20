package geometries;

import primitives.*;

public class Tube extends RadialGeometry{
    protected final Ray axisRay;

    public Tube(double r, Ray axisRay) {
        super(r);
        this.axisRay = axisRay;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }

    @Override
    public String toString() {
        return "Tube: " +
                "axisRay= " + axisRay +
                ", radius= " + radius;
    }
}
