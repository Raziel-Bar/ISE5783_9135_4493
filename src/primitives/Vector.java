package primitives;

/**
 * Represents a vector in 3-dimensional space.
 * This class extends the Point class, so it inherits the x, y, and z coordinates from Point.
 */
public class Vector extends Point {

    /**
     * Constructs a new Vector with the specified x, y, and z coordinates.
     *
     * @param x the x-coordinate of the Vector
     * @param y the y-coordinate of the Vector
     * @param z the z-coordinate of the Vector
     * @throws IllegalArgumentException if the Vector (0,0,0) is created, since it is illegal for use.
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("The Vector (0,0,0) is illegal for use.");
    }

    /**
     * Constructs a new Vector from a Double3 object.
     *
     * @param p the Double3 object to create the Vector from
     */
    Vector(Double3 p) {
        super(p);
        if (xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("The Vector (0,0,0) is illegal for use.");
    }

    /**
     * Adds the specified Vector to this Vector, returning a new Vector as the result.
     *
     * @param v the Vector to add to this Vector
     * @return the sum of the two Vectors as a new Vector
     */
    public Vector add(Vector v) {
        return new Vector(xyz.add(v.xyz));
    }

    /**
     * Scales this vector by a constant factor.
     *
     * @param k the scaling factor
     * @return the scaled vector as a new vector
     */
    public Vector scale(double k) {
        return new Vector(xyz.scale(k));
    }

    /**
     * Computes the dot product of this vector with another vector.
     *
     * @param v the vector to compute the dot product with
     * @return the dot product of the two vectors
     */
    public double dotProduct(Vector v) {
        return xyz.d1 * v.xyz.d1 + xyz.d2 * v.xyz.d2 + xyz.d3 * v.xyz.d3;
    }

    /**
     * Computes and returns the cross product of this vector and the given vector.
     *
     * @param v the vector to compute the cross product with.
     * @return a new vector that is the cross product of this vector and the given vector.
     */
    public Vector crossProduct(Vector v) {
        return new Vector(xyz.d2 * v.xyz.d3 - xyz.d3 * v.xyz.d2, xyz.d3 * v.xyz.d1 - xyz.d1 * v.xyz.d3, xyz.d1 * v.xyz.d2 - xyz.d2 * v.xyz.d1);
    }

    /**
     * Computes and returns the squared length of this vector.
     *
     * @return the squared length of this vector.
     */
    public double lengthSquared() {
        return this.dotProduct(this);
    }

    /**
     * Computes and returns the length of this vector.
     *
     * @return the length of this vector.
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Computes and returns a new vector that is the normalized version of this vector.
     *
     * @return a new vector that is the normalized version of this vector.
     */
    public Vector normalize() {
        double k = length();
        return new Vector(xyz.reduce(k));
    }

    /**
     * Compares this vector to the specified object for equality.
     *
     * @param o the object to compare to this vector.
     * @return true if the specified object is equal to this vector, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof Vector v
                && super.equals(v);
    }

    /**
     * Returns a string representation of this vector.
     *
     * @return a string representation of this vector.
     */
    @Override
    public String toString() {
        return "Vector: " + xyz;
    }
}
