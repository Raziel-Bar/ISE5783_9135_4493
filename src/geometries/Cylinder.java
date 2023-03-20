package geometries;
import primitives.*;
public class Cylinder extends Tube{

    private final double height;

    public Cylinder(double r, Ray axisRay, double height) {
        super(r, axisRay);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }

    @Override
    public String toString() {
        return "Cylinder: " +
                "height= " + height +
                ", axisRay= " + axisRay +
                ", radius= " + radius;
    }
}

