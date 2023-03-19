package geometries;

import primitives.Ray;

public abstract class RadialGeometry implements Geometry{
    protected double radius;
    public RadialGeometry(double r){
        radius = r;
    }
}
