package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public abstract class RadialGeometry implements Geometry{
    protected final double radius;
    public RadialGeometry(double r){
        radius = r;
    }

    public double getRadius() {
        return radius;
    }
}
