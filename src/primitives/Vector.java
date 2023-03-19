package primitives;

public class Vector extends Point {
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("The Vector (0,0,0) is illegal for use.");
    }

    Vector(Double3 p) {
        this(p.d1, p.d2, p.d3);
    }

    public Vector add(Vector v) {
        return new Vector(xyz.add(v.xyz));
    }

    public Vector scale(double k) {
        return new Vector(xyz.scale(k));
    }

    public double dotProduct(Vector v) {
        return xyz.d1 * v.xyz.d1 + xyz.d2 * v.xyz.d2 + xyz.d3 * v.xyz.d3;
    }

    public Vector crossProduct(Vector v){
        return new Vector(xyz.d2* v.xyz.d3 - xyz.d3 * v.xyz.d2, xyz.d3* v.xyz.d1 - xyz.d1 * v.xyz.d3, xyz.d1* v.xyz.d2 - xyz.d2 * v.xyz.d1);
    }

    public double lengthSquared(){
        return this.dotProduct(this);
    }

    public double length(){
        return Math.sqrt(lengthSquared());
    }

    public Vector normalize(){
        double k = length();
        return new Vector(xyz.reduce(k));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector v)) return false;
        return super.equals(v);
    }

    @Override
    public String toString() {
        return "Vector: " + xyz;
    }
}
